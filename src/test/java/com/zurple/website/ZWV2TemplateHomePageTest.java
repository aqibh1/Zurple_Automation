package com.zurple.website;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.backoffice.ZBOV2TemplatePage;

import resources.AbstractPage;
import resources.utility.AutomationLogger;
import us.zengtest1.Page;
import us.zengtest1.PageTest;

public class ZWV2TemplateHomePageTest extends PageTest{

	
	private WebDriver driver;
	private JSONObject dataObject;
	ZWV2TemplateHomePage page;
	
	@Override
	public Page getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZWV2TemplateHomePage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZWV2TemplateHomePage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;		
	}
	
	
	@Override
	public void testTitle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testBrand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyV2WebsiteTemplate(String pDataFile) {
		AutomationLogger.startTestCase("Verify V2 website template");
		dataObject = getDataFile(pDataFile);
		getPage();
		assertEquals(page.fbIconExists(),"FACEBOOK");
		assertEquals(page.twitterIconExists(),"TWITTER");
		assertEquals(page.youtubeIconExists(),"YOUTUBE");
		assertEquals(page.getSearchLabel().trim(),dataObject.optString("search_label"));
		assertTrue(page.blurbTitle(),"Unable to get blurb title..");
		assertTrue(page.blurbDescription(),"Unable to get blurb description..");
		assertTrue(page.footerLink(),"Unable to find footer link..");
		assertTrue(page.enterSearchText(dataObject.optString("search_text")),"Unable to type search text..");
		assertTrue(page.clickSearchButton(),"Unable to click search button..");
		assertTrue(page.closeModalAndListingsPage(), "Unable to close modal and verify listings page..");
		assertTrue(page.clickCustomSearch(),"Unable to navigate to home page..");
		assertTrue(page.clickAdvanceSearchButton(),"Unable to click advance search button..");
		assertTrue(page.searchFromAdvanceSearchModal(),"Unable to search from advance search modal");
		assertTrue(page.titleText(), "Unable to find title page..");
		AutomationLogger.endTestCase();
	}
	

}
