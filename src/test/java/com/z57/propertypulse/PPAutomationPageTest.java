/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class PPAutomationPageTest extends PageTest{
	private WebDriver driver;
	private PPAutomationPage page;
	
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
		assertTrue(page.selectFacebookIdxDropdown(lFacebookIdx),"Unable to select Facebook IDX post option..");
		clickSaveButton();
		assertTrue(verifyFacebookIDX(lFacebookIdx),"Unable to verify Facebook IDX option "+lFacebookIdx);
		assertTrue(page.selectFacebookIdxDropdown(lFacebookIdxUpdated),"Unable to select Facebook IDX post option..");
		clickSaveButton();
		assertTrue(verifyFacebookIDX(lFacebookIdxUpdated),"Unable to verify Facebook IDX option "+lFacebookIdx);
	}
	private boolean verifyFacebookIDX(String lFacebookIdx) {
		ActionHelper.RefreshPage(driver);
		return page.isFacebookIdxPostOptionEnabled(lFacebookIdx);
	}
	private void clickSaveButton() {
		assertTrue(page.clickOnSaveButton(),"Unable to save settings");
	}

}
