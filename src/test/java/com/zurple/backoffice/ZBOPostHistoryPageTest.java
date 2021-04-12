/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.backoffice.social.ZBODuplicatePage;
import com.zurple.backoffice.social.ZBOEditPostPage;
import com.zurple.backoffice.social.ZBOPostHistoryPage;
import com.zurple.backoffice.social.ZBOScheduledPostsPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.ZurpleListingConstants;

/**
 * @author habibaaq
 *
 */
public class ZBOPostHistoryPageTest extends PageTest{
	
	private WebDriver driver;
	private ZBOPostHistoryPage page;
	
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOPostHistoryPage(driver);
			page.setDriver(driver);
		}
		return page;
	}

	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOPostHistoryPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub	
	}
	
	//(dependsOnGroups= "com.zurple.backoffice.social.ZBOCreatePostPage.testCreatePost",retryAnalyzer = resources.RetryFailedTestCases.class)
	//@Parameters({"dataFile"})
	@Test
	public void testVerifyPostOnPostHistoryPage() throws JSONException, IOException {
		page=null;
		getPage("/social/history");
		boolean lFlag = false;
//		String lPostText = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleSocialPost);
//		String ld_platform = dataObject.optString("platform");
//		String ld_posttype = dataObject.optString("post_type");
		String lFileToRead = "/resources/data/zurple/backoffice/social/zurple-social-all-posts-data.json";
		JSONArray lPostIdsToVerifyArray = new JSONArray(getDataFileContentJsonArray(System.getProperty("user.dir")+lFileToRead));
		// String converted = convertToJSONArray(System.getProperty("user.dir")+lFileToRead);
		// JSONArray lPostIdsToVerifyArray = new JSONArray(converted);
		String lPostText = "";
		String ld_platform = "";
		String ld_posttype = "";
		
		String post_schedule_id_to_verify = "";
		for(int i=0;i<lPostIdsToVerifyArray.length();i++) {
			lFlag = false;
			lPostText = lPostIdsToVerifyArray.getJSONObject(i).get("post_text").toString();
			ld_platform = lPostIdsToVerifyArray.getJSONObject(i).get("platform").toString();
			ld_posttype = lPostIdsToVerifyArray.getJSONObject(i).get("post_type").toString();
			page=null;
			getPage("/social/history");
//			for(int j=0;j<lResponseDataArray.length();j++) {
//				String l_response_post_id = lResponseDataArray.getJSONObject(j).get("post_schedule_id").toString();
//				String l_response_post_status = lResponseDataArray.getJSONObject(j).get("post_status").toString(); //Should be 2
//				String l_response_post_status_code = lResponseDataArray.getJSONObject(j).get("post_status_code").toString(); //Should be 1
//				
//				//Comparing response and data post id
//				AutomationLogger.info("Post ID in data file :: "+post_schedule_id_to_verify);
//				AutomationLogger.info("Post ID in post history:: "+l_response_post_id);
//				if(post_schedule_id_to_verify.equalsIgnoreCase(l_response_post_id) /*&& l_response_post_status.equalsIgnoreCase("2")
//						&& l_response_post_status_code.equalsIgnoreCase("1")*/) {
//					lFlag = true;
//					break;
//				}		
		
		
		
		boolean lSpecialVerification = false;
		
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		if(!ld_platform.equalsIgnoreCase("Twitter") && !ld_posttype.equalsIgnoreCase("post_listing_video")) {
			assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		}
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");

		switch(ld_posttype) {
		case "text":
			assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
			assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			break;
		case "image":
			assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
			assertTrue(page.isPhotoPostIconVisible(lPostText), "Photo post icon is not visible on post history page..");
			assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
			assertTrue(page.isManualPhotPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			break;
		case "listing":
			assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
			assertTrue(page.isHomePostIconVisible(lPostText), "Home post icon is not visible on post history page..");
			assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
			assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
			assertTrue(page.isListingHeadingVisible(lPostText), "Unable to verify listing title..");
			assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description..");
			lSpecialVerification = true;
			break;
		case "video":
			assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
			assertTrue(page.isHomePostListingVideoIconVisible(lPostText), "Home post icon is not visible on post history page..");
			assertTrue(page.isManualListingVideoPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
			lSpecialVerification = true;
			break;
		case "link":
			assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
			assertTrue(page.isPostComputerIconVisible(lPostText), "Home post icon is not visible on post history page..");
			assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
			assertTrue(page.isManualLinkPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, ZurpleListingConstants.zurple_prod_land_url), "Unable to verify listing website Url");
			assertTrue(page.isListingHeadingVisible(lPostText), "Unable to verify listing title..");
			assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description..");
			break;
		}
		
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		
		ZBOEditPostPage editPage = new ZBOEditPostPage(driver);
		assertTrue(editPage.isEditPostPage(), "Edit post page is not visible..");
		assertTrue(editPage.verifyPost(lPostText), "Unable to verify edit post..");
		
		if(!ld_platform.equalsIgnoreCase("YouTube")) {
			assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
			ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
			assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
			//Handled listing and video posts
			if(lSpecialVerification) {
				assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
			}else {
				assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
			}	
			
		}
	}
		emptyFile(lFileToRead, "");
	}
	
	@Test
	@Parameters({"FB"})
	public void testVerifyTextPost(String pDataFile) {
		page=null;
		getPage("/social/history");
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
		assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
		emptyFile(pDataFile, "");
	}
}
