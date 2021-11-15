/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;

/**
 * @author adar
 *
 */
public class ZBOBillingPageTest extends PageTest{

	private ZBOBillingPage page;
	private WebDriver driver;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOBillingPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOBillingPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;		
	}
	
	@BeforeTest
	public void backOfficeLogin() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}
	
	/**
	 * Verify Add Payment Plan button is visible on Billing Page
	 * 47375
	 */
	@Test
	public void testIsAddPaymentPlanButtonVisibleOnBillingPage() {
		getPage("/billing");
		assertTrue(page.isBilingPage(), "Billing Page is not visible..");
		assertTrue(page.isAddPaymentPlanButtonVisible(), "Billing Page is not visible..");
	}
	
	/**
	 * Verify click on 'Add Payment Plan' button takes user to add credit card form
	 * 47376
	 */
	@Test
	public void testVerifyAddPaymentPlanButtonTakesUserToCreditCardForm() {
		getPage("/billing");
		assertTrue(page.clickOnAddPaymentPlan(), "Unable to click on Add payment plan button..");
		assertTrue(page.getCreditCardForm().isAddCreditCardForm(), "Credit Card form is not opened..");
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}


}
