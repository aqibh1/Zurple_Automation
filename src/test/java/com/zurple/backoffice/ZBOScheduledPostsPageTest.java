/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.backoffice.social.ZBOCreatePostPage;
import com.zurple.backoffice.social.ZBODuplicatePage;
import com.zurple.backoffice.social.ZBOEditPostPage;
import com.zurple.backoffice.social.ZBOPostHistoryPage;
import com.zurple.backoffice.social.ZBOScheduledPostsPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;

/**
 * @author adar
 *
 */
public class ZBOScheduledPostsPageTest extends PageTest{
	
	private WebDriver driver;
	private JSONObject dataObject;
	private ZBOScheduledPostsPage page;
	
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOScheduledPostsPage(driver);
			page.setDriver(driver);
		}
		return page;
	}

	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
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
	
	@Test(dependsOnGroups= "com.zurple.backoffice.social.ZBOCreatePostPage.testCreatePost")
	@Parameters({"dataFile"})
	public void testVerifyPostOnScheduledPostsPage(String pDataFile) {
		getPage("/social/scheduledposts");
		dataObject = getDataFile(pDataFile);
		String lPostText = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleSocialPost);
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		
		assertTrue(page.isShceduledPostsPage(), "Scheduled post Page is not visible..");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post Platform icon is not visible on scheduled posts page.");
		assertTrue(!page.getPostPageTitle(lPostText).isEmpty(), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");

		switch(ld_posttype) {
		case "post_text":
			assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
			assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			break;
		case "post_image":
			assertTrue(page.isPhotoPostIconVisible(lPostText), "Photo post icon is not visible on post scheduled posts page..");
			assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post scheduled posts page..");
			assertTrue(page.isManualPhotPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			break;
		case "post_listing":
			assertTrue(page.isHomePostIconVisible(lPostText), "Home post icon is not visible on post scheduled posts page..");
			assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post scheduled posts page..");
			assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
			assertTrue(page.isListingHeadingVisible(lPostText), "Unable to verify listing title..");
			assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description..");
			break;
		case "post_listing_video":
			assertTrue(page.isHomePostListingVideoIconVisible(lPostText), "Home post icon is not visible on post scheduled posts page..");
			assertTrue(page.isManualListingVideoPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
			break;
		case "post_link":
			assertTrue(page.isPostComputerIconVisible(lPostText), "Home post icon is not visible on post scheduled posts page..");
			assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post scheduled posts page..");
			assertTrue(page.isManualLinkPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
			assertTrue(page.isListingHeadingVisible(lPostText), "Unable to verify listing title..");
			assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description..");
			break;
		}
		
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyEditPostButtonIsWorking(lPostText), "Unable to verify edit post button is working..");
		ZBOEditPostPage editPage = new ZBOEditPostPage(driver);
		assertTrue(editPage.isEditPostPage(), "Edit post page is not visible..");
		assertTrue(editPage.verifyPost(lPostText), "Unable to verify edit post..");
		
	}

}
