/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
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
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreateAdPage(driver);
			page.setUrl("");
		}
		return page;
	}

	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreateAdPage(driver);
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
	@Parameters({"dataFile"})
	public void testCreateAd(String pDataFile) {
		dataObject = getDataFile(pDataFile);
		String lAd_Type = "Custom";//dataObject.optString("ad_type");
		String lSite = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url").split("www.")[1];
		getPage("/create-ad/step-one");
		assertTrue(page.isCreateAdPage(), "Create Ad Page is not visible..");
		assertTrue(page.isCreateAdStep1Visible(), "Create Ad Page Step 1 is not visible..");
		switch(lAd_Type) {
		case "Custom":
			assertTrue(page.clickOnCustomAdButton(), "Unable to click on Custom Ad button..");
			assertTrue(page.getSelectListingAlert().isSelectListingAlert(), "Select Listing Alert is not visible..");
			assertTrue(page.getSelectListingAlert().selectTheListingFromDropdown(), "Unable to select listing from drop down");
			break;
		case "Quick":
			break;
		}
		assertTrue(page.isCreateAdStep2Visible(), "Step2 is not visible..");
		assertTrue(page.isStep1Checked(), "Step1 is not checked..");
		assertTrue(page.isStep2HeadingVisible(), "Step2 heading is not visible..");
		assertTrue(page.isAdHeadingPopulated(), "Step2 ad heading is not populated..");
		assertTrue(page.isAdDescPopulated(), "Step2 ad description is not visible..");
		assertTrue(page.selectSite(lSite), "Unable to select the site from dropdown..");
		assertTrue(page.verifyFbAdPreviewDetails(), "Unable to verify Facebook ad preview details..");
		assertTrue(page.verifyInstagramAdPreviewDetails(), "Unable to verify Instagram ad preview details..");
		assertTrue(page.clickOnSelectButton(), "Unable to click on select button..");
	}

}
