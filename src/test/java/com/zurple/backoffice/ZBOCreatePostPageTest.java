/**
 * 
 */
package com.zurple.backoffice;

import com.zurple.my.PageTest;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.backoffice.social.ZBOCreatePostPage;
import com.zurple.backoffice.social.ZBOPostHistoryPage;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.utility.ActionHelper;

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
	
	@Test(groups= "com.zurple.backoffice.social.ZBOCreatePostPage.testCreatePost")
	@Parameters({"dataFile"})
	public void testCreatePost(String pDataFile) {
		getPage("/social/createpost");
		dataObject = getDataFile(pDataFile);
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
			break;
		case "post_listing_video":
			break;
		case "post_link":
			break;
		}
		if(ld_post_schedule.equalsIgnoreCase("Later")) {
			assertTrue(page.selectSchedule(ld_platform), "Unable to select the date and time..");
		}	
		assertTrue(page.clickOnPostButton(), "Unable to click on Post button");
		ZBOSucessAlert zboSuccessAlert = new ZBOSucessAlert(driver);
		assertTrue(zboSuccessAlert.isSuccessMessageVisible(), "Success message is not visible...");
		assertTrue(zboSuccessAlert.clickOnPostHistoryButton(), "Success message is not visible...");
		ZBOPostHistoryPage postHistoryPageObject = new ZBOPostHistoryPage(driver);
		assertTrue(postHistoryPageObject.isPostingHistoryPageIsVisible(), "Post History page button is not working on success dialog..");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleSocialPost, ld_post_text);
	}

}
