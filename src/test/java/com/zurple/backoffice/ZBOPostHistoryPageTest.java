/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.PageTest;
import com.zurple.backoffice.social.ZBODuplicatePage;
import com.zurple.backoffice.social.ZBOPostHistoryPage;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOPostHistoryPageTest extends PageTest{
	
	private WebDriver driver;
	private JSONObject dataObject;
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
	
	@Test(dependsOnGroups= "com.zurple.backoffice.social.ZBOCreatePostPage.testCreatePost")
	@Parameters({"dataFile"})
	public void testVerifyPostOnPostHistoryPage(String pDataFile) {
		getPage("/social/history");
		dataObject = getDataFile(pDataFile);
		String lPostText = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleSocialPost);
		String ld_platform = dataObject.optString("platform");
		String ld_posttype = dataObject.optString("post_type");
		boolean lSpecialVerification = false;
		
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		if(!ld_platform.equalsIgnoreCase("Twitter") && !ld_posttype.equalsIgnoreCase("post_listing_video")) {
			assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post not found on Post History page.");
		}
		assertTrue(page.getPostPageTitle(lPostText.split(" ")[0].trim()), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");

		switch(ld_posttype) {
		case "post_text":
			assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
			assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			break;
		case "post_image":
			assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
			assertTrue(page.isPhotoPostIconVisible(lPostText), "Photo post icon is not visible on post history page..");
			assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
			assertTrue(page.isManualPhotPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			break;
		case "post_listing":
			assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
			assertTrue(page.isHomePostIconVisible(lPostText), "Home post icon is not visible on post history page..");
			assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
			assertTrue(page.isManualListingPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
			assertTrue(page.isListingHeadingVisible(lPostText), "Unable to verify listing title..");
			assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description..");
			lSpecialVerification = true;
			break;
		case "post_listing_video":
			assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
			assertTrue(page.isHomePostListingVideoIconVisible(lPostText), "Home post icon is not visible on post history page..");
			assertTrue(page.isManualListingVideoPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
			lSpecialVerification = true;
			break;
		case "post_link":
			assertTrue(page.isPostProcessingiconVisible(lPostText), "The post processing icon is still visble after 3 minutes");
			assertTrue(page.isPostComputerIconVisible(lPostText), "Home post icon is not visible on post history page..");
			assertTrue(page.isImageDisplaying(ld_platform, lPostText), "Image is not displaying on post history page..");
			assertTrue(page.isManualLinkPostTextVisible(lPostText), "Manual Page Post text is not visible...");
			assertTrue(page.isListingWebsiteUrlDisplaying(lPostText, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")), "Unable to verify listing website Url");
			assertTrue(page.isListingHeadingVisible(lPostText), "Unable to verify listing title..");
			assertTrue(page.isListingDescVisible(lPostText), "Unable to verify listing description..");
			break;
		}
		
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
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
	@Test(dependsOnGroups= "com.zurple.backoffice.social.ZBOCreatePostPage.testCreatePost")
	public void testVerifyTwitterListingVideoOnPostHistoryPage() {
		getPage("/social/history");
		String lPostText = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleSocialPost);
		
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		//No way to identify iFrame is populated or not thats why
		// adding static wait
		ActionHelper.staticWait(250);
		ActionHelper.RefreshPage(driver);
		ActionHelper.staticWait(120);
		ActionHelper.RefreshPage(driver);
		ActionHelper.staticWait(30);
		assertTrue(page.isTwitterVideoTextVisible(lPostText), "Twitter video is not visible.");
		
	}
}
