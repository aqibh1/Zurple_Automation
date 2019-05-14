package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;

public class PPAnalyticsPageTest extends PageTest{
	
	private WebDriver driver;
	private PPAnalyticsPage page;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPAnalyticsPage(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new PPAnalyticsPage(driver);
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
	public void testVerifyFacebookAnalyticsPage() {
		getPage("/content/analytics");
		String faceBookPageToSelect = "Agent Thomsan";
		assertTrue(page.isDashboardAnalyticsPage(), "Dashboard Analytics page is not visible..");
		assertTrue(page.selectFacebookPage(faceBookPageToSelect), "Unable to select facebook page from drop down..");
		assertTrue(page.isFacebookGraphVisible(), "Facebook graph container is not visible..");
		assertTrue(page.verifyAllTabStats(), "All tabs stats are not displayed..");
		assertTrue(page.clickAndVerifyStatusResults(), "Statuses tabs stats are not displayed..");
		assertTrue(page.clickAndVerifyLinksResults(), "Links tabs stats are not displayed..");
		assertTrue(page.clickAndVerifyPhotosResults(), "Photos tabs stats are not displayed..");
		assertTrue(page.clickAndVerifyVideosResults(), "Videos tabs stats are not displayed..");
		
	}
}
