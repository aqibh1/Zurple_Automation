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
import com.zurple.backoffice.social.ZBOPostHistoryPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;

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
		
		assertTrue(page.isPostingHistoryPageIsVisible(), "Post History Page is not visible..");
		assertTrue(page.verifyPlatformIconIsVisible(ld_platform, lPostText), "Post Platform icon is not visible on Post History page.");
		assertTrue(!page.getPostPageTitle(lPostText).isEmpty(), "Platform title is not visible...");
		assertTrue(!page.getPostAccountName(lPostText, ld_platform).isEmpty(), "Unable to verify account name...");

		switch(ld_posttype) {
		case "post_text":
			assertTrue(page.isTextPostIconVisible(lPostText), "Unable to verify post icon");
			break;
		case "post_image":
			break;
		case "post_listing":
			break;
		case "post_listing_video":
			break;
		case "post_link":
			break;
		}
		
		assertTrue(!page.getPostPageDate(lPostText).isEmpty(), "Unable to verify post date on history page...");
		assertTrue(!page.getPostPageTime(lPostText).isEmpty(), "Unable to verify post time..");
		assertTrue(page.isManualPostTextVisible(lPostText), "Manual Page Post text is not visible...");
		assertTrue(page.verifyViewPostButtonIsWorking(ld_platform, lPostText), "View post button is not working...");
	}

}
