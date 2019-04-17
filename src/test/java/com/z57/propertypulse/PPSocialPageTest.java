/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.data.z57.PPSocialData;

/**
 * @author adar
 *
 */
public class PPSocialPageTest extends PageTest{

	WebDriver driver;
	PPSocialPage page;
	PPHeader header;
	PPSocialData socialData;
	PPPostingHistoryPage postingHistoryPage;
	PPSideMenu sideMenu;
	
	public AbstractPage getPage(String pURL) {
		if(page == null){
			driver = getDriver();
			page = new PPSocialPage(driver);
			header = new PPHeader(driver);
			postingHistoryPage = new PPPostingHistoryPage(driver);
			sideMenu = new PPSideMenu(driver);
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
			postingHistoryPage = new PPPostingHistoryPage(driver);
			sideMenu = new PPSideMenu(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	/*
	 * Facebook status post test case with Now, Later, Recuuring as schedule post option
	 */
	@Test
	@Parameters({"dataFile"})
	public void testPostAStatusToFacebook(String pDataFile) {
		socialData = new PPSocialData(pDataFile).getSocialData();
		String lStatus =socialData.getStatus();
		String lPhotoPath = socialData.getImage();
		String lPostSchedule=socialData.getSchedule();
		String lFacebookPage=socialData.getFacebookPage();
		String lDate=socialData.getStartDate();
		String lTime=socialData.getTime();
		String lEndingDate=socialData.getEndDate();
		String lRepeatOnDays=socialData.getRepeatDays();
		
		getPage("/content/marketing/social");
		
		assertTrue(page.isSocialPage(), "Social Posting page is not displayed..");
		//Selects Facebook option
		assertTrue(page.checkFacebookOption(), "Unable to check Facebook option..");
		
		assertTrue(page.typeStatus(lStatus), "Unable to type status in text area..");
		
		if(!lPhotoPath.isEmpty()) {
			assertTrue(page.clickOnLinkChooseFileButton(), "Unable to click on choose file button..");
		}
		
		assertTrue(page.selectFacebookPage(lFacebookPage), "Unable to select Facebook page from drop down ..");
		
		if(lPostSchedule.equalsIgnoreCase("Later")) {
			assertTrue(page.clickOnScheduleLater(), "Unable to click on Schedule Later radio button..");
			scheduleLater(lDate,lTime);
			
			//Click on Post Later button
			assertTrue(page.clickOnPostLaterButton(), "Unable to click on Post Later button ..");
			
			//Verifying the Later has been scheduled or not
			assertTrue(page.selectNumberOfRecords("100"), "Unable to select total number of records to display per page..");
			assertTrue(page.isUpcomingPostsSuccessful(lStatus, lPostSchedule), "Post not found in Upcoming Post results..");
			
		}else if(lPostSchedule.equalsIgnoreCase("Recurring")) {
			assertTrue(page.clickOnScheduleRecurring(), "Unable to click on Schedule Recurring radio button..");
			scheduleRecurring(lDate,lTime,lEndingDate,lRepeatOnDays);
			
			//Click on Schedule a Recurring post
			assertTrue(page.clickOnPostRecurringButton(), "Unable to click on Post Recurring button ..");
			
			//Verifying the Later has been scheduled or not
			assertTrue(page.selectNumberOfRecords("100"), "Unable to select total number of records to display per page..");
			assertTrue(page.isUpcomingPostsSuccessful(lStatus, lPostSchedule), "Post not found in Upcoming Post results..");
			
		}else {
		
			assertTrue(page.clickOnPostNowButton(), "Unable to click on Post now button ..");
			assertTrue(page.isPostCompleted(), "Post Completed Success Message is not displayed ..");
			
			assertTrue(sideMenu.goToSubMenu("Posting History"), "Unable to click on Posting History button on side menu ..");
			
			assertTrue(postingHistoryPage.isPostingHistoryPage(), "Posting History Page is not opened ..");
			
			assertTrue(postingHistoryPage.isPostCompleted(lStatus), "The Post is not found on Posting History Page ..");
			
//			assertTrue(page.clickOnPreviousPosts(), "Unable to click on Previous Posts tab button..");
//			assertTrue(page.selectNumberOfRecords("100"), "Unable to select total number of records to display per page..");
//			assertTrue(page.isUpcomingPostsSuccessful(lStatus, lPostSchedule), "Post not found in Previous Post results..");
		}
	}
	
	private void scheduleLater(String pDate, String pTime) {
		assertTrue(page.typeDate(pDate), "Unable to type Date ..");
		assertTrue(page.selectTime(pTime), "Unable to select Time from dropdown menu..");
		assertTrue(page.clickOnAddButton(), "Unable to click on Add button..");
		assertTrue(page.isDateTimeAdded(), "Date and time were not added successfully..");
	}
	private void scheduleRecurring(String pDate, String pTime, String pEndingDate,String pWeekdays) {
		assertTrue(page.typeDate(pDate), "Unable to type Date ..");
		assertTrue(page.selectTime(pTime), "Unable to select Time from dropdown menu..");
		assertTrue(page.typeEndingDate(pEndingDate), "Unable to type Ending Date ..");
		assertTrue(page.selectRepeatDays(pWeekdays), "Unable to select repeat days..");
	}

}
