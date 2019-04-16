/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;

/**
 * @author adar
 *
 */
public class PPSocialPageTest extends PageTest{

	WebDriver driver;
	PPSocialPage page;
	PPHeader header;
	
	public AbstractPage getPage(String pURL) {
		if(page == null){
			driver = getDriver();
			page = new PPSocialPage(driver);
			header = new PPHeader(driver);
			page.setUrl(pURL);
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPSocialPage(driver);
			header = new PPHeader(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	/*
	 * Facebook status post test case with Now as schedule post option
	 */
	@Test
	public void testPostAStatusToFacebook() {
		String lStatus ="";
		String lPhotoPath = "";
		String lPostSchedule="";
		String lFacebookPage="";
		
		getPage("/content/marketing/social");
		
		assertTrue(page.isSocialPage(), "Social Posting page is not displayed..");
		//Selects Facebook option
		assertTrue(page.checkFacebookOption(), "Unable to check Facebook option..");
		
		assertTrue(page.typeStatus(lStatus), "Unable to type status in text area..");
		
		if(!lPhotoPath.isEmpty()) {
			assertTrue(page.clickOnLinkChooseFileButton(), "Unable to click on choose file button..");
		}
		
		if(lPostSchedule.equalsIgnoreCase("Later")) {
			assertTrue(page.clickOnScheduleLater(), "Unable to click on Schedule Later radio button..");
		}
		if(lPostSchedule.equalsIgnoreCase("Recurring")) {
			assertTrue(page.clickOnScheduleRecurring(), "Unable to click on Schedule Recurring radio button..");
		}
		
		assertTrue(page.selectFacebookPage(lFacebookPage), "Unable to select Facebook page from drop down ..");
		
		assertTrue(page.clickOnPostNowButton(), "Unable to click on Post now button ..");
		
	}

}
