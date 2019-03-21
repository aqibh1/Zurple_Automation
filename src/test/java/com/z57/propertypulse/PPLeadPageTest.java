package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.hibernate.validator.AssertTrueValidator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.z57.propertypulse.PPLeadsPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;

public class PPLeadPageTest extends PageTest{
	WebDriver driver;
	private PPLeadsPage page;
	private String url = "/leads/manager";

	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPLeadsPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new PPLeadsPage(driver);
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
	public void testAddLead(String pDataFile) {
		getPage(url);
		assertTrue(page.isLeadPage(), "Lead page is not opened");
		assertTrue(page.clickOnManualEntryDropDown(), "Unable to click on Manual Entry drop down button");
		
		
	}

}
