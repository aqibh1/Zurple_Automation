/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.z57.site.v2.ContactMePage;
import com.zurple.my.PageTest;
import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.TestEnvironment;
import resources.utility.ActionHelper;
import resources.utility.DataConstants;

/**
 * @author adar
 *
 */
public class PPUserSettingsPageTest extends PageTest{
	WebDriver driver;
	PPUserSettingsPage page;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPUserSettingsPage(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pURL) {
		if(page == null){
			driver = getDriver();
			page = new PPUserSettingsPage(driver);
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
	@Parameters({"dataFileSettings"})
	public void testUpdateAndVerifyUserSettings(String pDataFile) {
		getPage("/account/settings");
		
		JSONObject dataObject = getDataFile(pDataFile);
		String lDesignation = updateName(dataObject.optString(DataConstants.Designation)).replace(" ","");
		String lPhoneNum = updateName(dataObject.optString(DataConstants.Phone)).replace(" ","");
		
		assertTrue(page.isSettingsPage(), "Settings page is not visible");
		assertTrue(page.typeDesignation(lDesignation), "Unable to type designation..");
//		assertTrue(page.typePhone(lPhoneNum), "Unable to type Phone number..");
		assertTrue(page.clickOnSaveButton(), "Unable to type Phone number..");
		assertTrue(page.isDetailUpdatedSuccessfully(), "Details are not updated..");
		
		verifyDetailsOnContactPage(lDesignation,lPhoneNum);
		
	}
	
	private void verifyDetailsOnContactPage(String pDesignation, String pPhoneNum) {
		ActionHelper.staticWait(60);
		driver.navigate().to(EnvironmentFactory.configReader.getPropertyByName("z57_site_v2_base_url")+"/contact-me");
		System.out.print(pDesignation);
		ActionHelper.staticWait(60);
		ActionHelper.RefreshPage(driver);
		ContactMePage contactPage = new ContactMePage(driver);
		assertTrue(contactPage.isContactMePage(), "Contact Page is not visible..");
		assertTrue(contactPage.verifyDesignation(pDesignation), "Unable to verify designation..");
//		assertTrue(contactPage.verifyPhone(pPhoneNum), "Unable to verify phone..");
	}

}
