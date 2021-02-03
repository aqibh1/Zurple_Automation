/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.backoffice.ads.ZBOCreateAdPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;

/**
 * @author adar
 *
 */
public class ZBOCreateAdPageTest extends PageTest{

	private JSONObject dataObject;
	private ZBOCreateAdPage page;
	private WebDriver driver;
	private String lAd_Type = "";
	private String l_heading = "";
	private String l_desc = "";
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreateAdPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreateAdPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl, boolean pSetupForcefully) {
		if(page==null && !pSetupForcefully) {
			driver = getDriver();
			page = new ZBOCreateAdPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}else if(page!=null && pSetupForcefully){
			driver = getDriver();
			page = new ZBOCreateAdPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	@BeforeTest
	public void backOfficeLogin() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}
	@Test
	public void testVerifyCustomAdSectionIsDisplayed() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isCreateAdPage(), "Create Ad Page is not visible..");
		assertTrue(page.isCreateAdStep1Visible(), "Create Ad Page Step 1 is not visible..");
		assertTrue(page.isCustomAdsHeadingDisplayed(), "Create Custom Ads heading is not displayed..");
	}
	@Test
	public void testVerifyPromoteListingHeadingIsDisplayed() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isPromoteListingHeadingIsVisible(), "Promote Listing heading is not displayed..");
	}
	@Test
	public void testVerifyCorrectTextIsDisplayed() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isCorrectTextDisplayed(), " 'Get more exposure, interest and engagement for your listing' is not displayed..");
	}
	@Test
	public void testVerifyHomeIconIsDisplayed() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isHomeIconDisplayedInCreateAdBox(), "Home Icon inside Create Custom Ad box is not displayed..");
	}
	@Test
	public void testVerifyCustomAdButtonIsDisplayed() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isCreateCustomAdButtonIsDisplayed(), "Create Custom Ad button is not displayed..");
	}
	
	@Test
	public void testVerifyCreateAdBoxIsBouncing() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isCreateCustomBoxBouncing(), "Create Custom Ad box is not bouncing on mouse hover..");
	}
	@Test
	@Parameters({"dataFile"})
	public void testCreateAd(String pDataFile) throws ParseException {
		dataObject = getDataFile(pDataFile);
		lAd_Type = dataObject.optString("ad_type");
		
		getPage("/create-ad/step-one");
		assertTrue(page.isCreateAdPage(), "Create Ad Page is not visible..");
		assertTrue(page.isCreateAdStep1Visible(), "Create Ad Page Step 1 is not visible..");
		switch(lAd_Type) {
		case "Custom":
			assertTrue(page.clickOnCustomAdButton(), "Unable to click on Custom Ad button..");
			assertTrue(page.getSelectListingAlert().isSelectListingAlert(), "Select Listing Alert is not visible..");
			assertTrue(!page.getSelectListingAlert().selectTheListingFromDropdown().isEmpty(), "Unable to select listing from drop down");
			assertTrue(page.getSelectListingAlert().clickOnOkButton(), "Unable to click on OK button");
			break;
		case "Quick":
			assertTrue(page.isStep1SelectQuickAdHeading(), "Step1 quick heading visible ..");
			assertTrue(page.isSlideShowVisible(), "Step1 slide show ad is not visible ..");
			l_heading = page.getFBAdTitleStep1();
			l_desc = page.getFBAdDescStep1().split("\n")[0].split(",")[0];
			assertTrue(page.clickOnSelectButtonStep1(), "Unable to click on select button on step 1 ..");
		
			break;
		}
		createAdStep2Section1();
		createAdStep2Section2();
		createAdStep2Section3();
		createAdStep2Section4();
	
	}
	private void createAdStep2Section1() {
//		assertTrue(page.isCreateAdStep2Visible(), "Step2 is not visible..");
		assertTrue(page.isStep2Section1SelectAnAdHeadingVisible(), "Step 2 Section 1 heading is not visible..");
		assertTrue(page.isListingHeadingVisible(), "Listing title is not visible..");
		assertTrue(page.isStep1Checked(), "Step1 is not checked..");
		assertTrue(page.isStep2Section2HeadingVisible(), "Step2 heading is not visible..");
	}
	private void createAdStep2Section2() {
		if(lAd_Type.equalsIgnoreCase("Custom")) {
			String lSite = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url").split("www.")[1];

			l_heading = page.isAdHeadingPopulated();
			l_desc = page.isAdDescPopulated();
			String ld_heading = dataObject.optString("ad_heading");
			String ld_desc = dataObject.optString("ad_desc");;

			assertTrue(!l_heading.isEmpty(), "Step2 ad heading is not populated..");
			assertTrue(!l_desc.isEmpty(), "Step2 ad description is not visible..");

			if(!ld_heading.isEmpty()) {
				assertTrue(page.typeHeading(ld_heading), "Unable to type heading ..");
			}else {
				ld_heading = l_heading;
			}
			if(!ld_desc.isEmpty()) {
				assertTrue(page.typeDesc(ld_desc), "Unable to type description ..");
			}else {
				ld_desc = l_desc;
			}
			assertTrue(page.selectSite(lSite), "Unable to select the site from dropdown..");		
		}
		
		//Verifications of section 2
		assertTrue(page.verifyAdHeadline(l_heading), "Unable to verify ad heading..");
		assertTrue(page.verifyAdDesc(l_desc), "Unable to verify ad description..");
		assertTrue(page.verifyFbAdPreviewDetails(), "Unable to verify Facebook ad preview details..");
		assertTrue(page.verifyInstagramAdPreviewDetails(), "Unable to verify Instagram ad preview details..");
		if(lAd_Type.equalsIgnoreCase("Custom")) {
			assertTrue(page.clickOnSelectButton(), "Unable to click on select button..");
		}
		
		assertTrue(page.verifyAdURLIsCorrect(), "Unable to verify ad URL..");
		assertTrue(page.isSection2Checked(), "Step 2 Section 2 is not checkedL..");
	}
	private void createAdStep2Section3() {
		String ld_taregetReachAmount = dataObject.optString("ad_amount");
		assertTrue(page.isDefaultCitySelected(), "Default city is not selected for the ad ..");
		String l_defaultCity = page.getDefaultCity();
		assertTrue(page.selectAdTaregetReach(ld_taregetReachAmount), "Unable to select target reach amount ..");
		assertTrue(page.clickOnNextStepButton(), "Unable to click on Next button ..");
		assertTrue(page.verifyAdCitySection3(l_defaultCity), "Unable to select target reach amount ..");
		assertTrue(page.verifyAdReachSection3(ld_taregetReachAmount), "Unable to select target reach amount ..");
		assertTrue(page.isSection3Checked(), "Section 3 is not checked ..");
		assertTrue(page.isSection3EditButtonEnabled(), "Section 3 edit button is not enabled ..");
		
	}
	private void createAdStep2Section4() throws ParseException {
		assertTrue(page.isSection4Visible(), "Section 4 heading is not visible  ..");
		assertTrue(page.verifyStartEndAndRenewalDate(), "Unable to verify start, end and renewal date  ..");
		assertTrue(page.clickOnTermsAndCond(), "Unable to click on terms and condition ..");
		assertTrue(page.clickOnPlaceAdButton(), "Unable to click on Place Ad button ..");
	}
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
