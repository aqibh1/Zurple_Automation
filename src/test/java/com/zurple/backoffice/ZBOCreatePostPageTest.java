/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.restapi.Part;
import com.restapi.Part.PartType;
import com.zurple.backoffice.social.ZBOCreatePostPage;
import com.zurple.backoffice.social.ZBOPostHistoryPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.utility.ActionHelper;
import resources.utility.ZurpleListingConstants;

/**
 * @author adar
 *
 */
public class ZBOCreatePostPageTest extends PageTest{

	private WebDriver driver;
	private JSONObject dataObject;
	private ZBOCreatePostPage page;
	
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreatePostPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreatePostPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("null")
	@Test(groups= "com.zurple.backoffice.social.ZBOCreatePostPage.testCreatePost",retryAnalyzer = resources.RetryFailedTestCases.class)
	@Parameters({"dataFile"})
	public void testCreatePost(String pDataFile) throws IOException {
		getPage("/social/createpost");
		dataObject = getDataFile(pDataFile);
		String lFileToWrite = "/resources/data/zurple/backoffice/social/zurple-social-all-posts-data.json";
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		String ld_post_text = updateName(dataObject.optString("post_text"));
		String ld_post_schedule = dataObject.optString("post_schedule");
		String ld_post_photo = System.getProperty("user.dir")+dataObject.optString("post_image");

		assertTrue(page.isCreatePostPage(), "Create Post Page is not visible..");
		assertTrue(page.verifyIfPlatformIsConnected(ld_platform), "Platform is not connected "+ld_platform);
		assertTrue(page.clickOnPlatformIcon(ld_platform), "Platform is not connected "+ld_platform);

		assertTrue(!page.getTitle(ld_platform).isEmpty(), "Title of the create post is empty..");
		assertTrue(!page.getUsername(ld_platform).isEmpty(), "Username of the create post is empty..");
		assertTrue(page.verifyPlatformProfilePicsAreVisible(ld_platform), "Profile picture is not correct for "+ld_platform);

		switch(ld_posttype) {
		case "post_text":
			assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
			break;
		case "post_image":
			assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
			assertTrue(page.uploadPhoto(ld_platform, ld_post_photo), "Unable to type text..");
			break;
		case "post_listing":
			assertTrue(page.clickOnPostListingButton(ld_platform), "Unable to click on Post Listing button..");
			ActionHelper.staticWait(3);
			assertTrue(page.selectTheListing(), "Unable to select the listing from Listing Alert..");
			ld_post_text = updateName("");
			ActionHelper.staticWait(20);
			assertTrue(page.appendTextAtStart(ld_platform, ld_post_text), "Unable to type text..");
			ld_post_text = ld_post_text.split(" ")[0];
			break;
		case "post_listing_video":
			if(!ld_platform.equalsIgnoreCase("YouTube")) {
				assertTrue(page.clickOnPostListingVideoButton(ld_platform), "Unable to click on Post Listing button..");
			}
			ActionHelper.staticWait(3);
			assertTrue(page.selectTheListing(), "Unable to select the listing from Listing Alert..");
			ActionHelper.staticWait(10);
			ld_post_text = updateName("");
			assertTrue(page.appendTextAtStart(ld_platform, ld_post_text), "Unable to type text..");
			ld_post_text = ld_post_text.split(" ")[0];
			break;
		case "post_link":
			String listing_link = ZurpleListingConstants.zurple_prod_land_url;
			assertTrue(page.clickOnPostLinkButton(ld_platform), "Unable to click on Post Link button..");
			assertTrue(page.typeLinkUrl(ld_platform, listing_link), "Unable to type listing URL");
			ActionHelper.staticWait(10);
			assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
			break;
		}
		if(ld_post_schedule.equalsIgnoreCase("Later")) {
			assertTrue(page.selectSchedule(ld_platform), "Unable to select the date and time..");
			assertTrue(page.isScheduled(), "Scheduled label is not visible on create post..");
			assertTrue(page.clickOnPostButton(), "Unable to click on Post button");
			ZBOSucessAlert zboSuccessAlert = new ZBOSucessAlert(driver);
			assertTrue(zboSuccessAlert.isSuccessMessageVisible(), "Success message is not visible...");
			assertTrue(zboSuccessAlert.clickOnScheduledPostButton(), "Unable to click on scheduled posts button...");
		}else {
			assertTrue(page.clickOnPostButton(), "Unable to click on Post button");
			ZBOSucessAlert zboSuccessAlert = new ZBOSucessAlert(driver);
			assertTrue(zboSuccessAlert.isSuccessMessageVisible(), "Success message is not visible...");
			assertTrue(zboSuccessAlert.clickOnPostHistoryButton(), "Unable to click on post history button...");
			ZBOPostHistoryPage postHistoryPageObject = new ZBOPostHistoryPage(driver);
			assertTrue(postHistoryPageObject.isPostingHistoryPageIsVisible(), "Post History page button is not working on success dialog..");
			// Map<String, Part> multiParts = new HashMap<String, Part>();
			// multiParts.put("post_text", new Part(ld_post_text, PartType.STRING));
			JSONObject jObject = new JSONObject();
			jObject.put("post_text", ld_post_text);
			jObject.put("platform", ld_platform);
			jObject.put("post_type", ld_posttype);
			writeJsonToFile(lFileToWrite,jObject);
//			//writing empty string in backup cache file
//			emptyFile(lBackUpFile, "");
//			//writing in back up file
//			writeJsonArrayToFile(lBackUpFile, lPostIdsToVerifyArray);
//			//writing empty string in actual cache file
//			emptyFile(lFileToWriteProd, "");
		}
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleSocialPost, ld_post_text);
	}

}
