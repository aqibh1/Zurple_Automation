package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.zurple.backoffice.marketing.ZBOCampaignPage;
import com.zurple.backoffice.marketing.ZBOCreateCampaignPage;
import com.zurple.backoffice.marketing.ZBOCreateTemplatePage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

public class ZBOCreateCampaignPageTest extends PageTest{

	ZBOCreateCampaignPage page;
	WebDriver driver;
	private JSONObject dataObject;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreateCampaignPage(driver);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreateCampaignPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	@Test(dependsOnGroups = {"com.zurple.backoffice.ZBOCreateTemplatePageTest.testCreateTemplate"})
	public void testVerifyTemplateIsAddedInCampaignsPage() {
		getPage("/campaigns/create");
		String lTemplateName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleTemplateName);
		assertTrue(page.isCampaignPage(), "Campaign page is not displayed...");
		assertTrue(page.clickOnAddTemplateButton(), "Unable to click on Add template button..");
		assertTrue(page.getZboAddTemplateForm().isCampaignAddTemplateFormVisible(), "Add template form is not displayed..");
		assertTrue(page.getZboAddTemplateForm().isTemplateExist(lTemplateName), "Template not found on campaigns manager page..");
		assertTrue(page.getZboAddTemplateForm().clickOnUpdateButton(), "Unaable to click on update button..");
		assertTrue(page.clickOnTemplateLink(lTemplateName), "Unable to click on template link button..");
		ZBOCreateTemplatePage createTemplatePageObject = new ZBOCreateTemplatePage(driver);
		ActionHelper.switchToSecondWindowByIndex(driver, 2);
		assertTrue(createTemplatePageObject.isCreateTemplatePage(), "Create template page is not visible..");
		assertEquals(createTemplatePageObject.getTemplateName(), lTemplateName, "Template name is not equal..");
	}
	
	@Test(groups= {"testCreateCampaign"}, priority=476)
	@Parameters({"dataFile"})
	public void testCreateCampaign(String pDataFile) {
		dataObject = getDataFile(pDataFile);
		String ld_campaignName = updateName(dataObject.optString("campaign_name"));
		String ld_campaignDesc = updateName(dataObject.optString("campaign_desc"));
		getPage("/campaigns");
		ZBOCampaignPage campaignPage = new ZBOCampaignPage(driver);
		assertTrue(campaignPage.isCampaignPage(), "Campaign Page is not visible..");
		assertTrue(campaignPage.clickOnCreateCampaignButton(), "Unable to click on create campaign button..");
		ActionHelper.switchToSecondWindow(driver);
		assertTrue(page.isCampaignPage(), "Create campaign page is not visible..");
		String lc_templateName = page.clickAndSelectTemplate();
		assertTrue(!lc_templateName.isEmpty(), "Unable to select the template..");
		assertTrue(page.typeCampaignName(ld_campaignName), "Unable to type campaign name..");
		assertTrue(page.typeCampaignDescription(ld_campaignDesc), "Unable to type campaign desc..");
		assertTrue(page.isTemplatedAdded(lc_templateName), "Template is not added to campaign.");
		assertTrue(page.clickOnSaveButton(), "Unable to click on save button...");
		assertTrue(new ZBOSucessAlert(driver).isSuccessMessageVisible(), "Success message is not visible..");
		String lCampaign_ID = driver.getCurrentUrl().split("enroll/")[1];
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleCampaignName, ld_campaignName);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleCampaignID, lCampaign_ID);
	}
	
	@Test(priority=479)
	@Parameters({"dataFile"})
	public void testDeleteCampaign(String pDataFile) {
		dataObject = getDataFile(pDataFile);
		page=null;
		getPage("/campaigns");
		String lc_campaign_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleCampaignID);
		if(lc_campaign_id.contains(",")) {
			String l_campaign_ids[] = lc_campaign_id.split(",");
			for(int i=0;i<l_campaign_ids.length;i++) {
				deleteCampaign(l_campaign_ids[i]);
			}
		}else {
			deleteCampaign(lc_campaign_id);
		}
	}
	
	private void deleteCampaign(String pCampaignid) {
		page = null;
		getPage("/campaigns/enroll/"+pCampaignid);
		ZBOCampaignPage campaignPage = new ZBOCampaignPage(driver);
		assertTrue(campaignPage.isCampaignDetailPage(), "Campaign Page is not visible..");
		assertTrue(campaignPage.deleteCampaign(), "Unable to delete the campaign..");
	}
	
	/**
	 * Verify that correct preview should be shown for added template before saving campaign
	 * 39823
	 */
	@Test
	public void testVerifyCorrectTemplatePreviewIsShownBeforeSavingCampaign() {
		getPage("/campaigns/create");
		selectTemplatePreCondition();
		assertTrue(page.clickOnPreviewButton(), "Unable to click on preview button..");
		assertTrue(page.isPrviewContains(getPlaceHolderValue()), "Preview does not contains the place holder value");
	}
	
	/**
	 * Verify that validation triggers on submitting empty fields
	 * 39851
	 */
	@Test
	public void testVerifyValidationIsTriggeredForEmptyCampaignName() {
		assertTrue(page.clickOnSaveButton(), "Unable to click on save button..");
		assertTrue(page.isEmptyCampaignNameAlertVisible(), "Empty campaign name alert is not visible");
	}
	
	/**
	 * @param pDataFile
	 * Verify that correct preview should be shown for added template after saving campaign
	 * 39824
	 */
	@Test
	@Parameters({"dataFile"})
	public void testVerifyCorrectTemplatePreviewIsShownAfterSavingCampaign(String pDataFile) {
		fillCampaignNameAndDescriptionPreCondition(getDataFile(pDataFile));
		assertTrue(page.clickOnPreviewButton(), "Unable to click on preview button..");
		assertTrue(page.isPrviewContains(getPlaceHolderValue()), "Preview does not contains the place holder value");
		assertTrue(page.closePreview(),"Unable to close preview button..");
	}
	
	/**
	 * Verify that user can see limited recipient options in Zurple
	 * 39826
	 */
	@Test
	public void testVerifyLimitedRecipientsOptionsAreVisible() {
		assertTrue(page.verifyRecipientsOptionsAreVisible(), "All recipients options are not visible..");
	}
	
	/**
	 * Verify that View Matching leads modal should show those leads as selected recipient
	 * 39827
	 */
	@Test
	public void testVerifyMatchingLeadsAreShown() {
		assertTrue(page.clickOnMatchingLeadButton(), "Unable to click on matchin lead button..");
		ActionHelper.staticWait(5);
		assertTrue(page.getZboLeadListform().isLeadListForm(),"Lead list form is not opened..");
		int l_lead_count = page.getZboLeadListform().getLeadsListCount();
		assertTrue(l_lead_count>0, "No matching leads found");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadsList, page.getMatchingLeads());
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadsCount, l_lead_count);
		ActionHelper.staticWait(3);
		assertTrue(page.getZboLeadListform().clickOnCancelButton(), "Unable to click on cancel button");
		ActionHelper.staticWait(3);
	}
	
	/**
	 * Verify that success modal should appear on successful enrollment of leads
	 * 39832
	 */
	@Test
	public void testVerifySuccessMessageIsDisplayedWehnEnrolledIsClicked() {
		SoftAssert softAssert = new SoftAssert();
		assertTrue(page.clickOnEnrollButton(), "Unable to click on enroll button");
		ActionHelper.staticWait(3);
		softAssert.assertTrue(page.getSuccessAlert().clickOnOverrideButton());
		assertTrue(page.getSuccessAlert().isSuccessMessageVisible(), "Success message is not displayed");
		assertTrue(page.getSuccessAlert().clickOnOkButton(), "Unable to click on ok button");
		String lCampaign_ID = driver.getCurrentUrl().split("enroll/")[1];
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleCampaignID, lCampaign_ID);
	}
	
	/**
	 * Verify that currently enrolled leads modal should show relevant leads correctly
	 * 39829
	 */
	@Test
	public void testVerifyLeadsAreEnrolledSuccessfully() {
		AutomationLogger.info("Waiting for leads to get enrolled.. 30 seconds wait");
		ActionHelper.staticWait(120);
		assertTrue(page.clickOnViewRecipientsButton(), "Unable to click on view recipients button");
		assertTrue(page.getZboLeadListform().isEnrolledInCampaignForm(),"Enrolled in campaign form is not opened..");
		assertTrue(page.verifyLeadsAreEnrolled(page.getMatchingLeads(), ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadsList)), "Unable to uncheck the lead");	
	}
	
	/**
	 * Verify that lead count is increased in campaigns list
	 * 39833
	 */
	@Test
	public void testVerifyLeadCounterHasIncreased() {
		assertTrue(verifyEnrolledLeadCount(), "Unable to verify lead count..");	
	}
	
	/**
	 * Verify that leads are not unenrolled if user selected cancel from unenrollment modal
	 * 39835
	 */
	@Test
	public void testVerifyLeadsDoesntUnEnrolledFromCurrentlyEnrolledPanelOnCancel() {
		assertTrue(page.clickOnViewRecipientsButton(), "Unable to click on view recipients button");
		ActionHelper.staticWait(5);
		ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadsList);
		String l_lead_to_unenroll = page.getEnrolledLead(ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadsList));
		assertTrue(page.unenrollLeadFromCurrentlyEnrolledPanel(ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadsList), l_lead_to_unenroll));
		assertTrue(page.getZboLeadListform().clickOnCancelButton(), "Unable to click on save button");
		assertTrue(verifyEnrolledLeadCount(), "Unable to verify lead count..");
	}
	
	/**
	 * Verify that if user unchecks a lead from currently enrolled modal then that lead should be unenrolled from campaign
	 * 39830
	 */
	@Test
	public void testVerifyLeadsGetUnEnrolledFromCurrentlyEnrolledPanel() {
		assertTrue(page.clickOnViewRecipientsButton(), "Unable to click on view recipients button");
		ActionHelper.staticWait(5);
		ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadsList);
		String l_lead_to_unenroll = page.getEnrolledLead(ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadsList));
		assertTrue(page.unenrollLeadFromCurrentlyEnrolledPanel(ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadsList), l_lead_to_unenroll));
		assertTrue(page.getZboLeadListform().clickOnSaveButton(), "Unable to click on save button");
		ActionHelper.staticWait(120);
		assertFalse(verifyEnrolledLeadCount(), "Unable to verify lead count..");
	}
	
	@Test
	public void testVerifyUnenrollButtonUnenrollLeadsFromCampaign() {
		enrollLeadsInCampaignPreCondition();
		assertTrue(page.clickOnUnenrollButton(), "Unable to click on unenroll button");
		assertTrue(page.getSuccessAlert().clickOnUnEnrollButton(), "Unable to click on Unenroll button");
		assertTrue(page.getSuccessAlert().clickOnOkButton(), "Unable to click on ok button");
		AutomationLogger.info("Waiting for leads to be unenrolled");
		ActionHelper.staticWait(200);
		assertFalse(verifyEnrolledLeadCount(), "Unable to verify lead count..");
	}
	
	/**
	 * Verify that if invalid email is provided in individual recipient then validation should trigger
	 * 39831
	 */
	@Test
	@Parameters({"dataFile"})
	public void testVerifyValidationIsTriggeredForInvalidLeadEmail(String pDataFile) {
		getPage("/campaigns/create");
		dataObject = getDataFile(pDataFile);
		selectTemplatePreCondition();
		fillCampaignNameAndDescriptionPreCondition(dataObject);
		assertTrue(page.clickOnIndividualLeadOption(), "Unable to click on individual lead option");
		assertTrue(page.typeEmailAddress("xyz@xyz.com"), "Unable to type the invalid lead");
		assertTrue(page.clickOnEnrollButton(), "Unable to click on enroll button");
		assertTrue(page.isEmailVerificationAlertIsTriggered(), "Email verification alert is not triggered");
		assertTrue(page.verifyInputEmailTurnsRed(), "The input field border is not red after the validation");
		cacheCampaignId();
	}
	
	/**
	 * @param pDataFile
	 * Verify that user can add multiple templates to a campaign
	 * 39846
	 */
	@Test
	@Parameters({"dataFile"})
	public void testVerifyMultipleTemplatesAreAdded(String pDataFile) {
		getPage("/campaigns/create");
		dataObject = getDataFile(pDataFile);
		assertTrue(page.clickAndSelectMultipleTemplate(), "Unable to select mutiple templates");
		fillCampaignNameAndDescriptionPreCondition(dataObject);
		assertTrue(page.getAddedTemplatesCount()==2, "Multiple Templates are not added in the campaign");
		cacheCampaignId();
	}
	
	/**
	 * Verify that recipient panel should not appear until changes are saved
	 * 39852
	 */
	@Test
	@Parameters({"dataFile"})
	public void testVerifyRecipientsOptionsAreVisibleAfterClickingSave(String pDataFile) {
		getPage("/campaigns/create");
		dataObject = getDataFile(pDataFile);
		selectTemplatePreCondition();
		assertFalse(page.verifyRecipientsOptionsAreVisible(), "Recipients options are visible before clicking on save button");
		fillCampaignNameAndDescriptionPreCondition(dataObject);
		assertTrue(page.verifyRecipientsOptionsAreVisible(), "Recipients options are not visible after clicking on save button");
	}
	
	/**
	 * Verify that user can rearrange priority of campaigns by drag and drop
	 * 39847
	 */
	@Test
	public void testVerifyCampaignPriorityGetsChanged() {
		String row_0_template_before = page.getRow0TemplateId();
		String row_1_template_before = page.getRow1TemplateId();
		assertTrue(page.dragRow1ToRow0(), "Unable to drag and drop row 01 of template");
		assertTrue(page.clickOnSaveButton(),"Unable to click on save button");
		assertTrue(new ZBOSucessAlert(driver).clickOnOkButton(), "Unable to click on OK button..");
		assertFalse(row_0_template_before.equalsIgnoreCase(page.getRow0TemplateId()), "Priority is not changed");
		assertFalse(row_1_template_before.equalsIgnoreCase(page.getRow1TemplateId()), "Priority is not changed");
	}
	
	/**
	 * Verify that user can provide number of days after previous templates it should be sent
	 * 39848
	 */
	@Test
	public void testVerifyUserCanProvideNumberOfDays() {
		assertTrue(page.typeNumberOfDaysInTemplate(page.getRow0TemplateId(), "2"), "Unable to type number of days for tempate 1");
		assertTrue(page.typeNumberOfDaysInTemplate(page.getRow1TemplateId(), "5"), "Unable to type number of days for tempate 1");
		assertTrue(page.clickOnSaveButton(),"Unable to click on save button");
		assertTrue(new ZBOSucessAlert(driver).clickOnOkButton(), "Unable to click on OK button..");
		assertTrue(page.getNumberOfDaysInTemplate(page.getRow0TemplateId()).equalsIgnoreCase("2"), "Number of days value not saved");
		assertTrue(page.getNumberOfDaysInTemplate(page.getRow1TemplateId()).equalsIgnoreCase("5"), "Number of days value not saved");
	}
	
	/**
	 * Verify that drag and drop icon should be shown with templates
	 * 39860
	 */
	@Test
	public void testVerifyDragDropIconVisible() {
		assertTrue(page.isDragDropIconVisible(), "Drag drop icon is not visible..");
	}
	
	/**
	 * @param pDataFile
	 * Verify override modal appears if leads are already enrolled in campaign
	 * 39864
	 */
	@Test
	@Parameters({"dataFile"})
	public void testVerifyOverirdeModalAppearsIfLeadsAreEnrolledInCampaign(String pDataFile) {
		addCampaignPreCondition(pDataFile);
		page=null;
		getPage("/campaigns/create");
		dataObject = getDataFile(pDataFile);
		selectTemplatePreCondition();
		fillCampaignNameAndDescriptionPreCondition(dataObject);
//		enrollLeadsInCampaignPreCondition();
		ActionHelper.staticWait(60);
		cacheCampaignId();
		assertTrue(page.clickOnAllLeadsStatusClient(), "Unable to click on recipients option..");
		assertTrue(page.clickOnMatchingLeadButton(), "Unable to click on matchin lead button..");
		ActionHelper.staticWait(5);
		assertTrue(page.getZboLeadListform().isLeadListForm(),"Lead list form is not opened..");
		int l_lead_count = page.getZboLeadListform().getLeadsListCount();
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadsCount, Integer.toString(l_lead_count));
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadsList, page.getMatchingLeads());
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadsListName, page.getLeadsName());
		assertTrue(page.getZboLeadListform().clickOnSaveButton(),"Unable to click on cancel button..");
		assertTrue(page.getSuccessAlert().waitForOverrideButton(), "Override modal is not displayed");	
	}
	
	/**
	 * Verify that override modal shows the count of leads to be overriden correctly
	 * 39867
	 */
	@Test
	public void testVerifyOverrideModalShowCorrectLeadCount() {
		String l_overirde_modal_text = page.getSuccessAlert().getOverrideModalText();
		String l_lead_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadsCount);
		assertTrue(l_overirde_modal_text.contains(l_lead_count),"Correct count of lead is not found "+l_lead_count);
	}
	
	/**
	 * Verify that if skipped then leads are kept enrolled in existing campaign
	 * 39866
	 */
	@Test
	public void testVerifySkipButtonDoesnotEnrollLeads() {
		assertTrue(page.getSuccessAlert().clickSkipButton(), "Unable to click on skip button..");
		assertTrue(new ZBOSucessAlert(driver).clickOnOkButton(), "Unable to click on OK button..");
		assertTrue(page.clickOnViewRecipientsButton(), "Unable to click on currently enrolled leads..");
		ActionHelper.staticWait(5);
		assertFalse(page.isLeadEnrolledInTheList(page.getMatchingLeads(), ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadsListName)), "Lead not found");	
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadsListName, page.getLeadsName());
	}
	
	@Test
	public void testVerifyOverrideButtonDoesEnrollLeads() {
		assertTrue(page.getZboLeadListform().clickOnSaveButton(),"Unable to click on cancel button..");
		assertTrue(page.clickOnEnrollButton(), "Unable to click on enroll bbutton");
		assertTrue(page.getSuccessAlert().clickOnOverrideButton(), "Unable to click on override button..");
		assertTrue(new ZBOSucessAlert(driver).clickOnOkButton(), "Unable to click on OK button..");
		ActionHelper.staticWait(60);
		assertTrue(page.clickOnViewRecipientsButton(), "Unable to click on currently enrolled leads..");
		ActionHelper.staticWait(5);
		assertTrue(page.isLeadEnrolledInTheList(page.getMatchingLeads(), ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadsListName)), "Lead not found");	

	}
	private void selectTemplatePreCondition() {
		if(!page.clickOnAddTemplateButton()) {
			throw new SkipException("Automation template cannot be added to campaign..");
		}
		ActionHelper.staticWait(2);
		if(!page.clickAndSelectAutoTemplate("Automation Template")) {
			throw new SkipException("Automation template cannot be added to campaign..");
		}
		ActionHelper.staticWait(2);
		if(!page.clickOnUpdateButton()) {
			throw new SkipException("Automation template cannot be added to campaign..");
		}
	}
	private void fillCampaignNameAndDescriptionPreCondition(JSONObject pDataObject) {
		String ld_campaignName = updateName(pDataObject.optString("campaign_name"));
		String ld_campaignDesc = updateName(pDataObject.optString("campaign_desc"));
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleCampaignName, ld_campaignName);
		try {
			assertTrue(page.typeCampaignName(ld_campaignName), "Unable to type campaign name..");
			assertTrue(page.typeCampaignDescription(ld_campaignDesc), "Unable to type campaign desc..");
			assertTrue(page.clickOnSaveButton(), "Unable to click on save button...");
			assertTrue(new ZBOSucessAlert(driver).isSuccessMessageVisible(), "Success message is not visible..");
			assertTrue(new ZBOSucessAlert(driver).clickOnOkButton(), "Unable to click on OK button..");
		}catch(Exception ex) {
			throw new SkipException("Campaign Name and description could not be saved..");
		}
	}
	private String getPlaceHolderValue() {
		String lPlaceholderValue = "";
		if(getIsProd()) {
			lPlaceholderValue = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url").replace("https://www.", "");

		}else {
			lPlaceholderValue = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url").replace("http://www.stage01.", "");
		}
		return lPlaceholderValue;
	}
	
	private boolean verifyEnrolledLeadCount() {
		String l_current_url = driver.getCurrentUrl();
		driver.navigate().to(l_current_url.split("/enroll")[0]);
		String l_campaign_name = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleCampaignName);
		int l_lead_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadsCount);
		boolean leadEnrolled = page.verifyLeadCount(l_campaign_name, l_lead_count);
		driver.navigate().to(l_current_url);
		return leadEnrolled;
	}
	private void enrollLeadsInCampaignPreCondition() {
		try {
			assertTrue(page.clickOnAllLeadsStatusClient(), "Unable to click on recipients option..");
			assertTrue(page.clickOnMatchingLeadButton(), "Unable to click on matchin lead button..");
			ActionHelper.staticWait(5);
			assertTrue(page.getZboLeadListform().isLeadListForm(),"Lead list form is not opened..");
			int l_lead_count = page.getZboLeadListform().getLeadsListCount();
			ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadsList, page.getMatchingLeads());
			ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadsCount, l_lead_count);
			ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadsListName, page.getLeadsName());
			assertTrue(page.getZboLeadListform().clickOnSaveButton(),"Unable to click on cancel button..");
			SoftAssert softAssert = new SoftAssert();
			ActionHelper.staticWait(3);
			softAssert.assertTrue(page.getSuccessAlert().clickOnOverrideButton());
			assertTrue(page.getSuccessAlert().isSuccessMessageVisible(), "Success message is not displayed");
			assertTrue(page.getSuccessAlert().clickOnOkButton(), "Unable to click on ok button");
		}catch(Exception ex) {
			throw new SkipException("Campaign Name and description could not be saved..");
		}
	}
	private void cacheCampaignId() {
		String lCampaign_ID = driver.getCurrentUrl().split("enroll/")[1];
		String lc_campaign_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleCampaignID);
		if(lc_campaign_id==null) {
			ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleCampaignID, lCampaign_ID);
		}else {
			ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleCampaignID, lCampaign_ID+","+lc_campaign_id);
		}
		
	}
	private void addCampaignPreCondition(String pDataFile) {
		getPage("/campaigns/create");
		dataObject = getDataFile(pDataFile);
		selectTemplatePreCondition();
		fillCampaignNameAndDescriptionPreCondition(dataObject);
		enrollLeadsInCampaignPreCondition();
		cacheCampaignId();
		assertTrue(page.clickOnSaveButton(), "Unable to save campaign");
	}
}
