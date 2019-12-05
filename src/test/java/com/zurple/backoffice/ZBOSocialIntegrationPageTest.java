/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;

/**
 * @author adar
 *
 */
public class ZBOSocialIntegrationPageTest extends PageTest{

	private ZBOSocialIntegrationAndSettingsPage page;
	private WebDriver driver;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOSocialIntegrationAndSettingsPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOSocialIntegrationAndSettingsPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;		
	}
	
	@Test
	public void testConnectToFacebook() {
		getPage("/social/integrations-and-settings");
		assertTrue(page.isSocialIntegrationPage(), "Social Integration Page is not visible..");
		assertTrue(page.connectToFacebook(true), "Unable to connect to facebook..");
		
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}

}
