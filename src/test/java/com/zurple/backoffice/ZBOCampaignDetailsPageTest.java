package com.zurple.backoffice;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.backoffice.marketing.ZBOCampaignDetailsPage;
import com.zurple.my.PageTest;
import com.zurple.website.ZWRegisterUserPageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.blocks.zurple.ZBOHeadersBlock;

public class ZBOCampaignDetailsPageTest extends PageTest{

	ZBOCampaignDetailsPage page;
	WebDriver driver;
	private JSONObject dataObject;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			setLoginPage(driver);
			page = new ZBOCampaignDetailsPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl, boolean pForcefully) {
		if(pForcefully) {
			driver = getDriver();
			setLoginPage(driver);
			page = new ZBOCampaignDetailsPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			setLoginPage(driver);
			page = new ZBOCampaignDetailsPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Verify Beta campaign is visible in BO
	 * 49949
	 */
	@Test
	public void testVerifyCampaignIsVisible() {
		String l_camapaign_id = EnvironmentFactory.configReader.getPropertyByName("beta_campaign_id");
		getPage("/campaigns/enroll/"+l_camapaign_id,true);
		assertTrue(!page.getCampaignName().isEmpty(), "Beta Campaign name is visible..");		
	}
	
	/**
	 * Verify agents are unable to edit the content or templates within the beta campaign
	 * 49951
	 */
	@Test
	public void testVerifyAgentIsUnableToEditContentOrTemplates() {
		String l_camapaign_id = EnvironmentFactory.configReader.getPropertyByName("beta_campaign_id");
		getPage("/campaigns/enroll/"+l_camapaign_id);
		assertTrue(!page.isAddTemplateButtonVisible(), "Add template button is not visible");
		assertTrue(page.isRemoveButtonDisabled(), "Remove button is not disable for beta campaign");
	}
	
	/**
	 * Verify agents are able to preview the templates in beta campaign
	 * 49952
	 */
	@Test
	public void testVerifyPreviewButtonsWorking() {
		String l_camapaign_id = EnvironmentFactory.configReader.getPropertyByName("beta_campaign_id");
		getPage("/campaigns/enroll/"+l_camapaign_id);
		assertTrue(page.verifyUserCanPreviewTheTemplates(), "Preview button is not working");
	}
	
	/**
	 * Verify agent is unable to change scheduling of templates
	 * 49953
	 */
	@Test
	public void testVerifyTimeLineInputIsDisabled() {
		String l_camapaign_id = EnvironmentFactory.configReader.getPropertyByName("beta_campaign_id");
		getPage("/campaigns/enroll/"+l_camapaign_id);
		assertTrue(page.isTimeLineInputDisabled(), "Timeline input is not disabled for beta campaign");
	}
	
	/**
	 * Verify agent is unable to change campaign name or description
	 * 49955
	 */
	@Test
	public void testVerifyBetaCampaignNameDescriptionIsDisabled() {
		String l_camapaign_id = EnvironmentFactory.configReader.getPropertyByName("beta_campaign_id");
		getPage("/campaigns/enroll/"+l_camapaign_id);
		assertTrue(page.isCampaignNameInputDisabled(), "Campaign Name is not disabled for beta campaign");
		assertTrue(page.isCampaignDescriptionInputDsiabled(), "Campaign Description input is not disabled for beta campaign");
	}
	
	/**
	 * Verify agents can not change recipients of the campaign
	 * 49956
	 */
	@Test
	public void testVerifyRecipientsAreDisabled() {
		String l_camapaign_id = EnvironmentFactory.configReader.getPropertyByName("beta_campaign_id");
		getPage("/campaigns/enroll/"+l_camapaign_id);
		assertTrue(page.isIndividualLeadOptionDisabled(), "Individual lead option is not disabled for beta campaign");
		assertTrue(page.isAllLeadNewOptionDisabled(), "New lead option is not disabled for beta campaign");
		assertTrue(page.isIndividualLeadUnresponsiveOptionDisabled(), "Unresponsive lead option is not disabled for beta campaign");
		assertTrue(page.isIndividualLeadCommunicatedOptionDisabled(), "Communicated with me lead option is not disabled for beta campaign");
		assertTrue(page.isIndividualLeadActiveOptionDisabled(), "Active lead option is not disabled for beta campaign");
	
	}
	
	/**
	 * Verify Lead source can not be edited
	 * 49957
	 */
	@Test
	public void testVerifyLeadSourceCannotBeEdited() {
		String l_camapaign_id = EnvironmentFactory.configReader.getPropertyByName("beta_campaign_id");
		getPage("/campaigns/enroll/"+l_camapaign_id);
		assertTrue(page.isLeadSourceInputDisabled(), "Campaign Name is not disabled for beta campaign");
	}
	
	/**
	 * Verify Auto Enroll toggle can be enabled/disabled
	 * 49958
	 */
	@Test
	public void testVerifyAutoEnrollCanBeToggled() {
		String l_camapaign_id = EnvironmentFactory.configReader.getPropertyByName("beta_campaign_id");
		getPage("/campaigns/enroll/"+l_camapaign_id);
		assertTrue(page.verifyAutoEnrollCanBeToggled(), "Auto Enroll can not be toggled for beta campaign");
	}
	
	/**
	 * Verify Auto Enroll toggle can be enabled/disabled
	 * 49958
	 */
	@Test
	public void testVerifyOnlyLeadWithPhonenumberCannotBeToggled() {
		String l_camapaign_id = EnvironmentFactory.configReader.getPropertyByName("beta_campaign_id");
		getPage("/campaigns/enroll/"+l_camapaign_id);
		assertFalse(page.verifyPhoneNumberCannotBeToggled(), "Phone number was toggled for beta campaign");
	}
	
	/**
	 * Verify Lead Status is set to New for those admins who have entry in campaign_user_targeting_criteria table
	 * 49979
	 */
	@Test
	public void testVerifyProspectNewOptionIsSelectedByDefault() {
		String l_camapaign_id = EnvironmentFactory.configReader.getPropertyByName("beta_campaign_id");
		getPage("/campaigns/enroll/"+l_camapaign_id);
		assertTrue(page.isProspectNewRadioSelected(), "Radio input is not selected");
	}
	
	/**
	 * Verify Lead gets enrolled into Beta campaign successfully
	 * 49960
	 */
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLeadWithPhoneNumberGetsEnrolledInBetaCampaign(String pDataFile) {
		String l_camapaign_id = EnvironmentFactory.configReader.getPropertyByName("beta_campaign_id");
		getPage("/campaigns/enroll/"+l_camapaign_id,true);
		String l_beta_campaign_name = page.getCampaignName();
		gotoWebsiteWithMLB();
		ZWRegisterUserPageTest registerUser = new ZWRegisterUserPageTest();
		registerUser.testRegisterUser(pDataFile);
		gotoLeadDetailPage();
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.verifyCampaignNameFromMyMessages(l_beta_campaign_name), "Lead is not entolled in beta campaign");

	}
	
	/**
	 * Verify Lead gets enrolled into Beta campaign successfully
	 * 49963
	 */
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLeadWithoutPhoneNumberDoesNotGetsEnrolledInBetaCampaign(String pDataFile) {
		gotoWebsiteWithMLB();
		ZWRegisterUserPageTest registerUser = new ZWRegisterUserPageTest();
		registerUser.testRegisterUser(pDataFile);
		gotoLeadDetailPage();
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.verifyCampaignNameFromMyMessages("None"), "Lead is not entolled in beta campaign");
		
	}
	
	/**
	 * Verify Lead Status is set to Unresponsive for those admins who do not have entry in campaign_user_targeting_criteria table
	 * 49978
	 */
	@Test
	public void testVerifyLeadStatusIsUnresponsiveForAdminsWithNoEntryInCampaignsCriteria() {
		getPage();
		ZBOHeadersBlock headers = new ZBOHeadersBlock(driver);
		assertTrue(headers.logoutFromBackOffice(), "Unable to logout from back office.");
		assertTrue(getLoginPage().doLogin(EnvironmentFactory.configReader.getPropertyByName("sub_admin_email"), 
				EnvironmentFactory.configReader.getPropertyByName("sub_admin_password")), "Unable to login with Sub Admin credentials");
		String l_camapaign_id = EnvironmentFactory.configReader.getPropertyByName("beta_campaign_id");
		getPage("/campaigns/enroll/"+l_camapaign_id,true);
		assertTrue(page.isIndividualLeadUnresponsiveOptionDisabled(), "Radio unresponsive input is not disabled");
		assertTrue(page.isUnresponsiveRadioSelected(), "Radio input is not selected");
		
	}
	
	/**
	 * Verify scheduling of templates once Lead gets enrolled in beta campaign
	 * 49968
	 */
	@Test
	public void testVerifyScheduledMessagesArePopulatedForLeadsEnrolledInBetaCampaign() {
		String l_camapaign_id = EnvironmentFactory.configReader.getPropertyByName("beta_campaign_id");
		getPage("/campaigns/enroll/"+l_camapaign_id,true);
		int number_of_scheduled_emails_sms = page.getTemplateNames().size();
		assertTrue(verifyTemplateFromScheduledMessages(number_of_scheduled_emails_sms),"The templates are not scheduled under scheduled messages");
	}
    private boolean verifyTemplateFromScheduledMessages(int pNumberOfScheduledItems) {
    	gotoLeadDetailPage();
    	ZBOLeadDetailPage leaddetailPage = new ZBOLeadDetailPage(driver);
		return leaddetailPage.verifyEmailSmsAreScheduled(pNumberOfScheduledItems);
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
	private void gotoLeadDetailPage() {
		String l_lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		String lUpdatedUrl = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+"/lead/"+l_lead_id;
		driver.navigate().to(lUpdatedUrl);
	}
	private void gotoWebsiteWithMLB() {
		String lUpdatedUrl = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")+"/register?mlb";
		driver.navigate().to(lUpdatedUrl);
	}
	
}
