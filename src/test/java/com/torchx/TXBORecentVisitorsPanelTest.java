package com.torchx;

import static org.testng.Assert.assertEquals;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.AbstractPage;

/**
 * 
 * @author ahabib
 *
 */
public class TXBORecentVisitorsPanelTest extends PageTest {
	private WebDriver driver;
	TXBORecentVisitorsPanel page;
	private JSONObject dataObject;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new TXBORecentVisitorsPanel(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new TXBORecentVisitorsPanel(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyRecentVisitorsPanelHeader(String pDataFile) {
		getPage();
		dataObject = getDataFile(pDataFile);
		if(!getLoginPage().doLogin(getTXBOUsername(), getTXBOPassword())) {
			throw new SkipException("Skipping the test because [Login] pre-condition was failed.");
		}
		assertEquals(page.getPanelHeaderText(),dataObject.optString("panel_header"));
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyRecentVisitorsPanelTooltip(String pDataFile) {
		getPage();
		dataObject = getDataFile(pDataFile);
		assertEquals(page.getToolTipText(),dataObject.optString("tooltip_text"));
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
