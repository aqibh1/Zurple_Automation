/**
 * 
 */
package com.zurple.backoffice.admin;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.zurple.admin.ZACreateSellerLeadsAdPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;

/**
 * @author darrraqi
 *
 */
public class ZASellerLeadsAdPageTest extends PageTest{
	private WebDriver driver;
	private ZACreateSellerLeadsAdPage page;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZACreateSellerLeadsAdPage(driver);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZACreateSellerLeadsAdPage(driver);
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
	
	public void testCreateAndVerifyLeadFormSellerLeadAd(String pDataFile) {
		getPage("/admin/create-sl-ad");
		assertTrue(page.isSellerLeadAdPage(), "Seller lead ad page is not displayed..");
		JSONObject dataObject = getDataFile(pDataFile);
		fillSellerLeadForm(dataObject);
		assertTrue(page.clickOnSubmitButton(),"Unable to click on submit button..");
		assertTrue(page.isSuccessMessageVisible(), "Success message for ad is not visible");
	}
	
	private void fillSellerLeadForm(JSONObject pDataObject) {
		assertTrue(page.typeAndSelectPackage(pDataObject.optString("package_id")), "Unable to select the package");
		assertTrue(page.typeAndSelectAdmin(pDataObject.optString("admin_id")), "Unable to select the package");
		assertTrue(page.typeBudget(pDataObject.optString("budget")), "Unable to select the package");
		assertTrue(page.typeZipCode(pDataObject.optString("zip_code")), "Unable to select the package");
		assertTrue(page.typeAdCity(pDataObject.optString("main_city")), "Unable to select the package");
		assertTrue(page.clickAndSelectAdType(pDataObject.optString("ad_type")), "Unable to select the package");
		assertTrue(page.clickAndSelectCarousel(pDataObject.optString("cma_carousel")), "Unable to select the package");
		assertTrue(page.clickAndSelectCMAVideo(pDataObject.optString("cma_video")), "Unable to select the package");
		assertTrue(page.clickAndSelectDownloadCarousel(pDataObject.optString("download_carousel")), "Unable to select the package");
	}
	
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
