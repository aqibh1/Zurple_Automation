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
public class ZBOScheduledPostHistoryPageTest extends PageTest{
	
	private WebDriver driver;
	private ZBOScheduledPostsPage page;
	private JSONObject dataObject;
	
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			//page = new ZBOPostHistoryPage(driver);
			page = new ZBOScheduledPostsPage(driver);
			page.setDriver(driver);
		}
		return page;
	}

	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			//page = new ZBOPostHistoryPage(driver);
			page = new ZBOScheduledPostsPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub	
	}
	
	public void ScheduledPostVerification(String lFileToRead) throws JSONException, IOException {
		page=null;
		getPage("/social/scheduledposts");
		boolean lFlag = false;
		dataObject = getDataFile(lFileToRead);
		String lPostText = dataObject.optString("post_text");
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		boolean lSpecialVerification = false;		
		assertTrue(page.isScheduledPostsPage(), "Scheduled post Page is not visible..");
		if(!ld_platform.equalsIgnoreCase("Twitter") && !ld_posttype.equalsIgnoreCase("post_listing_video")) {
			assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		}
		assertTrue(!page.getPostPageTitle(lPostText).isEmpty(), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");

		switch(ld_posttype) {
		case "text":
			assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
			assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			break;
		case "image":
			//assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
			assertTrue(page.isPhotoPostIconVisible(lPostText), "Photo post icon is not visible on post history page..");
			assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
			assertTrue(page.isManualPhotPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			break;
		case "listing":
			//assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
			assertTrue(page.isHomePostIconVisible(lPostText), "Home post icon is not visible on post history page..");
			assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
			assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
			assertTrue(page.isListingHeadingVisible(lPostText), "Unable to verify listing title..");
			assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description..");
			lSpecialVerification = true;
			break;
		case "video":
			//assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
			assertTrue(page.isHomePostListingVideoIconVisible(lPostText), "Home post icon is not visible on post history page..");
			assertTrue(page.isManualListingVideoPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
			lSpecialVerification = true;
			break;
		case "link":
			//assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
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
		//assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
		
		ZBOEditPostPage editPage = new ZBOEditPostPage(driver);
		assertTrue(editPage.isEditPostPage(), "Edit post page is not visible..");
		assertTrue(editPage.verifyPost(lPostText), "Unable to verify edit post..");
		
		emptyFile(lFileToRead, "");
	}
}
