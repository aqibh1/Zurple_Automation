/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.time.LocalDate;

import org.hibernate.property.Getter;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.TestEnvironment;
import resources.data.z57.PPSocialData;
import resources.orm.hibernate.HibernateUtil;
import resources.orm.hibernate.models.pp.Posts;
import resources.utility.FrameworkConstants;

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
	@Parameters({"dataFileSocial"})
	public void testPostAStatusToFacebook(String pDataFile) throws AWTException {
		socialData = new PPSocialData(pDataFile).getSocialData();
		String lStatus =updateName(socialData.getStatus());
		String lPhotoPath = socialData.getImage();
		String lPostSchedule=socialData.getSchedule();
		String lFacebookPage=socialData.getFacebookPage();
		String lDate=getTodaysDate();//socialData.getStartDate();
		String lTime=getPSTTime();//socialData.getTime();
		String lEndingDate=getTomorrowsDate();//socialData.getEndDate();
		String lRepeatOnDays=getCurrentAndNextDayOfTheWeek();//socialData.getRepeatDays();
		String lPromoteListing = socialData.getPromoteListing();
		String lLinkToProperty = socialData.getPropertyLink();
		String lFileToWriteProd = "/resources/cache/posts-to-verify-prod.json";
		String lFileToWriteStage = "/resources/cache/posts-to-verify-qa.json";
		
		getPage("/content/marketing/social");
		
		assertTrue(page.isSocialPage(), "Social Posting page is not displayed..");
		
		//Selects Facebook option
		assertTrue(page.checkFacebookOption(), "Unable to check Facebook option..");
		
		assertTrue(page.typeStatus(lStatus), "Unable to type status in text area..");
		
		
		if(!lPhotoPath.isEmpty()) {
			assertTrue(page.clickOnPhoto(), "Unable to click Photo tab button....");
			assertTrue(page.uploadImage(System.getProperty("user.dir")+lPhotoPath), "Unable to upload the image..");
		}
		
		//If Promote a Listing option
		if(lPromoteListing!=null && !lPromoteListing.isEmpty()) {
			assertTrue(page.clickOnPromoteListingTab(), "Unable to click Promote a listing tab button....");
			assertTrue(page.getPpPromoteListingForm().isChooseAListingForm(), "Promote Listing form is not visible...");
			assertTrue(page.getPpPromoteListingForm().selectListing(ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ListingsAddress)), "Unable to select listing from dropdown...");
			assertTrue(page.getPpPromoteListingForm().clickOnSelect(), "Unable to click on Select button...");
			assertTrue(page.getPpPromoteListingForm().isSelectButtonDisappeared(), "Select button is not disappeared...");
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			lStatus = lStatus.split("details! ")[0]+"details!";
		}
		
		if(lLinkToProperty!=null && !lLinkToProperty.isEmpty()) {
			assertTrue(page.clickOnLinkToURL(), "Unable to click Link to URL tab button....");
			assertTrue(page.setLinkToURL(lLinkToProperty),"Unable to set link in link field..");
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
		}
		
		assertTrue(page.selectFacebookPage(lFacebookPage), "Unable to select Facebook page from drop down ..");
		
		if(lPostSchedule.equalsIgnoreCase("Later")) {
//			lDate = getStartDateInFormat("");
			
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			assertTrue(page.clickOnScheduleLater(), "Unable to click on Schedule Later radio button..");
			
			scheduleLater(lDate,lTime);
			
			//Click on Post Later button
			assertTrue(page.clickOnPostLaterButton(), "Unable to click on Post Later button ..");
			assertTrue(page.isScheduleLaterPostCompleted(), "Scheduled Post Completed Success Message is not displayed ..");
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			//Verifying the Later has been scheduled or not
			assertTrue(page.selectNumberOfRecords("100"), "Unable to select total number of records to display per page..");
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			assertTrue(page.isUpcomingPostsSuccessful(lStatus,FrameworkConstants.FacebookIconImage,lDate, lTime), "Post not found in Upcoming Post results..");
			
			
//			forceLaterPost();
//			verifyLaterPost();
			
		}else if(lPostSchedule.equalsIgnoreCase("Recurring")) {
			
//			lDate = getStartDateInFormat("");
			lEndingDate = getStartDateInFormat(LocalDate.now().plusDays(7).toString());
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			assertTrue(page.clickOnScheduleRecurring(), "Unable to click on Schedule Recurring radio button..");
			
			scheduleRecurring(lDate,lTime,lEndingDate,lRepeatOnDays);
			
			//Click on Schedule a Recurring post
			assertTrue(page.clickOnPostRecurringButton(), "Unable to click on Post Recurring button ..");
			assertTrue(page.isScheduleLaterPostCompleted(), "Scheduled Post Completed Success Message is not displayed ..");
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			//Verifying the Later has been scheduled or not
			assertTrue(page.selectNumberOfRecords("100"), "Unable to select total number of records to display per page..");
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			assertTrue(page.isUpcomingRecurringPostsSuccessful(lStatus,FrameworkConstants.FacebookIconImage,lDate, lTime,lEndingDate,lRepeatOnDays), "Post not found in Upcoming Post results..");

			
//			String lNewFileToWrite = System.getProperty("environment").equalsIgnoreCase("prod")?"/resources/cache/posts-to-verify-prod.json":"/resources/cache/posts-to-verify-qa.json";
//			String lPreviousFileToWrite = System.getProperty("environment").equalsIgnoreCase("prod")?"/resources/cache/facebook-recurring-previous.json":"/resources/cache/facebook-recurring-previous-qa.json";
//
//			createCacheFile(lStatus,lNewFileToWrite ,lPreviousFileToWrite, lFacebookPage);
			
		}else {
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			assertTrue(page.clickOnPostNowButton(), "Unable to click on Post now button ..");
			assertTrue(page.isPostCompleted(), "Post Completed Success Message is not displayed ..");
			
			assertTrue(sideMenu.goToSubMenu("Posting History"), "Unable to click on Posting History button on side menu ..");
			
			assertTrue(postingHistoryPage.isPostingHistoryPage(), "Posting History Page is not opened ..");
			
			assertTrue(postingHistoryPage.isPostCompleted(lStatus), "The Post is not found on Posting History Page ..");
		}
		if(!lPostSchedule.equalsIgnoreCase("Now")) {
//			String lScheduleId = getScheduleId(lStatus,"Facebook");
//			forceLaterPost(lScheduleId);
//			Posts postObject = ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.PostObject);
//			verifyLaterPost(postObject.getPostID().toString());
			
			String lNewFileToWrite = getIsProd()?lFileToWriteProd:lFileToWriteStage;
			String lPreviousFileToWrite = getIsProd()?"/resources/cache/facebook-later-previous.json":"/resources/cache/facebook-later-previous-qa.json";
			createCacheFile(lStatus,lNewFileToWrite ,lPreviousFileToWrite, lFacebookPage);
		}
	}
	
	@Test
	@Parameters({"dataFileSocial"})
	public void testPostOnSocialPlatform(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		String lStatus =updateName(dataObject.optString("status"));
		String lPhotoPath = dataObject.optString("image_path");
		String lPostSchedule=dataObject.optString("post_schedule"); 
		String lFacebookPage=dataObject.optString("facebook_page");
		String lDate=getTodaysDate();//socialData.getStartDate();
		String lTime=getPSTTime();//socialData.getTime();
		String lEndingDate=getTomorrowsDate();//socialData.getEndDate();
		String lRepeatOnDays=getCurrentAndNextDayOfTheWeek();//socialData.getRepeatDays();
		String lPromoteListing = dataObject.optString("promote_listing");
		String lLinkToProperty = dataObject.optString("property_link");
		String lPlatform = dataObject.optString("platform");
		String lPlatformIcon = "";
		getPage("/content/marketing/social");
		
		assertTrue(page.isSocialPage(), "Social Posting page is not displayed..");
//		String lNewFileToWrite = getIsProd()?"/resources/cache/facebook-later.json":"/resources/cache/posts-to-verify-qa.json";
		String lFileToWriteProd = "/resources/cache/posts-to-verify-prod.json";
		String lFileToWriteStage = "/resources/cache/posts-to-verify-qa.json";
		String lNewFileToWrite = getIsProd()?lFileToWriteProd:lFileToWriteStage;

	
		
		switch(lPlatform){

		case "Twitter":
			//Uncheck Facebook option
			assertTrue(page.unCheckFacebookOption(), "Unable to check Facebook option..");
			assertTrue(page.checkTwitterOption(true), "Unable to check Twitter checkbox..");
			lPlatformIcon = FrameworkConstants.TwitterIconImage;
			assertTrue(page.typeStatus(lStatus), "Unable to type status in text area..");
			break;

		case "YouTube":
			lStatus = ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.ListingsAddress);
			assertTrue(page.checkYoutubeOption(), "Unable to check Youtube checkbox..");
			assertTrue(page.getPpPromoteListingForm().isChooseAListingForm(), "Promote Listing form is not visible after clicking youtube checkbox..");
			assertTrue(page.getPpPromoteListingForm().selectListingYoutube(lStatus), "Unable to select listing from dropdown..");
			assertTrue(page.getPpPromoteListingForm().isSelectButtonDisappeared(), "Listing is not selected..");
			lPlatformIcon = FrameworkConstants.YoutubeIconImage;
			break;

		case "LinkedIn":
			//Uncheck Facebook option
			assertTrue(page.unCheckFacebookOption(), "Unable to check Facebook option..");
			assertTrue(page.checkLinkedInOption(true), "Unable to check LinkedIn checkbox..");
			lPlatformIcon = FrameworkConstants.LinkedInIconImage;
			assertTrue(page.typeStatus(lStatus), "Unable to type status in text area..");
			break;

		case "Facebook":
			lStatus = ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.ListingsAddress);
			assertTrue(page.clickOnPromoteListingVideoTab(), "Unable to click on Promote Listing video button..");
			assertTrue(page.getPpPromoteListingForm().isChooseAListingForm(), "Promote Listing form is not visible after clicking youtube checkbox..");
			assertTrue(page.getPpPromoteListingForm().selectListingYoutube(lStatus), "Unable to select listing from dropdown..");
			assertTrue(page.getPpPromoteListingForm().isSelectButtonDisappeared(), "Listing is not selected..");
			assertTrue(page.selectFacebookPage(lFacebookPage), "Unable to select Facebook page from drop down ..");
			lPlatformIcon = FrameworkConstants.FacebookIconImage;
			break;

		default:
			break;
		}

		if(!lPhotoPath.isEmpty()) {
			assertTrue(page.clickOnPhoto(), "Unable to click Photo tab button....");
			assertTrue(page.uploadImage(System.getProperty("user.dir")+lPhotoPath), "Unable to upload the image..");
		}
		
		//If Promote a Listing option
		if(lPromoteListing!=null && !lPromoteListing.isEmpty()) {
			assertTrue(page.clickOnPromoteListingTab(), "Unable to click Promote a listing tab button....");
			assertTrue(page.getPpPromoteListingForm().isChooseAListingForm(), "Promote Listing form is not visible...");
			assertTrue(page.getPpPromoteListingForm().selectListing(ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ListingsAddress)), "Unable to select listing from dropdown...");
			assertTrue(page.getPpPromoteListingForm().clickOnSelect(), "Unable to click on Select button...");
			assertTrue(page.getPpPromoteListingForm().isSelectButtonDisappeared(), "Select button is not disappeared...");
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			lStatus = lStatus.split("details! ")[0]+"details!";
		}
		
		if(lLinkToProperty!=null && !lLinkToProperty.isEmpty()) {
			assertTrue(page.clickOnLinkToURL(), "Unable to click Link to URL tab button....");
			assertTrue(page.setLinkToURL(lLinkToProperty),"Unable to set link in link field..");
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
		}
		
		if(lPostSchedule.equalsIgnoreCase("Later")) {
			lDate = getStartDateInFormat("");
			
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			assertTrue(page.clickOnScheduleLater(), "Unable to click on Schedule Later radio button..");
			
			scheduleLater(lDate,lTime);
			
			//Click on Post Later button
			assertTrue(page.clickOnPostLaterButton(), "Unable to click on Post Later button ..");
			assertTrue(page.isScheduleLaterPostCompleted(), "Scheduled Post Completed Success Message is not displayed ..");
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			//Verifying the Later has been scheduled or not
			assertTrue(page.selectNumberOfRecords("100"), "Unable to select total number of records to display per page..");
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			assertTrue(page.isUpcomingPostsSuccessful(lStatus,lPlatformIcon,lDate, lTime), "Post not found in Upcoming Post results..");

			String lScheduleId = getScheduleId(lStatus,lPlatform);
			forceLaterPost(lScheduleId);
			Posts postObject = ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.PostObject);
			writePojoToJsonFile(postObject,lNewFileToWrite);
//			verifyLaterPost(postObject.getPostID().toString());
			
		}else if(lPostSchedule.equalsIgnoreCase("Recurring")) {
			
			lDate = getStartDateInFormat("");
			lEndingDate = getStartDateInFormat(LocalDate.now().plusDays(7).toString());
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			assertTrue(page.clickOnScheduleRecurring(), "Unable to click on Schedule Recurring radio button..");
			
			scheduleRecurring(lDate,lTime,lEndingDate,lRepeatOnDays);
			
			//Click on Schedule a Recurring post
			assertTrue(page.clickOnPostRecurringButton(), "Unable to click on Post Recurring button ..");
			assertTrue(page.isScheduleLaterPostCompleted(), "Scheduled Post Completed Success Message is not displayed ..");
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			//Verifying the Later has been scheduled or not
			assertTrue(page.selectNumberOfRecords("100"), "Unable to select total number of records to display per page..");
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			assertTrue(page.isUpcomingRecurringPostsSuccessful(lStatus,lPlatformIcon,lDate, lTime,lEndingDate,lRepeatOnDays), "Post not found in Upcoming Post results..");
			
			HibernateUtil.setSessionFactoryEmpty();
			String lScheduleId = getScheduleId(lStatus,lPlatform);
			forceLaterPost(lScheduleId);
			Posts postObject = ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.PostObject);
			writePojoToJsonFile(postObject,lNewFileToWrite);
//			verifyLaterPost(postObject.getPostID().toString());
			
		}else {
			assertTrue(page.isLoaderDisappeared(), "Ajax loader is not disappeared ..");
			assertTrue(page.clickOnPostNowButton(), "Unable to click on Post now button ..");
			if(lPlatform.equalsIgnoreCase("YouTube") || lPlatform.equalsIgnoreCase("Facebook")) {
				assertTrue(page.isYoutubePostCompleted(), "Your Video is being processed Message is not displayed ..");
			}else {
				assertTrue(page.isPostCompleted(), "Post Completed Success Message is not displayed ..");
			}
			assertTrue(sideMenu.goToSubMenu("Posting History"), "Unable to click on Posting History button on side menu ..");

			assertTrue(postingHistoryPage.isPostingHistoryPage(), "Posting History Page is not opened ..");

			if(lPlatform.equalsIgnoreCase("Youtube") || lPlatform.equalsIgnoreCase("Facebook")) {
				assertTrue(postingHistoryPage.isYoutubePostCompleted(lStatus,lPlatform), "The Post is not found on Posting History Page ..");

			}else {
				assertTrue(postingHistoryPage.isPostCompleted(lStatus), "The Post is not found on Posting History Page ..");
			}
		}
	}
	
	private void verifyLaterPost(String pPostId) {
		HibernateUtil.setSessionFactoryEmpty();
	
		Posts postObj = getEnvironment().getPostByParentPostId(pPostId);

		assertTrue(new DBHelperMethods(getEnvironment()).isPostSuccessful(postObj), "Post was not successful");
		
	}

	private String getScheduleId(String pStatus, String pPlatform) {
		Posts postObj = null;
		String forLikeQuery = pStatus.split(" ")[pStatus.split(" ").length-1];
		switch(pPlatform) {
		case "Twitter":
			postObj = getEnvironment().getPostByTwitterStatus(forLikeQuery);
			break;

		case "LinkedIn":
			postObj = getEnvironment().getPostByLinkedInStatus(forLikeQuery);
			break;

		case "YouTube":
			postObj = getEnvironment().getPostByYoutubeStatus(forLikeQuery);
			break;

		default:
			break;
		}
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(), ModuleCacheConstants.PostObject, postObj);
		return postObj.getScheduleID().toString();
	}

	/*
	 * This is DB test, which will verify that post was successfully posted on the
	 * social platform. It uses hard coded post id.
	 */
	
	private void forceLaterPost() {
		PPForceExecuteSchedulePost forceSchedulePost = new PPForceExecuteSchedulePost(driver);
		String lNewFileToWrite = System.getProperty("environment").equalsIgnoreCase("prod")?"/resources/cache/facebook-later.json":"/resources/cache/facebook-later-qa.json";
		String lScheduleId = getDataFile(lNewFileToWrite).opt("scheduleID").toString();
		driver.navigate().to("https://propertypulse.z57.com/dev/force-execute-scheduled-post/"+lScheduleId);
		assertTrue(forceSchedulePost.isExecutingSchedulePage(), "Force Schedule page was not found");
		assertTrue(forceSchedulePost.getStatus().equalsIgnoreCase("1"), "Force Schedule was not succcessful. Status code is "+forceSchedulePost.getStatus());
		assertTrue(forceSchedulePost.getResultMessage().contains("success"), "Force Schedule was not succcessful. Result message is "+forceSchedulePost.getResultMessage());
	}

	@Test
	public void testVerificationOfReccurringPosts() {
//		getPage();

		String lNewFileToWrite = System.getProperty("environment").equalsIgnoreCase("prod")?"/resources/cache/facebook-recurring.json":"/resources/cache/facebook-recurring-qa.json";

		String lParentPostId = "";

		lParentPostId = getDataFile(lNewFileToWrite).opt("postID").toString();

		Posts postObj = getEnvironment().getPostByParentPostId(lParentPostId);

		assertTrue(new DBHelperMethods(getEnvironment()).isPostSuccessful(postObj), "Post was not successful");
	}
	
	@Test
	public void testVerificationOfLaterPosts() {
		
		String lNewFileToWrite = System.getProperty("environment").equalsIgnoreCase("prod")?"/resources/cache/facebook-later.json":"/resources/cache/facebook-later-qa.json";

		String lParentPostId = "";

		lParentPostId = getDataFile(lNewFileToWrite).opt("postID").toString();

		Posts postObj = getEnvironment().getPostByParentPostId(lParentPostId);

		assertTrue(new DBHelperMethods(getEnvironment()).isPostSuccessful(postObj), "Post was not successful");
	}
	
	private void verifyLaterPost() {
		HibernateUtil.setSessionFactoryEmpty();
		String lNewFileToWrite = System.getProperty("environment").equalsIgnoreCase("prod")?"/resources/cache/facebook-later.json":"/resources/cache/facebook-later-qa.json";

		String lParentPostId = "";

		lParentPostId = getDataFile(lNewFileToWrite).opt("postID").toString();

		Posts postObj = getEnvironment().getPostByParentPostId(lParentPostId);

		assertTrue(new DBHelperMethods(getEnvironment()).isPostSuccessful(postObj), "Post was not successful");
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
	
	private void createCacheFile(String pStatus, String pNewFileFile, String pPreviousFile, String pFacebookPage) {
		String forLikeQuery = pStatus.split(" ")[pStatus.split(" ").length-1];
		//Writing cache files for verification of recurring posts are success
		JSONObject lCurrentObject = getDataFile(pNewFileFile);
		writeJsonToFile(pPreviousFile, lCurrentObject);
		
		Posts postObj = getEnvironment().getPostByFacebookStatus(forLikeQuery,pFacebookPage);
		writePojoToJsonFile(postObj,pNewFileFile);
		
	}
	private String getStartDateInFormat(String pDate) {
		String lDate = "";
		if(pDate.isEmpty()) {
			LocalDate today = LocalDate.now();
			LocalDate tomorrow = today.plusDays(1);
			lDate = tomorrow.toString();
		}else {
			lDate = pDate;
		}
		String tempDate[] = lDate.split("-");
		lDate = tempDate[1]+"/"+tempDate[2]+"/"+tempDate[0];
		return lDate;
	}
	
	private void forceLaterPost(String pScheduleId) {
		PPForceExecuteSchedulePost forceSchedulePost = new PPForceExecuteSchedulePost(driver);
		driver.navigate().to("https://propertypulse.z57.com/dev/force-execute-scheduled-post/"+pScheduleId);
		assertTrue(forceSchedulePost.isExecutingSchedulePage(), "Force Schedule page was not found");
		assertTrue(forceSchedulePost.getStatus().equalsIgnoreCase("1"), "Force Schedule was not succcessful. Status code is "+forceSchedulePost.getStatus());
		assertTrue(forceSchedulePost.getResultMessage().contains("success"), "Force Schedule was not succcessful. Result message is "+forceSchedulePost.getResultMessage());
	}
}
