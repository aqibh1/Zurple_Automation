/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.admin.ZACampaignTemplatesPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;

/**
 * @author adar
 *
 */
public class ZACampaignTemplatesPageTest extends PageTest{
	
	private WebDriver driver;
	private JSONObject dataObject;
	private ZACampaignTemplatesPage page;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZACampaignTemplatesPage(driver);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZACampaignTemplatesPage(driver);
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
	public void testInActiveTemplate() {
		getPage("/admin/campaigntemplates");
		String lTemplateSubject = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleTemplateSubject);
		assertTrue(page.isCampaignTemplatesPage(), "Campaign Template page is not visible..");
		assertTrue(page.selectTemplateToDeActive(lTemplateSubject), "Unable to select the template..");
		assertTrue(page.toggleCheckbox(false), "Unable to uncheck the Active checkbox..");
		assertTrue(page.clickOnSaveButton(), "Unable to click on save button..");
	}
}
