/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.backoffice.marketing.ZBOCreateTemplatePage;
import com.zurple.backoffice.marketing.ZBOTemplateManagerPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOCreateTemplatePageTest extends PageTest{

	ZBOCreateTemplatePage page;
	WebDriver driver;
	private JSONObject dataObject;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreateTemplatePage(driver);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreateTemplatePage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	//retryAnalyzer = resources.RetryFailedTestCases.class
	@Test(groups="com.zurple.backoffice.ZBOCreateTemplatePageTest.testCreateTemplate")
	@Parameters({"templateData"})
	public void testCreateTemplate(String pDataFile) {
		getPage("/marketing/templates");
		dataObject = getDataFile(pDataFile);
		
		String lTemplateName = updateName(dataObject.optString("template_name"));
		String lTemplateSubject = updateName(dataObject.optString("template_subject"));
		
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleTemplateName, lTemplateName);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleTemplateSubject, lTemplateSubject);
		
		ZBOTemplateManagerPage templateManagerPage = new ZBOTemplateManagerPage(driver);
		assertTrue(templateManagerPage.isTemplateManagerPageVisible(), "Template Manager page is not visible...");
		assertTrue(templateManagerPage.clickOnCreateTemplateButton(), "Unable to click on Create Template button...");
		
		ActionHelper.switchToSecondWindow(driver);
		
		assertTrue(page.isCreateTemplatePage(), "Create template page is not visible..");
		assertTrue(page.typeTemplateName(lTemplateName), "Unable to type template name..");
		assertTrue(page.typeTemplateSubject(lTemplateSubject), "Unable to type template subject..");
		assertTrue(page.typeTemplateBody(dataObject.optString("template_body")), "Unable to type template body");
		String lTemplateBody = dataObject.optString("template_body").split("%")[0].trim();
		String lPlaceholderValue = "";
		if(getIsProd()) {
			lPlaceholderValue = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url").replace("https://www.", "");

		}else {
			lPlaceholderValue = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url").replace("http://www.stage01.", "");
		}
				
		
		//For Attachment of the file
		if(dataObject.optString("file_path")!=null && !dataObject.optString("file_path").isEmpty()) {
			assertTrue(page.clickOnAttachFileButton(), "Unable to click on attach file button..");
			ActionHelper.staticWait(2);
			page.getZboAttachFileForm().switchToBrowserToNewWindow();
			ActionHelper.staticWait(10);
			assertTrue(page.getZboAttachFileForm().clickAndSelectFile(), "Unable to select the file from upload form ..");
			ActionHelper.staticWait(5);
			page.getZboAttachFileForm().switchToOriginalWindow();
			ActionHelper.staticWait(5);
			
			assertTrue(page.isAttachmentRemoveButtonVisible(), "Remove button after attaching file is not visible..");
		}
		//Inserting image in the body
		if(dataObject.optString("image_path")!=null && !dataObject.optString("image_path").isEmpty()) {
			assertTrue(page.clickOnInsertImageButton(), "Unable to click on insert image button..");
			ActionHelper.staticWait(5);
			ActionHelper.switchToSecondWindowByIndex(driver, 1);
			page.setZboInsertImageForm(driver);
			page.getZboInsertImageForm().insertImage(dataObject.optString("image_path"));
			ActionHelper.switchToOriginalWindow(driver);
		}
		assertTrue(page.clickOnPlaceHolderButton(), "Unable to click on Placeholder button..");
		assertTrue(page.getZboPlaceholderForm().isPlaceHolderFromVisible(), "Placeholder form is not visible..");
		assertTrue(page.getZboPlaceholderForm().countAllThePlaceHolders(), "The placeholders are not visible..");
		assertTrue(page.getZboPlaceholderForm().closePlaceHolder(), "Unable to close the placeholder dialog..");
		
		//Preview verifications
		assertTrue(page.getZboPreviewForm().clickOnPreviewTemplateButton(), "Unable to click on preview button..");
		assertTrue(page.getZboPreviewForm().isPreviewHeadingVisibleTemplate(), "Preview heading is not visible..");
		page.getZboPreviewForm().verificationOfPreviewContent(dataObject.optString("template_subject"), lTemplateBody, true, dataObject.optString("image_path"), lPlaceholderValue);
		assertTrue(page.clickOnSaveTemplateButton(), "Unable to click on save button");
		assertTrue(page.getZboSuccessAlert().isSuccessMessageVisible(), "Success alert message is not visible..");
		assertTrue(page.getZboSuccessAlert().clickOnOkButton(), "Unable to click on OK button..");
	}
	
	@Test(dependsOnGroups="com.zurple.backoffice.ZBOCreateTemplatePageTest.testCreateTemplate")
	public void testDeleteTemplate() {
		page = null;
		getPage("/marketing/templates");
		String lTemplate_Name = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleTemplateName);
		
		ZBOTemplateManagerPage templateManagerPage = new ZBOTemplateManagerPage(driver);
		
		assertTrue(templateManagerPage.searchAndClickEditButton(lTemplate_Name), "Unable to find template..");
		assertTrue(page.clickOnDeleteTemplateButton(), "Unable to delete the template..");
	}
	
	@Test(dependsOnGroups="com.zurple.backoffice.ZBOCreateTemplatePageTest.testCreateTemplate")
	public void testVerifyTemplateIsDeletedFromTheList() {
		page=null;
		getPage("/marketing/templates/create");
		String lTemplate_Name = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleTemplateName);
		assertFalse(page.selectExistingTempplate(lTemplate_Name), "Template is present in the list after deletion");
	}
	
	@Test //39814
	public void testVerifyValidationAlertsAreTriggered() {
		getPage("/marketing/templates/create");
		assertTrue(page.clickOnSaveTemplateButton(), "Unable to click on save template button..");
		ActionHelper.staticWait(5);
		assertTrue(page.verifyTemplateNameValidationAlertIsTriggered("Please enter a name for your template."), "Template name validation is not triggered");
		assertTrue(page.verifyTemplateNameValidationAlertIsTriggered("Please enter a subject for your template."), "Template subject validation is not triggered");
		assertTrue(page.verifyTemplateNameValidationAlertIsTriggered("Please enter template body."), "Template body validation is not triggered");

	}
	/**
	 * Verify that user can select existing templates from drop down to edit
	 * 39809
	 */
	@Test
	public void testVerifyTemplateIsSelectable() {
		assertTrue(page.selectExistingTempplate("Test Automation"), "Unable to select test automation template from dropdown");
		assertTrue(page.isTemplateInputEnabled(), "Template Name input is not enabled..");
		assertTrue(page.isTemplateSubjectInputEnabled(), "Template Subject input is not enabled..");
		assertTrue(page.isTemplateBodyInputEnabled(), "Template Body input is not enabled..");
	}
	/**
	 * Verify that all data populate as added when existing template is selected on templates page
	 * 39816
	 */
	@Test
	public void testVerifyExistingTemplatePopulatesTheFields() {
		assertTrue(page.isTemplateNamePopulated(), "Template name input is not populated..");
		assertTrue(page.isTemplateSubjectPopulated(), "Template subject input is not populated");
		assertTrue(page.isTemplateBodyPopulated(), "Template body input is not populated");
	}
	
}
