/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;

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
	String lPostText = "";
	String ld_platform = "";
	String ld_posttype = "";
	
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
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
		page=null;
		getPage("/social/history");
		scrollPageForPostToBeVisible();
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
	}
	
	////////////FACEBOOK////////////////
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookPostPageTitle(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookPostAccountName(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
	}

	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookPlatformIconIsVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookTextPostIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookManualPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookPostPageDate(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookPostPageTime(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookViewPostButtonIsWorking(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookDuplicatePostButtonIsWorking(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookDuplicatePostPage(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_posttype = dataObject.optString("post_type");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		if(ld_posttype.contains("listing") || ld_posttype.contains("video")) {
			assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
		} else {
			assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
		}
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookPhotoPostIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isPhotoPostIconVisible(lPostText), "Photo post icon is not displaying...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookImageDisplaying(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(page.isImageDisplaying(ld_platform,lPostText), "Photo post image is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookManualPhotPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualPhotPostTextVisible(lPostText), "Photo post text is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookPostComputerIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isPostComputerIconVisible(lPostText), "Post computer icon is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookManualLinkPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualLinkPostTextVisible(lPostText), "Manual link post text is not visible..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookHomePostIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isHomePostIconVisible(lPostText), "Home icon for listing post is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookManualListingPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual listing post text is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookListingWebsiteUrlDisplaying(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookListingHeadingVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isListingHeadingVisible(lPostText), "Listing heading is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookListingDescVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
		assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookHomePostListingVideoIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isHomePostListingVideoIconVisible(lPostText), "Unable to verify listing Video icon...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyFacebookManualListingVideoPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualListingVideoPostTextVisible(lPostText), "Unable to verify listing video post text...");
	}
	
		////////////TWITTER////////////////
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterPostPageTitle(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterPostAccountName(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
	}

	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterPlatformIconIsVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterTextPostIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterManualPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterPostPageDate(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterPostPageTime(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterViewPostButtonIsWorking(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterDuplicatePostButtonIsWorking(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterDuplicatePostPage(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_posttype = dataObject.optString("post_type");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		if(ld_posttype.contains("listing") || ld_posttype.contains("video")) {
			assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
		} else {
			assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
		}
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterPhotoPostIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isPhotoPostIconVisible(lPostText), "Photo post icon is not displaying...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterImageDisplaying(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(page.isImageDisplaying(ld_platform,lPostText), "Photo post image is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterManualPhotPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualPhotPostTextVisible(lPostText), "Photo post text is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterPostComputerIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isPostComputerIconVisible(lPostText), "Post computer icon is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterManualLinkPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualLinkPostTextVisible(lPostText), "Manual link post text is not visible..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterHomePostIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isHomePostIconVisible(lPostText), "Home icon for listing post is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterManualListingPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual listing post text is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterListingWebsiteUrlDisplaying(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterListingHeadingVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isListingHeadingVisible(lPostText), "Listing heading is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterListingDescVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterHomePostListingVideoIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isHomePostListingVideoIconVisible(lPostText), "Unable to verify listing Video icon...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterManualListingVideoPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualListingVideoPostTextVisible(lPostText), "Unable to verify listing video post text...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyTwitterTwitterVideoTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isTwitterVideoTextVisible(lPostText), "Unable to verify twitter video text..");
	}
	
	
	////////////LINKEDIN////////////////
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinPostPageTitle(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinPostAccountName(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
	}

	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinPlatformIconIsVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinTextPostIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinManualPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinPostPageDate(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinPostPageTime(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinViewPostButtonIsWorking(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinDuplicatePostButtonIsWorking(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.verifyDuplicatePostButtonIsWorking(lPostText), "Duplicate post button is not working...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinDuplicatePostPage(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_posttype = dataObject.optString("post_type");
		ZBODuplicatePage duplicatePage = new ZBODuplicatePage(driver);
		assertTrue(duplicatePage.isDuplicatePostPage(), "Duplicate post page is not visible..");
		if(ld_posttype.contains("listing") || ld_posttype.contains("video")) {
			assertTrue(duplicatePage.verifyPost("Check out this"), "Unable to verify duplicate post..");
		} else {
			assertTrue(duplicatePage.verifyPost(lPostText), "Unable to verify duplicate post..");
		}
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinPhotoPostIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isPhotoPostIconVisible(lPostText), "Photo post icon is not displaying...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinImageDisplaying(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(page.isImageDisplaying(ld_platform,lPostText), "Photo post image is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinManualPhotPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualPhotPostTextVisible(lPostText), "Photo post text is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinPostComputerIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isPostComputerIconVisible(lPostText), "Post computer icon is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinManualLinkPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualLinkPostTextVisible(lPostText), "Manual link post text is not visible..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinHomePostIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isHomePostIconVisible(lPostText), "Home icon for listing post is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinManualListingPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual listing post text is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinListingWebsiteUrlDisplaying(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinListingHeadingVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isListingHeadingVisible(lPostText), "Listing heading is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLinkedinListingDescVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description...");
	}
	
	////////////YOUTUBE////////////////
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubePostPageTitle(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.getPostPageTitle(lPostText), "Platform title is not visible...");
		assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubePostAccountName(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");
	}

	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubePlatformIconIsVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubePostPageDate(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubePostPageTime(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubeViewPostButtonIsWorking(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		ld_platform = dataObject.optString("platform");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubeManualPhotPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualPhotPostTextVisible(lPostText), "Photo post text is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubePostComputerIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isPostComputerIconVisible(lPostText), "Post computer icon is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubeManualLinkPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualLinkPostTextVisible(lPostText), "Manual link post text is not visible..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubeManualListingPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual listing post text is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubeListingWebsiteUrlDisplaying(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubeListingHeadingVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isListingHeadingVisible(lPostText), "Listing heading is not visible...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubeListingDescVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubeHomePostListingVideoIconVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isHomePostListingVideoIconVisible(lPostText), "Unable to verify listing Video icon...");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyYouTubeManualListingVideoPostTextVisible(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		lPostText = dataObject.optString("post_text");
		assertTrue(page.isManualListingVideoPostTextVisible(lPostText), "Unable to verify listing video post text...");
	}

	///////Others///////
	
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



