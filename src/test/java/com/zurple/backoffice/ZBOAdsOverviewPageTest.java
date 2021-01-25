/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.zurple.backoffice.ads.ZBOAdsOverviewPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;

/**
 * @author adar
 *
 */
public class ZBOAdsOverviewPageTest extends PageTest{

	private WebDriver driver;
	private JSONObject dataObject;
	private ZBOAdsOverviewPage page;
	private ZBOLoginPage loginPage;
	private String zurpleUsername;
	private String zurplePassowrd;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOAdsOverviewPage(driver);
			loginPage = new ZBOLoginPage(driver);
			zurpleUsername = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_user");
			zurplePassowrd =EnvironmentFactory.configReader.getPropertyByName("zurple_bo_pass");
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	public void testVerifyAdsManagerIsVisibleInHeader() {
		getPage();
		if(!loginPage.doLogin(zurpleUsername, zurplePassowrd)) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
		assertTrue(page.getHeader().isAdsManagerHeadingVisible(), "Ads Manager heading is not visible.");
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
