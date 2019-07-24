package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.utility.ActionHelper;

public class PPSocialAutoPosterPageTest extends PageTest{
	
	private WebDriver driver;
	private PPSocialAutoPoster page;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPSocialAutoPoster(driver);	
		}
		return page;
	}
	
	private AbstractPage getPage(String pURL) {
		if(page == null){
			driver = getDriver();
			page = new PPSocialAutoPoster(driver);
			page.setUrl(pURL);
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	@Parameters({"dataFileAutoPoster"})
	public void testCreateSocialAutoPost(String pDataFile) {
		JSONObject dataObject = getDataFile(pDataFile);
		String lPostType = dataObject.optString("post_type");
		String lPostTitle = "";
		getPage("/content/marketing/poster");
		assertTrue(page.isSocialAutoPosterPage(),"Social Auto Poster page is not available..");
		assertTrue(page.clickOnNewPostButton(),"Unable to click Add New Post button..");
		assertTrue(page.clickOnInputRadioButton(lPostType),"Unable to select radio option..");
		switch(lPostType) {
		
		case "Promote Website":
			lPostTitle = updateName(dataObject.optString("website_name"));
			assertTrue(page.typeWebSiteName(lPostTitle), "Unable to type website name..");
			assertTrue(page.typeWebSiteUrl(dataObject.optString("website_url")), "Unable to type website URL..");
			assertTrue(page.uploadImage(dataObject.optString("image_path")), "Unable to upload images..");
			assertTrue(page.typeWebsiteMessage(dataObject.optString("website_message")), "Unable to type website message..");
			break;

		case "Promote Tab":
			break;

		case "RSS Feed":
			break;
			
		default:
			break;
		}
		
		assertTrue(page.selectFacebookPage(dataObject.optString("facebook_page")),"Unable to select facebook page..");
		assertTrue(page.selectPostingSchedule(dataObject.optString("posting_schedule")),"Unable to select posting schedule..");
		assertTrue(page.selectDays(dataObject.optString("posting_days").split(",")),"Unable to select posting days..");
		assertTrue(page.selectTime(dataObject.optString("posting_time").split(",")),"Unable to select posting time..");
		assertTrue(page.clickSaveButton(),"Unable click on Save button..");
		assertTrue(page.verifyAutoPost(lPostTitle),"Unable click on Save button..");
		
	}

}
