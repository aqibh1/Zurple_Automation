package com.zurple.backoffice;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.orm.hibernate.models.zurple.AdminDashboardStats;
import resources.utility.AutomationLogger;
import resources.utility.DataConstants;
import resources.utility.GmailEmailVerification;

public class ZBODashboardTest extends PageTest
{

    private ZBODashboardPage page;
    private WebDriver driver;
    private JSONObject dataObject;
    private AdminDashboardStats adminDashboardStatsObject;
    
    public AbstractPage getPage() {
    	page=null;
    	if(page == null){
        	driver = getDriver();
			page = new ZBODashboardPage(driver);
			adminDashboardStatsObject=getAdminDashboardStatsObject();
			page.setUrl("");
			page.setDriver(driver);
        }
        return page;
	}
    
    public AbstractPage getPage(String pUrl){
        if(page == null){
        	driver = getDriver();
			page = new ZBODashboardPage(driver);
			adminDashboardStatsObject=getAdminDashboardStatsObject();
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
    @Parameters({"registerUserDataFile"})
    public void testPhoneNumber(String pDataFile){
    	AutomationLogger.startTestCase("Verify lead phone number from dashboard");
    	page=null;
    	getPage();
    	dataObject = getDataFile(pDataFile);
    	String pNum = dataObject.optString(DataConstants.Phone);
    	String pCacheNum =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleleadPhone);
    	if(pCacheNum!=null && !pCacheNum.isEmpty()) {
    		pNum = pCacheNum;
    	}
        assertTrue(page.verifyPhoneNumberText(pNum), "Phone numbers didn't match..");
        assertTrue(page.verifyPhoneAlert(), "Phone tap Alert not present..");
    }
    
    @Test
    public void testLogoutFromBackOffice() {
    	getPage();
    	assertTrue(page.doLogout(), "Logout failed");
    }
    
    @Test
    public void testVerifyDashboardStatsAreDisplayed() {
    	getPage();
//    	assertTrue(page.hardRefreshThePage(), "Unable to do hard refresh..");
    	assertTrue(page.isKeyStatsVisible(), "Key stats are not displayed..");
    	assertTrue(page.isLeadsManagedStatsVisible(), "Leads managed stats are not displayed..");
    	assertTrue(page.isMessagesSentVisible(), "Messages sent are not displayed..");
    	assertTrue(page.isMessagesOpenRateVisible(), "Messages open rate stats are not displayed..");
    	assertTrue(page.allVisitesTodayWorking(), "All visits today graph is not working..");
    	assertTrue(page.allVisitesPastWeekWorking(), "All visits past week graph is not working..");
    	assertTrue(page.allVisitesPastMontWorking(), "All visits past month graph is not working..");
    	assertTrue(page.allVisitesPastYearWorking(), "All visits past year graph is not working..");
    	assertTrue(page.isZurpleUpdateVisible(), "Zurple updates are not displayed..");
    	assertTrue(page.isZurpleUpdatesTextVisible(), "Zurple updates text is not displayed..");
    	assertTrue(page.isNewLeadsHeadingAndStatsDisplayed(), "New leads heading and stats are not displayed..");
    	assertTrue(page.isLeadNamesDisplayed(), "Lead names are not displayed..");
    	assertTrue(page.isHotBehaviorsDisplayed(), "Hot Behaviors is not displayed..");
    	assertTrue(page.clickOnViewLeadsButton(), "View leads button is not working..");
    	
    }
    @Test
    public void testEmailIsReceived() {
    	GmailEmailVerification gmailObject = new GmailEmailVerification();
    	gmailObject.isEmailPresentAndReply("z57testuser.zurpleqa@gmail.com", "uznhhalkthskjpyx", "Quick Question", "aqib.zurple.production@zengtest2.us",true);    	
    }
   
    @Test
    public void testIsNewLeadDisplayedOnDashboard() {
    	page=null;
    	getPage();
    	String lc_leadName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);;
    	assertTrue(page.isLeadDisplayed(lc_leadName), "Lead name is not displayed on the dashboard");
    	assertTrue(page.clickOnLeadName(lc_leadName), "Unable to click on lead name on the dashboard");
    	ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
    	assertTrue(leadDetailPage.isLeadDetailPage(), "Lead detail page is not visible..");
    	assertTrue(leadDetailPage.isLeadNameExist(lc_leadName), "Lead Name does not exists on lead detail page ..");
    }
    
    /**
     * Verify 'New Lead' show correct count under Key Stats section
     * 47702
     */
    @Test
    public void testVerifyNewLeadsCountOnDashboard() {
    	getPage();
    	adminDashboardStatsObject = getAdminDashboardStatsObject();
    	int l_new_leads_count_db = adminDashboardStatsObject.getNew_leads();
    	int l_new_leads_count_ui = Integer.parseInt(page.getNewLeadsCountFromKeyStats());
    	assertTrue(l_new_leads_count_db==l_new_leads_count_ui, "New leads count is not same on dashboard key stats section");	
    }
    
    /**
     * Verify 'Leads Managed' show correct count under Key Stats section
     * 47703
     */
    @Test
    public void testVerifyManageLeadsCountOnDashboard() {
    	getPage();
    	int l_managed_leads_count_db = adminDashboardStatsObject.getLeads_managed();
    	int l_managed_leads_count_ui = Integer.parseInt(page.getLeadsManagedCountFromKeyStats());
    	assertTrue(l_managed_leads_count_db==l_managed_leads_count_ui, "Managed leads count is not same on dashboard key stats section");
    }
    
    /**
     * Verify 'Messages Sent' show correct count under Key Stats section
     * 47704
     */
    @Test
    public void testVerifyMessagesSentCountOnDashboard() {
    	getPage();
    	int l_messages_sent_count_db = adminDashboardStatsObject.getMessages_sent();
    	int l_messages_sent_count_ui = Integer.parseInt(page.getMessagesSentCountFromKeyStats());
    	assertTrue(l_messages_sent_count_db==l_messages_sent_count_ui, "Messages Sent count is not same on dashboard key stats section");
    }
    
    /**
     * Verify 'Open Rate' show correct count under Key Stats section
     * 47705
     */
    @Test
    public void testVerifyOpenRateCountOnDashboard() {
    	getPage();
    	int l_messages_open_rate_count_db = adminDashboardStatsObject.getOpen_rate();
    	int l_messages_open_rate_ui = Integer.parseInt(page.getMessagesOpenRateCountFromKeyStats());
    	assertTrue(l_messages_open_rate_count_db==l_messages_open_rate_ui, "Messages Open Rate count is not same on dashboard key stats section");
    }
   
    /**
     * Verify 'Get More Leads' show correct count under Key Stats section
     * 47706
     */
    @Test
    public void testVerifyZurpleAutoLeadsCountOnDashboard() {
    	getPage();
    	int l_auto_leads_count_db = adminDashboardStatsObject.getAuto_leads();
    	int l_auto_leads_ui = Integer.parseInt(page.getZurpleAutoLeadsStatsCount());
    	assertTrue(l_auto_leads_count_db==l_auto_leads_ui, "Zurple Auto leads count mismatched: "+l_auto_leads_count_db);
    }
    
    /**
     * Verify 'Lead Replies' show correct count under Key Stats section
     * 47707
     */
    @Test
    public void testVerifyLeadReplyCountOnDashboard() {
    	getPage();
    	int l_lead_replies_rate_count_db = adminDashboardStatsObject.getLead_replies();
    	int l_lead_replies_rate_ui = Integer.parseInt(page.getZurpleLeadRepliesStatsCount());
    	assertTrue(l_lead_replies_rate_count_db==l_lead_replies_rate_ui, "Lead Replies count is not same on dashboard key stats section");
    }
    
    /**
     * Verify 'Alerts Triggered' show correct count under Key Stats section
     * 47709
     */
    @Test
    public void testVerifyAlertsTriggeredCountOnDashboard() {
    	getPage();
    	int l_alert_triggered_count_db = adminDashboardStatsObject.getAlert_triggered();
    	int l_alert_triggered_count_ui = Integer.parseInt(page.getAlertTriggeredFromKeyStats());
    	assertTrue(l_alert_triggered_count_db==l_alert_triggered_count_ui, "Alert Triggered count is not same on dashboard key stats section");
    }
    
    /**
     * Verify 'Website Visit' show correct count under Key Stats section
     * 47708
     */
    @Test
    public void testVerifyWebsiteVisitsCountOnDashboard() {
    	getPage();
    	int l_website_stats_count_db = adminDashboardStatsObject.getWebsite_visits();
    	int l_website_stats_count_ui = Integer.parseInt(page.getWebsiteVisitFromKeyStats());
    	assertTrue(l_website_stats_count_db==l_website_stats_count_ui, "Website stats count is not same on dashboard key stats section");
    }
//    private int getZurpleAutoLeadCount(List<Admin> list_of_admin) {
//    	int totalLeadCount = 0;
//    	DBHelperMethods dbObject = new DBHelperMethods(getEnvironment());
//    	String create_date = getDateAfterSubtractingNumberOfDays(-30, "YYYY-MM-dd");
//    	Date create_date_format = null;
//		try {
//			create_date_format = new SimpleDateFormat("YYYY-MM-dd").parse(create_date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(Admin admObject: list_of_admin) {
//			int admin_id = admObject.getId();
//			int lead_count_hv = dbObject.getListOfUsers(admin_id, "home-valuation", create_date_format).size();
//			int lead_count_unknown = dbObject.getListOfUsers(admin_id, "unknown", create_date_format).size();
//			totalLeadCount += lead_count_hv+lead_count_unknown;
//		}
//		return totalLeadCount;
//	}

	private AdminDashboardStats getAdminDashboardStatsObject() {
    	DBHelperMethods dbObject = new DBHelperMethods(getEnvironment());	
    	int l_admin_id = Integer.parseInt(EnvironmentFactory.configReader.getPropertyByName("zurple_bo_default_agent_id"));
    	AdminDashboardStats adminDashboardStatsObject = dbObject.getAdminStatsByAdminId(l_admin_id);
    	page.waitForLoadingKeyStatsToDisappear();
    	return adminDashboardStatsObject;
    }

}