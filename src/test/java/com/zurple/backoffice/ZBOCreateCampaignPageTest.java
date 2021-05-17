package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.backoffice.marketing.ZBOCampaignPage;
import com.zurple.backoffice.marketing.ZBOCreateCampaignPage;
import com.zurple.backoffice.marketing.ZBOCreateTemplatePage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.utility.ActionHelper;

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
		page = null;
		getPage("/campaigns/enroll/"+lc_campaign_id);
		ZBOCampaignPage campaignPage = new ZBOCampaignPage(driver);
		assertTrue(campaignPage.isCampaignDetailPage(), "Campaign Page is not visible..");
		assertTrue(campaignPage.deleteCampaign(), "Unable to delete the campaign..");
		
	}
}
