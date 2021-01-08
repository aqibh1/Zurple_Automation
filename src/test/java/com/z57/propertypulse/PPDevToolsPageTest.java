/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;

/**
 * @author adar
 *
 */
public class PPDevToolsPageTest extends PageTest{
	
	private PPForceExecuteSchedulePost page;
	private WebDriver driver;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPForceExecuteSchedulePost(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new PPForceExecuteSchedulePost(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	public void testVerifyForceRegularListingPosts() {
		String lUrl = "/dev/manual-accelerator/"+EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_account_id")+"/force";
		getPage(lUrl);
		String lListing_Id = ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.ListingId);
		
		assertTrue(page.isQATestToolPage(), "QA test tool page is not visible..");
		assertTrue(page.isListingIdExistFacebook(lListing_Id),"Listing doesn't exist for Facebook");
		assertTrue(page.getFacebookListingStatus().equalsIgnoreCase("1"),"Facebook Listing post is unsuccessful..");
		
		assertTrue(page.isListingIdExistTwitter(lListing_Id),"Listing doesn't exist for Twitter");
		assertTrue(page.getFacebookListingStatus().equalsIgnoreCase("1"),"Facebook Listing post is unsuccessful..");
		
	}
	

}
