/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.PageTest;

import resources.AbstractPage;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBOLeadExportPageTest extends PageTest{
	private WebDriver driver;
	private ZBOExportLeadsPage page;

	@Override
	public AbstractPage getPage() {
		if(page == null){
            page = new ZBOExportLeadsPage();
            page.setDriver(getDriver());
        }
        return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
            page = new ZBOExportLeadsPage(driver);
            page.setUrl(pUrl);
            page.setDriver(driver);
            
        }
        return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	@Test(retryAnalyzer = resources.RetryFailedTestCases.class) 
	public void testExportLead() {
		AutomationLogger.startTestCase("Export Leads");
		getPage("/leads/exportleads");
		assertTrue(page.isExportLeadsPage(), "Export leads page is not visible..");
		assertTrue(page.selectAgentLeads(), "Unable to select agents from the list..");
		assertTrue(page.clickOnExportSelectedButton(), "Unable to click on export leads button..");
		assertTrue(ActionHelper.clickSaveAsButtonWindows(driver), "Unable to click 'Save As' button..");
		AutomationLogger.endTestCase();

	}

}
