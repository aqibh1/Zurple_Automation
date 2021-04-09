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
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
 * @author habibaaq
 *
 */
public class ZBOCreatePostPageTestNew extends PageTest{

	private WebDriver driver;
	private JSONObject dataObject;
	private ZBOCreatePostPage page;
	ZBOScheduledPostHistoryPageTest sPost= new ZBOScheduledPostHistoryPageTest(); 
	
	private String FBTextPost = "/resources/data/zurple/backoffice/social/cache-z-social-fb-text-post.json";
	private String FBPhotoPost = "/resources/data/zurple/backoffice/social/cache-z-social-fb-photo-post.json";
	private String FBLinkPost = "/resources/data/zurple/backoffice/social/cache-z-social-fb-link-post.json";
	private String FBListingPost = "/resources/data/zurple/backoffice/social/cache-z-social-fb-listing-post.json";
	private String FBVideoPost = "/resources/data/zurple/backoffice/social/cache-z-social-fb-listing-video-post.json";
	private String TwitterTextPost = "/resources/data/zurple/backoffice/social/cache-z-social-twitter-text-post.json";
	private String TwitterLinkPost = "/resources/data/zurple/backoffice/social/cache-z-social-twitter-link-post.json";
	private String TwitterListingPost = "/resources/data/zurple/backoffice/social/cache-z-social-twitter-listing-post.json";
	private String TwitterVideoPost = "/resources/data/zurple/backoffice/social/cache-z-social-twitter-listing-video-post.json";
	private String TwitterPhotoPost = "/resources/data/zurple/backoffice/social/cache-z-social-twitter-photo-post.json";
	private String LinkedinTextPost = "/resources/data/zurple/backoffice/social/cache-z-social-linkedin-text-post.json";
	private String LinkedinPhotoPost = "/resources/data/zurple/backoffice/social/cache-z-social-linkedin-photo-post.json";
	private String LinkedinLinkPost = "/resources/data/zurple/backoffice/social/cache-z-social-linkedin-link-post.json";
	private String LinkedinListingPost = "/resources/data/zurple/backoffice/social/cache-z-social-linkedin-listing-post.json";
	private String FBTextPostS = "/resources/data/zurple/backoffice/social/cache-z-social-fb-text-post-scheduled.json";
	private String FBPhotoPostS = "/resources/data/zurple/backoffice/social/cache-z-social-fb-photo-post-scheduled.json";
	private String FBLinkPostS = "/resources/data/zurple/backoffice/social/cache-z-social-fb-link-post-scheduled.json";
	private String FBListingPostS = "/resources/data/zurple/backoffice/social/cache-z-social-fb-listing-post-scheduled.json";
	private String FBVideoPostS = "/resources/data/zurple/backoffice/social/cache-z-social-fb-listing-video-post-scheduled.json";
	private String TwitterTextPostS = "/resources/data/zurple/backoffice/social/cache-z-social-twitter-text-post-scheduled.json";
	private String TwitterLinkPostS = "/resources/data/zurple/backoffice/social/cache-z-social-twitter-link-post-scheduled.json";
	private String TwitterListingPostS = "/resources/data/zurple/backoffice/social/cache-z-social-twitter-listing-post-scheduled.json";
	private String TwitterVideoPostS = "/resources/data/zurple/backoffice/social/cache-z-social-twitter-listing-video-post-scheduled.json";
	private String TwitterPhotoPostS = "/resources/data/zurple/backoffice/social/cache-z-social-twitter-photo-post-scheduled.json";
	private String LinkedinTextPostS = "/resources/data/zurple/backoffice/social/cache-z-social-linkedin-text-post-scheduled.json";
	private String LinkedinPhotoPostS = "/resources/data/zurple/backoffice/social/cache-z-social-linkedin-photo-post-scheduled.json";
	private String LinkedinLinkPostS = "/resources/data/zurple/backoffice/social/cache-z-social-linkedin-link-post-scheduled.json";
	private String LinkedinListingPostS = "/resources/data/zurple/backoffice/social/cache-z-social-linkedin-listing-post-scheduled.json";
	private String YTVideoPost = "/resources/data/zurple/backoffice/social/cache-z-social-yt-video-post.json";

	private String ld_platform = "";
	private String ld_posttype = "";
	private String ld_post_text = "";
	
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreatePostPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreatePostPage(driver);
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
	}
	
	@Test
	@Parameters({"FBDataFile"})
	public void testCreateFBTextScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile, "text");
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		schedulePost();
		sPost.ScheduledPostVerification(FBTextPostS);
		
		saveData(FBTextPostS);
	}
	
	@Test
	@Parameters({"FBDataFile"})
	public void testCreateFBLinkScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile, "link");		
		String listing_link = ZurpleListingConstants.zurple_prod_land_url;
		assertTrue(page.clickOnPostLinkButton(ld_platform), "Unable to click on Post Link button..");
		assertTrue(page.typeLinkUrl(ld_platform, listing_link), "Unable to type listing URL");
		ActionHelper.staticWait(10);
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		schedulePost();
		saveData(FBLinkPostS);
	}
	
	@Test
	@Parameters({"FBDataFile"})
	public void testCreateFBPhotoScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		String ld_post_photo = System.getProperty("user.dir")+dataObject.optString("post_image");
		createPostInitialVerification(pDataFile,"photo");
		assertTrue(!page.getTitle(ld_platform).isEmpty(), "Title of the create post is empty..");
		assertTrue(!page.getUsername(ld_platform).isEmpty(), "Username of the create post is empty..");
		assertTrue(page.verifyPlatformProfilePicsAreVisible(ld_platform), "Profile picture is not correct for "+ld_platform);
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		assertTrue(page.uploadPhoto(ld_platform, ld_post_photo), "Unable to type text..");
		schedulePost();
		saveData(FBPhotoPostS);
	}
	
	@Test
	@Parameters({"FBDataFile"})
	public void testCreateFBListingScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"listing");
		assertTrue(page.clickOnPostListingButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.selectTheListing(), "Unable to select the listing from Listing Alert..");
		ld_post_text = updateName("");
		ActionHelper.staticWait(20);
		assertTrue(page.appendTextAtStart(ld_platform, ld_post_text), "Unable to type text..");
		ld_post_text = ld_post_text.split(" ")[0];
		schedulePost();
		saveData(FBListingPostS);
	}
	
	@Test
	@Parameters({"FBDataFile"})
	public void testCreateFBListingVideoScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile, "video");
		//assertTrue(page.clickOnPostListingButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.clickOnPostListingVideoButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.selectTheListing(), "Unable to select the listing from Listing Alert..");
		ActionHelper.staticWait(10);
		ld_post_text = updateName("");
		assertTrue(page.appendTextAtStart(ld_platform, ld_post_text), "Unable to type text..");
		ld_post_text = ld_post_text.split(" ")[0];
		schedulePost();
		saveData(FBVideoPostS);
	}
	
	@Test
	@Parameters({"TwitterDataFile"})
	public void testCreateTwitterTextScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"text");
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		schedulePost();
		saveData(TwitterTextPostS);
	}
	
	@Test
	@Parameters({"TwitterDataFile"})
	public void testCreateTwitterLinkScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"link");		
		String listing_link = ZurpleListingConstants.zurple_prod_land_url;
		assertTrue(page.clickOnPostLinkButton(ld_platform), "Unable to click on Post Link button..");
		assertTrue(page.typeLinkUrl(ld_platform, listing_link), "Unable to type listing URL");
		ActionHelper.staticWait(10);
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		schedulePost();
		saveData(TwitterLinkPostS);
	}
	
	@Test
	@Parameters({"TwitterDataFile"})
	public void testCreateTwitterPhotoScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		String ld_post_photo = System.getProperty("user.dir")+dataObject.optString("post_image");
		createPostInitialVerification(pDataFile,"photo");
		assertTrue(!page.getTitle(ld_platform).isEmpty(), "Title of the create post is empty..");
		assertTrue(!page.getUsername(ld_platform).isEmpty(), "Username of the create post is empty..");
		assertTrue(page.verifyPlatformProfilePicsAreVisible(ld_platform), "Profile picture is not correct for "+ld_platform);
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		assertTrue(page.uploadPhoto(ld_platform, ld_post_photo), "Unable to type text..");
		schedulePost();
		saveData(TwitterPhotoPostS);
	}
	
	@Test
	@Parameters({"TwitterDataFile"})
	public void testCreateTwitterListingScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"listing");
		assertTrue(page.clickOnPostListingButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.selectTheListing(), "Unable to select the listing from Listing Alert..");
		ld_post_text = updateName("");
		ActionHelper.staticWait(20);
		assertTrue(page.appendTextAtStart(ld_platform, ld_post_text), "Unable to type text..");
		ld_post_text = ld_post_text.split(" ")[0];
		schedulePost();
		saveData(TwitterListingPostS);
	}
	
	@Test
	@Parameters({"TwitterDataFile"})
	public void testCreateTwitterListingVideoScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"video");
		//assertTrue(page.clickOnPostListingButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.clickOnPostListingVideoButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.selectTheListing(), "Unable to select the listing from Listing Alert..");
		ActionHelper.staticWait(10);
		ld_post_text = updateName("");
		assertTrue(page.appendTextAtStart(ld_platform, ld_post_text), "Unable to type text..");
		ld_post_text = ld_post_text.split(" ")[0];
		schedulePost();
		saveData(TwitterVideoPostS);
	}
	
	@Test
	@Parameters({"LinkedinDataFile"})
	public void testCreateLinkedinTextScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"text");
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		schedulePost();
		saveData(LinkedinTextPostS);
	}
	
	@Test
	@Parameters({"LinkedinDataFile"})
	public void testCreateLinkedinLinkScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"link");		
		String listing_link = ZurpleListingConstants.zurple_prod_land_url;
		assertTrue(page.clickOnPostLinkButton(ld_platform), "Unable to click on Post Link button..");
		assertTrue(page.typeLinkUrl(ld_platform, listing_link), "Unable to type listing URL");
		ActionHelper.staticWait(10);
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		schedulePost();
		saveData(LinkedinLinkPostS);
	}
	
	@Test
	@Parameters({"LinkedinDataFile"})
	public void testCreateLinkedinPhotoScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		String ld_post_photo = System.getProperty("user.dir")+dataObject.optString("post_image");
		createPostInitialVerification(pDataFile,"photo");
		assertTrue(!page.getTitle(ld_platform).isEmpty(), "Title of the create post is empty..");
		assertTrue(!page.getUsername(ld_platform).isEmpty(), "Username of the create post is empty..");
		assertTrue(page.verifyPlatformProfilePicsAreVisible(ld_platform), "Profile picture is not correct for "+ld_platform);
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		assertTrue(page.uploadPhoto(ld_platform, ld_post_photo), "Unable to type text..");
		schedulePost();
		saveData(LinkedinPhotoPostS);
	}
	
	@Test
	@Parameters({"LinkedinDataFile"})
	public void testCreateLinkedinListingScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"listing");
		assertTrue(page.clickOnPostListingButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.selectTheListing(), "Unable to select the listing from Listing Alert..");
		ld_post_text = updateName("");
		ActionHelper.staticWait(20);
		assertTrue(page.appendTextAtStart(ld_platform, ld_post_text), "Unable to type text..");
		ld_post_text = ld_post_text.split(" ")[0];
		schedulePost();
		saveData(LinkedinListingPostS);
	}
	
	@Test
	@Parameters({"FBDataFile"})
	public void testCreateFBTextPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"text");
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		postNow();
		saveData(FBTextPost);
	}
	
	@Test
	@Parameters({"FBDataFile"})
	public void testCreateFBLinkPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"link");		
		String listing_link = ZurpleListingConstants.zurple_prod_land_url;
		assertTrue(page.clickOnPostLinkButton(ld_platform), "Unable to click on Post Link button..");
		assertTrue(page.typeLinkUrl(ld_platform, listing_link), "Unable to type listing URL");
		ActionHelper.staticWait(10);
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		postNow();
		saveData(FBLinkPost);
	}
	
	@Test
	@Parameters({"FBDataFile"})
	public void testCreateFBPhotoPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		String ld_post_photo = System.getProperty("user.dir")+dataObject.optString("post_image");
		createPostInitialVerification(pDataFile,"photo");
		assertTrue(!page.getTitle(ld_platform).isEmpty(), "Title of the create post is empty..");
		assertTrue(!page.getUsername(ld_platform).isEmpty(), "Username of the create post is empty..");
		assertTrue(page.verifyPlatformProfilePicsAreVisible(ld_platform), "Profile picture is not correct for "+ld_platform);
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		assertTrue(page.uploadPhoto(ld_platform, ld_post_photo), "Unable to type text..");
		postNow();
		saveData(FBPhotoPost);
	}
	
	@Test
	@Parameters({"FBDataFile"})
	public void testCreateFBListingPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"listing");
		assertTrue(page.clickOnPostListingButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.selectTheListing(), "Unable to select the listing from Listing Alert..");
		ld_post_text = updateName("");
		ActionHelper.staticWait(20);
		assertTrue(page.appendTextAtStart(ld_platform, ld_post_text), "Unable to type text..");
		ld_post_text = ld_post_text.split(" ")[0];
		postNow();
		saveData(FBListingPost);
	}
	
	@Test
	@Parameters({"FBDataFile"})
	public void testCreateFBListingVideoPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"video");
		//assertTrue(page.clickOnPostListingButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.clickOnPostListingVideoButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.selectTheListing(), "Unable to select the listing from Listing Alert..");
		ActionHelper.staticWait(10);
		ld_post_text = updateName("");
		assertTrue(page.appendTextAtStart(ld_platform, ld_post_text), "Unable to type text..");
		ld_post_text = ld_post_text.split(" ")[0];
		postNow();
		saveData(FBVideoPost);
	}
	
	@Test
	@Parameters({"TwitterDataFile"})
	public void testCreateTwitterTextPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"text");
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		postNow();
		saveData(TwitterTextPost);
	}
	
	@Test
	@Parameters({"TwitterDataFile"})
	public void testCreateTwitterLinkPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"link");		
		String listing_link = ZurpleListingConstants.zurple_prod_land_url;
		assertTrue(page.clickOnPostLinkButton(ld_platform), "Unable to click on Post Link button..");
		assertTrue(page.typeLinkUrl(ld_platform, listing_link), "Unable to type listing URL");
		ActionHelper.staticWait(10);
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		postNow();
		saveData(TwitterLinkPost);
	}
	
	@Test
	@Parameters({"TwitterDataFile"})
	public void testCreateTwitterPhotoPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		String ld_post_photo = System.getProperty("user.dir")+dataObject.optString("post_image");
		createPostInitialVerification(pDataFile,"photo");
		assertTrue(!page.getTitle(ld_platform).isEmpty(), "Title of the create post is empty..");
		assertTrue(!page.getUsername(ld_platform).isEmpty(), "Username of the create post is empty..");
		assertTrue(page.verifyPlatformProfilePicsAreVisible(ld_platform), "Profile picture is not correct for "+ld_platform);
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		assertTrue(page.uploadPhoto(ld_platform, ld_post_photo), "Unable to type text..");
		postNow();
		saveData(TwitterPhotoPost);
	}
	
	@Test
	@Parameters({"TwitterDataFile"})
	public void testCreateTwitterListingPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"listing");
		assertTrue(page.clickOnPostListingButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.selectTheListing(), "Unable to select the listing from Listing Alert..");
		ld_post_text = updateName("");
		ActionHelper.staticWait(20);
		assertTrue(page.appendTextAtStart(ld_platform, ld_post_text), "Unable to type text..");
		ld_post_text = ld_post_text.split(" ")[0];
		postNow();
		saveData(TwitterListingPost);
	}
	
	@Test
	@Parameters({"TwitterDataFile"})
	public void testCreateTwitterListingVideoPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"video");
		//assertTrue(page.clickOnPostListingButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.clickOnPostListingVideoButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.selectTheListing(), "Unable to select the listing from Listing Alert..");
		ActionHelper.staticWait(10);
		ld_post_text = updateName("");
		assertTrue(page.appendTextAtStart(ld_platform, ld_post_text), "Unable to type text..");
		ld_post_text = ld_post_text.split(" ")[0];
		postNow();
		saveData(TwitterVideoPost);
	}
	
	@Test
	@Parameters({"LinkedinDataFile"})
	public void testCreateLinkedinTextPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"text");
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		postNow();
		saveData(LinkedinTextPost);
	}
	
	@Test
	@Parameters({"LinkedinDataFile"})
	public void testCreateLinkedinLinkPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"link");		
		String listing_link = ZurpleListingConstants.zurple_prod_land_url;
		assertTrue(page.clickOnPostLinkButton(ld_platform), "Unable to click on Post Link button..");
		assertTrue(page.typeLinkUrl(ld_platform, listing_link), "Unable to type listing URL");
		ActionHelper.staticWait(10);
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		postNow();
		saveData(LinkedinLinkPost);
	}
	
	@Test
	@Parameters({"LinkedinDataFile"})
	public void testCreateLinkedinPhotoPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		String ld_post_photo = System.getProperty("user.dir")+dataObject.optString("post_image");
		createPostInitialVerification(pDataFile,"photo");
		assertTrue(!page.getTitle(ld_platform).isEmpty(), "Title of the create post is empty..");
		assertTrue(!page.getUsername(ld_platform).isEmpty(), "Username of the create post is empty..");
		assertTrue(page.verifyPlatformProfilePicsAreVisible(ld_platform), "Profile picture is not correct for "+ld_platform);
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		assertTrue(page.uploadPhoto(ld_platform, ld_post_photo), "Unable to type text..");
		postNow();
		saveData(LinkedinPhotoPost);
	}
	
	@Test
	@Parameters({"LinkedinDataFile"})
	public void testCreateLinkedinListingPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"listing");
		assertTrue(page.clickOnPostListingButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.selectTheListing(), "Unable to select the listing from Listing Alert..");
		ld_post_text = updateName("");
		ActionHelper.staticWait(20);
		assertTrue(page.appendTextAtStart(ld_platform, ld_post_text), "Unable to type text..");
		ld_post_text = ld_post_text.split(" ")[0];
		postNow();
		saveData(LinkedinListingPost);
	}
	
	@Test
	@Parameters({"YTDataFile"})
	public void testCreateYTListingVideoPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"video");
		//assertTrue(page.clickOnPostListingButton(ld_platform), "Unable to click on Post Listing button..");
//		ActionHelper.staticWait(3);
//		assertTrue(page.clickOnPostListingVideoButton(ld_platform), "Unable to click on Post Listing button..");
		ActionHelper.staticWait(3);
		assertTrue(page.selectTheListing(), "Unable to select the listing from Listing Alert..");
		ActionHelper.staticWait(10);
		ld_post_text = updateName("");
		assertTrue(page.appendTextAtStart(ld_platform, ld_post_text), "Unable to type text..");
		ld_post_text = ld_post_text.split(" ")[0];
		postNow();
		saveData(YTVideoPost);
	}
	
	public void createPostInitialVerification(String pDataFile, String post_type) {
		dataObject = getDataFile(pDataFile);
		ld_platform = dataObject.optString("platform");
		ld_posttype = dataObject.optString(post_type);
		ld_post_text = updateName(dataObject.optString("post_text"));
		assertTrue(page.isCreatePostPage(), "Create Post Page is not visible..");
		assertTrue(page.verifyIfPlatformIsConnected(ld_platform), "Platform is not connected "+ld_platform);
		assertTrue(page.clickOnPlatformIcon(ld_platform), "Platform is not connected "+ld_platform);
		assertTrue(!page.getTitle(ld_platform).isEmpty(), "Title of the create post is empty..");
		assertTrue(!page.getUsername(ld_platform).isEmpty(), "Username of the create post is empty..");
		assertTrue(page.verifyPlatformProfilePicsAreVisible(ld_platform), "Profile picture is not correct for "+ld_platform);
	}
	
	public void schedulePost() {
		assertTrue(page.selectSchedule(ld_platform), "Unable to select the date and time..");
		assertTrue(page.isScheduled(), "Scheduled label is not visible on create post..");
		assertTrue(page.clickOnPostButton(), "Unable to click on Post button");
		ZBOSucessAlert zboSuccessAlert = new ZBOSucessAlert(driver);
		assertTrue(zboSuccessAlert.isSuccessMessageVisible(), "Success message is not visible...");
		// assertTrue(zboSuccessAlert.clickOnScheduledPostButton(), "Unable to click on scheduled posts button...");
	}
	
	public void postNow() {
		assertTrue(page.clickOnPostButton(), "Unable to click on Post button");
		ZBOSucessAlert zboSuccessAlert = new ZBOSucessAlert(driver);
		assertTrue(zboSuccessAlert.isSuccessMessageVisible(), "Success message is not visible...");
	}
	
	public void saveData(String fileToWrite) {
		JSONObject jObject = new JSONObject();
		jObject.put("post_text", ld_post_text);
		jObject.put("platform", ld_platform);
		jObject.put("post_type", ld_posttype);
		writeJsonToFile(fileToWrite,jObject);
	}
	
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}

}
