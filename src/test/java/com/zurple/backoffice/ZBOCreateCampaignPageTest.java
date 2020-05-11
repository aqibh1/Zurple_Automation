package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.backoffice.marketing.ZBOCreateCampaignPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;

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
	}

}
