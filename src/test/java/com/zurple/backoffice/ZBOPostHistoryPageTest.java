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
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOPostHistoryPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver,pUrl,true);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub	
	}
	
	@BeforeTest
	public void backOfficeLogin() {
		page = null;
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}

	/////FACEBOOK////
	
	@Test
	@Parameters({"FBText"})
	public void testVerifyTextPostFB(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
		assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"FBTextS"})
	public void testVerifyTextPostFBScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
		assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"FBPhoto"})
	public void testVerifyPhotoPostFB(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isPhotoPostIconVisible(lPostText), "Photo post icon is not visible on post history page..");
		assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
		assertTrue(page.isManualPhotPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"FBPhotoScheduled"})
	public void testVerifyPhotoPostFBScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isPhotoPostIconVisible(lPostText), "Photo post icon is not visible on post history page..");
		assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
		assertTrue(page.isManualPhotPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"FBLink"})
	public void testVerifyLinkPostFB(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isPostComputerIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isManualLinkPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"FBLinkScheduled"})
	public void testVerifyLinkPostFBScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isPostComputerIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isManualLinkPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"FBListing"})
	public void testVerifyListingPostFB(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isHomePostIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
		assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
		assertTrue(page.isListingHeadingVisible(lPostText), "Unable to verify listing title..");
		assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description..");		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"FBListingScheduled"})
	public void testVerifyListingPostFBScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isHomePostIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
		assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
		assertTrue(page.isListingHeadingVisible(lPostText), "Unable to verify listing title..");
		assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description..");		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"FBListingVideo"})
	public void testVerifyListingVideoPostFB(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isHomePostListingVideoIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isManualListingVideoPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"FBListingVideoScheduled"})
	public void testVerifyListingVideoPostFBScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isHomePostListingVideoIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isManualListingVideoPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
	}
	
	/////Twitter////

	@Test
	@Parameters({"TWITTERText"})
	public void testVerifyTextPostTwitter(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
		assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"TWITTERTextS"})
	public void testVerifyTextPostTwitterScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
		assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"TWITTERPhoto"})
	public void testVerifyPhotoPostTwitter(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isPhotoPostIconVisible(lPostText), "Photo post icon is not visible on post history page..");
		assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
		assertTrue(page.isManualPhotPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"TWITTERPhotoScheduled"})
	public void testVerifyPhotoPostTwitterScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isPhotoPostIconVisible(lPostText), "Photo post icon is not visible on post history page..");
		assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
		assertTrue(page.isManualPhotPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"TWITTERLink"})
	public void testVerifyLinkPostTwitter(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isPostComputerIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isManualLinkPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"TWITTERLinkScheduled"})
	public void testVerifyLinkPostTwitterScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isPostComputerIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isManualLinkPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"TWITTERListing"})
	public void testVerifyListingPostTwitter(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isHomePostIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
		assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
		assertTrue(page.isListingHeadingVisible(lPostText), "Unable to verify listing title..");
		assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description..");		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"TWITTERListingScheduled"})
	public void testVerifyListingPostTwitterScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isHomePostIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
		assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
		assertTrue(page.isListingHeadingVisible(lPostText), "Unable to verify listing title..");
		assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description..");		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"TWITTERListingVideo"})
	public void testVerifyListingVideoPostTwitter(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.isTwitterVideoTextVisible(lPostText), "Platform title is not visible...");
		//assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
//		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
//		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
//		assertTrue(page.isHomePostListingVideoIconVisible(lPostText), "Home post icon is not visible on post history page..");
//		assertTrue(page.isManualListingVideoPostTextVisible(lPostText), "Manual Page Post text is not visible...");
//		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"TWITTERListingVideoScheduled"})
	public void testVerifyListingVideoPostTwitterScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.isTwitterVideoTextVisible(lPostText), "Platform title is not visible...");
		//assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		//assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
//		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
//		assertTrue(page.isHomePostListingVideoIconVisible(lPostText), "Home post icon is not visible on post history page..");
//		assertTrue(page.isManualListingVideoPostTextVisible(lPostText), "Manual Page Post text is not visible...");
//		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
	}
	
	
	/////LINKEDIN////
	
	
	@Test
	@Parameters({"LINKEDINText"})
	public void testVerifyTextPostLinkedin(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
		assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"LINKEDINTextS"})
	public void testVerifyTextPostLinkedinScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
		assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"LINKEDINPhoto"})
	public void testVerifyPhotoPostLinkedin(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isPhotoPostIconVisible(lPostText), "Photo post icon is not visible on post history page..");
		assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
		assertTrue(page.isManualPhotPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"LINKEDINPhotoScheduled"})
	public void testVerifyPhotoPostLinkedinScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible(); 
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isPhotoPostIconVisible(lPostText), "Photo post icon is not visible on post history page..");
		assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
		assertTrue(page.isManualPhotPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"LINKEDINLink"})
	public void testVerifyLinkPostLinkedin(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isPostComputerIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isManualLinkPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"LINKEDINLinkScheduled"})
	public void testVerifyLinkPostLinkedinScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isPostComputerIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isManualLinkPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"LINKEDINListing"})
	public void testVerifyListingPostLinkedin(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isHomePostIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
		assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
		assertTrue(page.isListingHeadingVisible(lPostText), "Unable to verify listing title..");
		assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description..");		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
	}
	
	@Test
	@Parameters({"LINKEDINListingScheduled"})
	public void testVerifyListingPostLinkedinScheduled(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isHomePostIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
		assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
		assertTrue(page.isListingHeadingVisible(lPostText), "Unable to verify listing title..");
		assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description..");		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
	}
	
	
	/////YOUTUBE////
	
	@Test
	@Parameters({"YouTubeListingVideo"})
	public void testVerifyListingVideoPostYoutube(String pDataFile) {
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		// assertTrue(page.isHomePostListingVideoIconVisible(lPostText), "Home post icon is not visible on post history page..");
		assertTrue(page.isManualListingVideoPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
	}
	
	public void scrollPageForPostToBeVisible() {
		ActionHelper.RefreshPage(driver); 
		ActionHelper.RefreshPage(driver); 
		ActionHelper.staticWait(4); 
		ActionHelper.ScrollDownByPixels(driver, "9000");
		ActionHelper.staticWait(2);
		ActionHelper.ScrollDownByPixels(driver, "9000"); 
		ActionHelper.staticWait(2);
		ActionHelper.ScrollDownByPixels(driver, "9000"); 
		ActionHelper.staticWait(2);
		ActionHelper.ScrollDownByPixels(driver, "9000"); 
		ActionHelper.staticWait(2); 
	}
	
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}	
}



