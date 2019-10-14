package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.AutomationLogger;

public class ZBOLeadPageTest extends PageTest{
	WebDriver driver;
	ZBOLeadPage page;
	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		return null;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOLeadPage(driver);
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
	public void testSearchAndVerifyLead() {
		AutomationLogger.startTestCase("Search and Verify lead");
		getPage("/leads");
		String lLeadName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);
		assertTrue(page.isLeadPage(), "Lead page is not visible..");
		assertTrue(page.isLeadExist(lLeadName), "Unable to find lead on lead page..");
		AutomationLogger.endTestCase();
		
	}
	
	@Test
	public void testVerifySortingIsWorking() {
		AutomationLogger.startTestCase("Veifying Sorting on Leads page");
		getPage("/leads");
		assertTrue(page.isLeadPage(), "Lead Page is not visible..");
		assertTrue(page.isProcessingComplete(), "Processing is not completed..");
		assertTrue(page.verifyNameSortingWorking(), "Sorting for Name column is not working...");
		assertTrue(page.verifyEmailSortingWorking(), "Sorting for Email column is not working...");
		assertTrue(page.verifySearchLocationSortingWorking(), "Sorting for Search Location column is not working...");
		assertTrue(page.verifyMaxPriceSortingWorking(), "Sorting for Max Price column is not working...");
		assertTrue(page.verifyDateCreatedSortingWorking(), "Sorting for Date Created column is not working...");
		assertTrue(page.verifyAgentSortingWorking(), "Sorting for Agent column is not working...");
		assertTrue(page.verifyLastModifiedSortingWorking(), "Sorting for Last Modified column is not working...");
		assertTrue(page.verifyLastVisitSortingWorking(), "Sorting for Last Visit column is not working...");
		AutomationLogger.endTestCase();
	}

}
