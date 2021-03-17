/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;

/**
 * @author darrraqi
 *
 */
public class ZBOSendReportPageTest extends PageTest{

	private WebDriver driver;
	private ZBOSendCMAReportPage page;
	private JSONObject dataObject;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOSendCMAReportPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOSendCMAReportPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl, boolean pSetupForcefully) {
		if(page==null && !pSetupForcefully) {
			driver = getDriver();
			page = new ZBOSendCMAReportPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}else if(page!=null && pSetupForcefully){
			driver = getDriver();
			page = new ZBOSendCMAReportPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	
	@Override
	public void clearPage() {
	
	}
	
	@BeforeTest
	public void backOfficeLogin() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLeadNameIsDisplayedOnCMAReportPage(String pDataFile) {
		dataObject = getDataFile(pDataFile);
		String l_lead_id = getIsProd()?dataObject.optString("prod_lead_id"):dataObject.optString("stage_lead_id");
		String l_lead_name = getIsProd()?dataObject.optString("prod_lead_name"):dataObject.optString("stage_lead_name");
		getPage("/cma/email/lead/"+l_lead_id,true);
		assertTrue(page.isLeadNameVisible(l_lead_name), "Lead name is not visible..");
	}
	
	@Test
	public void testVerifyLeadNameIsDisplayedOnCMAReportPage() {
		getPage();
		assertTrue(page.verifyPropertyHeadingIsVisible(), "Property Heading is not visible..");
	}
	@Test
	public void testVerifyAllFieldsAreDisplayedInPropertySection() {
		getPage();
		assertTrue(page.isAddressInputVisible(), "Address Input is not visible..");
		assertTrue(page.isCityInputVisible(), "City Input is not visible..");
		assertTrue(page.isStateInputVisible(), "State Input is not visible..");
		assertTrue(page.isZipInputVisible(), "Zip Input is not visible..");
		assertTrue(page.isBedsInputVisible(), "Bed Input is not visible..");
		assertTrue(page.isBathsInputVisible(), "Bath Input is not visible..");
		assertTrue(page.isMinPriceInputVisible(), "Minimum price Input is not visible..");
		assertTrue(page.isMaxPriceInputVisible(), "Maximum Input is not visible..");
	}
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
