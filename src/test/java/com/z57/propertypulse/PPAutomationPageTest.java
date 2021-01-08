/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class PPAutomationPageTest extends PageTest{
	private WebDriver driver;
	private PPAutomationPage page;
	private PPForceExecuteSchedulePost devToolPage;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPAutomationPage(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new PPAutomationPage(driver);
			devToolPage = new PPForceExecuteSchedulePost(driver);
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
	public void testConfigureAutomationSettings() {
		getPage("/automation");
		String lFacebookIdx = "Enabled";
		String lFacebookIdxUpdated = "Disabled";
		
		assertTrue(page.isAutomationSettingsPage(),"Automation page is not visible");
		assertTrue(page.selectFacebookPage("Aqib Automated Testing"),"Unable to select facebook page..");

		assertTrue(page.selectFacebookIdxDropdown(lFacebookIdx),"Unable to select Facebook IDX post option..");
		clickSaveButton();
		assertTrue(verifyFacebookIDX(lFacebookIdx),"Unable to verify Facebook IDX option "+lFacebookIdx);
		assertTrue(page.selectFacebookIdxDropdown(lFacebookIdxUpdated),"Unable to select Facebook IDX post option..");
		clickSaveButton();
		assertTrue(verifyFacebookIDX(lFacebookIdxUpdated),"Unable to verify Facebook IDX option "+lFacebookIdx);
	
	}
	
	@Test
	@Parameters({"dataFileAutomation"})
	public void testEnableAndVerifyIdxListingPosts(String pDataFile) {
		getPage("/automation");
		JSONObject dataObject = getDataFile(pDataFile);
		
		assertTrue(page.isAutomationSettingsPage(),"Automation page is not visible");
		assertTrue(page.selectFacebookPage(dataObject.optString("facebook_page")),"Unable to select facebook page..");
		assertTrue(page.selectFacebookIdxDropdown(dataObject.optString("fb_idx_listing_post")),"Unable to select Facebook IDX post option..");
		assertTrue(page.selectTwitterIdxDropdown(dataObject.optString("tw_idx_listing_post")),"Unable to select Twitter IDX post option..");
		assertTrue(page.typeZip(dataObject.optString("zip")),"Unable to type zip..");
		assertTrue(page.selectMinPrice(dataObject.optString("minimum_price")),"Unable to select minimum price..");
		assertTrue(page.selectMaxPrice(dataObject.optString("maximum_price")),"Unable to select maximum price..");
		
		clickSaveButton();
		triggerIdxPosts();
		
//		assertTrue(devToolPage.isListingDetailsPage(),"Dev tool page is not visible..");
		assertTrue(isValidPrice(dataObject.optString("minimum_price"),dataObject.optString("maximum_price")), "Listing price is not valid");
		assertTrue(devToolPage.getScheduledPostsStatus().equalsIgnoreCase("1"), "Force Schedule was not succcessful. Status code is "+devToolPage.getScheduledPostsStatus());
		
		String resultMessage = devToolPage.getStatusMessage();
		
		assertTrue(isIdxFacebookPostSuccessful(resultMessage), "IDX Facebook post is not successful");
		assertTrue(isIdxTwitterPostSuccessful(resultMessage), "IDX Twitter post is not successful");
	}
	
	@Test
	public void testEnableAndVerifyListingPosts() {
		getPage("/automation");
		
		assertTrue(page.isAutomationSettingsPage(),"Automation page is not visible");
		assertTrue(page.selectFacebookPage("Aqib Automated Testing"),"Unable to select facebook page..");
		ActionHelper.staticWait(3);
		assertTrue(page.selectFacebookListingPostDropdown("Enabled"),"Unable to select Facebook post option..");
		ActionHelper.staticWait(3);
		assertTrue(page.selectTwitterListingPostDropdown("Enabled"),"Unable to select Twitter post option..");
	
		clickSaveButton();
	}
	
	private boolean isIdxTwitterPostSuccessful(String resultMessage) {
		return resultMessage.contains("Posted to Twitter");
	}
	private boolean isIdxFacebookPostSuccessful(String resultMessage) {
		return resultMessage.contains("Posted to Facebook");
	}
	private boolean isValidPrice(String pMinPrice, String pMaxPrice) {
		boolean isValidPrice = false;
		int lMinPrice = Integer.parseInt(pMinPrice.replace("$", "").replace(",", ""));
		int lMaxPrice = Integer.parseInt(pMaxPrice.replace("$", "").replace(",", ""));
		int lListingPrice = Integer.parseInt(devToolPage.getListingPrice());
		if(lListingPrice>=lMinPrice && lListingPrice<=lMaxPrice) {
			isValidPrice = true;
		}
		return isValidPrice;
	}
	private void triggerIdxPosts() {
		//https://propertypulse.z57.com/dev/manual-idx-accelerator/xxxx/force
		String lUserAccountId = EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_account_id");
		String lPPBaseUrl = EnvironmentFactory.configReader.getPropertyByName("z57_pp_base_url");
		String lIdxForceUrl = lPPBaseUrl+"/dev/manual-idx-accelerator/"+lUserAccountId+"/force";
		driver.navigate().to(lIdxForceUrl);
		
	}
	private boolean verifyFacebookIDX(String lFacebookIdx) {
		ActionHelper.RefreshPage(driver);
		return page.isFacebookIdxPostOptionEnabled(lFacebookIdx);
	}
	private void clickSaveButton() {
		assertTrue(page.clickOnSaveButton(),"Unable to save settings");
	}

}
