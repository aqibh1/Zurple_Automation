/**
 * 
 */
package com.z57.site.v1;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.data.z57.SaveSearchFormData;

/**
 * @author adar
 *
 */
public class HomePageTest extends PageTest{
	
	WebDriver driver;
	private HomePage page;

	@Override
	public Page getPage() {
		if(page == null){
			driver =getDriver();
			page = new HomePage(driver);
		}
		return page;
	}
	public Page getPage(String pUrl) {
		if(page == null) {
			driver=getDriver();
			page = new HomePage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);			
		}
		return page;
	}
	
	 @Test
	 @Parameters({"registerLeadData"})
	 public void testRegisterLead(String pDataFile1) {
		 getPage("/idx");
		 
		 SaveSearchFormData saveListingFormData = new SaveSearchFormData(pDataFile1).getEmailListingData();

		 String lLeadName = updateName(saveListingFormData.getLeadName());
		 String lLeadEmail = updateEmail(saveListingFormData.getLeadEmail());
		 String lLeadPhone = saveListingFormData.getLeadPhoneNumber();
		
		 ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(), ModuleCacheConstants.RegisterFormLeadEmail, lLeadEmail);
		 
		 HomeSearchPage homeSearchPage = new HomeSearchPage(driver);
		 assertTrue(homeSearchPage.isHomeSearchPage(), "Home Search Page is not visible");
		 assertTrue(homeSearchPage.clickOnSignInButton(), "Unable to click on Sign In button");
		 
		 assertTrue(page.getRegisterUserForm().isRegisterForm(), "Registeration form is not visible..");
		 assertTrue(page.getRegisterUserForm().typeLeadName(lLeadName), "Unable to type lead name..");
		 assertTrue(page.getRegisterUserForm().typeEmailAddress(lLeadEmail), "Unable to type lead email..");
		 assertTrue(page.getRegisterUserForm().typePhoneNumber(lLeadPhone), "Unable to type lead phone number..");
		 assertTrue(page.getRegisterUserForm().clickOnRegisterButton(), "Unable to click on Register button..");
		 assertTrue(page.getRegisterUserForm().isLeadRegistered(), "Lead is not registered. Registeration form still visible..");
		 
	 }
}
