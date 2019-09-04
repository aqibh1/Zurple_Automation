package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.AutomationLogger;

public class ZBOLeadDetailPageTest extends PageTest{

	private WebDriver driver;
	private ZBOLeadDetailPage page;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOLeadDetailPage(driver);
			page.setUrl("");
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOLeadDetailPage(driver);
			page.setUrl(pUrl);
		}
		return page;
	}
	
	@Override
	public void clearPage() {
	
	}
	
	@Test
	public void testVerifyLeadPropTracking() {
		AutomationLogger.startTestCase("Verify lead property tracking");
		getPage();
		String lLeadEmail = ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.RegisterFormLeadEmail);
		String lLeadId = ModuleCommonCache.getElement(getThreadId().toString(), lLeadEmail);
		String lLeadAddress = ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.ListingsAddress);
		
		String lUpdatedUrl = driver.getCurrentUrl().replace("dashboard", "lead/"+lLeadId+"");
		driver.navigate().to(lUpdatedUrl);
		
		assertTrue(page.isLeadDetailPage(),"Lead Detail page is not opened..");
		assertTrue(page.isPropertyTracked(lLeadAddress),"Property is not tracked under lead details page.."+lLeadAddress);
		
		AutomationLogger.endTestCase();
	}


}
