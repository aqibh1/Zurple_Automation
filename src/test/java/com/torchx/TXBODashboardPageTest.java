/**
 * 
 */
package com.torchx;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.AbstractPage;

/**
 * @author adar
 *
 */
public class TXBODashboardPageTest extends PageTest{
	
	TXBODashboardPage page;
	private WebDriver driver;
	JSONObject dataObject;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new TXBODashboardPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new TXBODashboardPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	
	@Test
	public void testVerifySideBarNavigationWorks() {
		getPage();
		assertTrue(page.isDashboardPage(), "Dashboard page is not visible..");
		assertTrue(page.getSideMenuBlock().isTorchXLogoVisible(), "TorchX logo is not visible on side menu bar");
		assertTrue(page.getSideMenuBlock().verifyNavigationbarColor(), "Navigation bar color is not as expected..");
		assertTrue(page.getSideMenuBlock().expandAutomationMenu(), "Unable to click on Automation dropdown in side bar");
//		assertTrue(page.getSideMenuBlock().gotoMyProfile(), "My Profile link in side bar is not working");
		assertTrue(page.getSideMenuBlock().goBackToDashboard(), "Dashboard page is not visible..");
		assertTrue(page.getSideMenuBlock().expandAutomationMenu(), "Unable to click on Automation dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoBilling(), "Biling link in side bar is not working");
		assertTrue(page.getSideMenuBlock().expandAutomationMenu(), "Unable to click on Automation dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoSupport(), "Support link in side bar is not working");
		assertTrue(page.getSideMenuBlock().expandLeadMenu(), "Unable to click on Leads dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoLeadList(), "Lead List link is not working in side bar");
		assertTrue(page.getSideMenuBlock().expandLeadMenu(), "Unable to click on Leads dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoAddList(), "Add Lead link is not working in side bar");
		assertTrue(page.getSideMenuBlock().gotoProperties(), "Properties link is not working in side bar");
		assertTrue(page.getSideMenuBlock().expandMarketingMenu(), "Unable to click on Marketing dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoMessageLog(), "Message Log link is not working in side bar");
		assertTrue(page.getSideMenuBlock().expandMarketingMenu(), "Unable to click on Marketing dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoEmailMessage(), "Email Message link is not working in side bar");
		assertTrue(page.getSideMenuBlock().expandMarketingMenu(), "Unable to click on Marketing dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoTextMessage(), "Text Message link is not working in side bar");
		assertTrue(page.getSideMenuBlock().expandMarketingMenu(), "Unable to click on Marketing dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoCampaigns(), "Campaigns link is not working in side bar");
		assertTrue(page.getSideMenuBlock().expandMarketingMenu(), "Unable to click on Marketing dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoTemplateManager(), "Template Manager link is not working in side bar");
		assertTrue(page.getSideMenuBlock().expandMarketingMenu(), "Unable to click on Marketing dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoCraiglist(), "Craiglist link is not working in side bar");
		assertTrue(page.getSideMenuBlock().expandSocialMenu(), "Unable to expand Social menu dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoCreatePost(), "Create Post link is not working in side bar");
		assertTrue(page.getSideMenuBlock().expandSocialMenu(), "Unable to expand Social menu dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoSchedulePost(), "Scheduled Post link is not working in side bar");
		assertTrue(page.getSideMenuBlock().expandSocialMenu(), "Unable to expand Social menu dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoPostHistory(), "Posting History link is not working in side bar");
		assertTrue(page.getSideMenuBlock().expandSocialMenu(), "Unable to expand Social menu dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoIntegrationAndSettings(), "Integration and settings link is not working in side bar");
		assertTrue(page.getSideMenuBlock().gotoStatistics(), "Statistics link is not working in side bar");
		
	}
	
	@Test
	public void testVerifyFooterNavigationWorks() {
		page=null;
		getPage();
		assertTrue(page.isDashboardPage(), "Dashboard page is not visible..");
		assertTrue(page.getFooterMenuBlock().gotoLeadList(), "Lead link is not working in footer..");
		assertTrue(page.getFooterMenuBlock().gotoProperties(), "Properties link is not working in footer..");
		assertTrue(page.getFooterMenuBlock().gotoStatistics(), "Statistics link is not working in footer..");
		assertTrue(page.getFooterMenuBlock().gotoSupport(), "Support link is not working in footer..");
		assertTrue(page.getFooterMenuBlock().goBackToDashboard(), "Dashboard link is not working in footer..");
	}
	
	@Test
	public void testNavigationWorksInMobileView() {
		getPage();
		assertTrue(page.resizeWindowToMobileView(), "Unable to resize window to mobile view");
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		
		assertTrue(page.isDashboardPage(), "Dashboard page is not visible..");
		assertTrue(page.getSideMenuBlock().isTorchXLogoVisible(), "TorchX logo is not visible on side menu bar");
		assertTrue(page.getSideMenuBlock().verifyNavigationbarColor(), "Navigation bar color is not as expected..");
		assertTrue(page.getSideMenuBlock().expandAutomationMenu(), "Unable to click on Automation dropdown in side bar");
//		assertTrue(page.getSideMenuBlock().gotoMyProfile(), "My Profile link in side bar is not working");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().goBackToDashboard(), "Dashboard page is not visible..");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandAutomationMenu(), "Unable to click on Automation dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoBilling(), "Biling link in side bar is not working");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandAutomationMenu(), "Unable to click on Automation dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoSupport(), "Support link in side bar is not working");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandLeadMenu(), "Unable to click on Leads dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoLeadList(), "Lead List link is not working in side bar");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandLeadMenu(), "Unable to click on Leads dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoAddList(), "Add Lead link is not working in side bar");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().gotoProperties(), "Properties link is not working in side bar");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandMarketingMenu(), "Unable to click on Marketing dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoMessageLog(), "Message Log link is not working in side bar");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandMarketingMenu(), "Unable to click on Marketing dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoEmailMessage(), "Email Message link is not working in side bar");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandMarketingMenu(), "Unable to click on Marketing dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoTextMessage(), "Text Message link is not working in side bar");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandMarketingMenu(), "Unable to click on Marketing dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoCampaigns(), "Campaigns link is not working in side bar");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandMarketingMenu(), "Unable to click on Marketing dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoTemplateManager(), "Template Manager link is not working in side bar");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandMarketingMenu(), "Unable to click on Marketing dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoCraiglist(), "Craiglist link is not working in side bar");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandSocialMenu(), "Unable to expand Social menu dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoCreatePost(), "Create Post link is not working in side bar");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandSocialMenu(), "Unable to expand Social menu dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoSchedulePost(), "Scheduled Post link is not working in side bar");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandSocialMenu(), "Unable to expand Social menu dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoPostHistory(), "Posting History link is not working in side bar");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().expandSocialMenu(), "Unable to expand Social menu dropdown in side bar");
		assertTrue(page.getSideMenuBlock().gotoIntegrationAndSettings(), "Integration and settings link is not working in side bar");
		
		assertTrue(page.clickOnNavToggleButton(), "Unable to click on navigation toggle button..");
		assertTrue(page.getSideMenuBlock().gotoStatistics(), "Statistics link is not working in side bar");
	}
}
