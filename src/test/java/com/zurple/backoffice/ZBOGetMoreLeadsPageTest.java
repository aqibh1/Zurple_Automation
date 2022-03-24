package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertTrue;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;
import resources.AbstractPage;

public  class ZBOGetMoreLeadsPageTest extends PageTest {

	private ZBOGetMoreLeadsPage page;
	private WebDriver driver;
	
	@Test
	public void testGetMoreLeadHeadingIsVisible() {
		page=null;
		getPage("/getmoreleads");
		assertTrue(page.isGetMoreLeadHeadingVisible(),"Get more leads heading is not Visible");
	}

	@Test
	public void testElementsVisibleAndClickable() {
		assertTrue(page.verifyZapierlogoIsClickable(),"Zapier logo is not Visible");
		assertTrue(page.verifyZillowGrouplogoIsClickable(),"Zillow Group logo is not Visible");
		assertTrue(page.verifyRealtorlogoIsClickable(),"Realtor logo is not Visible");
		assertTrue(page.checkMarketLeaderlogoIsClickable(),"Market leader logo is not Visible");
	}
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOGetMoreLeadsPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOGetMoreLeadsPage(driver);
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
	
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
	
	
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
}
