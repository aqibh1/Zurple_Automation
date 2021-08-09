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
import resources.utility.CacheFilePathsConstants;
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
		saveData(CacheFilePathsConstants.FBTextPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.FBTextPostS);
		saveData(CacheFilePathsConstants.FBTextPost);
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
		ActionHelper.staticWait(5);
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		schedulePost();
		saveData(CacheFilePathsConstants.FBLinkPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.FBLinkPostS);
		saveData(CacheFilePathsConstants.FBLinkPost);
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
		saveData(CacheFilePathsConstants.FBPhotoPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.FBPhotoPostS);
		saveData(CacheFilePathsConstants.FBPhotoPost);
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
		saveData(CacheFilePathsConstants.FBListingPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.FBListingPostS);
		saveData(CacheFilePathsConstants.FBListingPost);
	}
	
	@Test
	@Parameters({"FBDataFile"})
	public void testCreateFBListingVideoScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		int tryCount = 0;
		boolean isSuccess = false;
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
		if(!listingSchedulePost()) {
			do {
				ActionHelper.RefreshPage(driver);
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
				isSuccess = listingSchedulePost();
				tryCount++;
			} while(!isSuccess || tryCount!=3);
		}
		saveData(CacheFilePathsConstants.FBVideoPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.FBVideoPostS);
		saveData(CacheFilePathsConstants.FBVideoPost);
	}
	
	@Test
	@Parameters({"TwitterDataFile"})
	public void testCreateTwitterTextScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"text");
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		schedulePost();
		saveData(CacheFilePathsConstants.TwitterTextPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.TwitterTextPostS);
		saveData(CacheFilePathsConstants.TwitterTextPost);
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
		saveData(CacheFilePathsConstants.TwitterLinkPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.TwitterLinkPostS);
		saveData(CacheFilePathsConstants.TwitterLinkPost);
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
		saveData(CacheFilePathsConstants.TwitterPhotoPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.TwitterPhotoPostS);
		saveData(CacheFilePathsConstants.TwitterPhotoPost);
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
		saveData(CacheFilePathsConstants.TwitterListingPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.TwitterListingPostS);
		saveData(CacheFilePathsConstants.TwitterListingPost);
	}
	
	@Test
	@Parameters({"TwitterDataFile"})
	public void testCreateTwitterListingVideoScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		int tryCount = 0;
		boolean isSuccess = false;
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
		if(!listingSchedulePost()) {
			do {
				ActionHelper.RefreshPage(driver);
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
				isSuccess = listingSchedulePost();
				tryCount++;
			} while(!isSuccess || tryCount!=3);
		}
		saveData(CacheFilePathsConstants.TwitterVideoPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.TwitterVideoPostS);
		saveData(CacheFilePathsConstants.TwitterVideoPost);
	}
	
	@Test
	@Parameters({"LinkedinDataFile"})
	public void testCreateLinkedinTextScheduledPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"text");
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		schedulePost();
		saveData(CacheFilePathsConstants.LinkedinTextPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.LinkedinTextPostS);
		saveData(CacheFilePathsConstants.LinkedinTextPost);
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
		saveData(CacheFilePathsConstants.LinkedinLinkPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.LinkedinLinkPostS);
		saveData(CacheFilePathsConstants.LinkedinLinkPost);
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
		saveData(CacheFilePathsConstants.LinkedinPhotoPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.LinkedinPhotoPostS);
		saveData(CacheFilePathsConstants.LinkedinPhotoPost);
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
		saveData(CacheFilePathsConstants.LinkedinListingPostS);
		sPost.ScheduledPostVerification(CacheFilePathsConstants.LinkedinListingPostS);
		saveData(CacheFilePathsConstants.LinkedinListingPost);
	}
	
	@Test
	@Parameters({"FBDataFile"})
	public void testCreateFBTextPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"text");
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		postNow();
		saveData(CacheFilePathsConstants.FBTextPost);
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
		saveData(CacheFilePathsConstants.FBLinkPost);
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
		saveData(CacheFilePathsConstants.FBPhotoPost);
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
		saveData(CacheFilePathsConstants.FBListingPost);
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
		saveData(CacheFilePathsConstants.FBVideoPost);
	}
	
	@Test
	@Parameters({"TwitterDataFile"})
	public void testCreateTwitterTextPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"text");
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		postNow();
		saveData(CacheFilePathsConstants.TwitterTextPost);
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
		saveData(CacheFilePathsConstants.TwitterLinkPost);
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
		saveData(CacheFilePathsConstants.TwitterPhotoPost);
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
		saveData(CacheFilePathsConstants.TwitterListingPost);
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
		saveData(CacheFilePathsConstants.TwitterVideoPost);
	}
	
	@Test
	@Parameters({"LinkedinDataFile"})
	public void testCreateLinkedinTextPost(String pDataFile) throws IOException {
		page = null;
		getPage("/social/createpost");
		createPostInitialVerification(pDataFile,"text");
		assertTrue(page.typeTextPost(ld_platform, ld_post_text), "Unable to type text..");
		postNow();
		saveData(CacheFilePathsConstants.LinkedinTextPost);
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
		saveData(CacheFilePathsConstants.LinkedinLinkPost);
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
		saveData(CacheFilePathsConstants.LinkedinPhotoPost);
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
		saveData(CacheFilePathsConstants.LinkedinListingPost);
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
		saveData(CacheFilePathsConstants.YTVideoPost);
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
	
	public boolean listingSchedulePost() {
		boolean isSuccess = false; 
		assertTrue(page.selectSchedule(ld_platform), "Unable to select the date and time..");
		assertTrue(page.isScheduled(), "Scheduled label is not visible on create post..");
		assertTrue(page.clickOnPostButton(), "Unable to click on Post button");
		ZBOSucessAlert zboSuccessAlert = new ZBOSucessAlert(driver);
		isSuccess = zboSuccessAlert.isSuccessMessageVisible();
		return isSuccess;
	}
	
	public void schedulePost() {
		assertTrue(page.selectSchedule(ld_platform), "Unable to select the date and time..");
		assertTrue(page.isScheduled(), "Scheduled label is not visible on create post..");
		assertTrue(page.clickOnPostButton(), "Unable to click on Post button");
		ZBOSucessAlert zboSuccessAlert = new ZBOSucessAlert(driver);
		assertTrue(zboSuccessAlert.isSuccessMessageVisible(), "Success message is not visible...");
	}
	
	public void postNow() {
		assertTrue(page.clickOnPostButton(), "Unable to click on Post button");
		ZBOSucessAlert zboSuccessAlert = new ZBOSucessAlert(driver);
		assertTrue(zboSuccessAlert.isSuccessMessageVisible(), "Success message is not visible...");
	}
	
	public void saveData(String fileToWrite) {
		emptyFile(fileToWrite, "");
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

