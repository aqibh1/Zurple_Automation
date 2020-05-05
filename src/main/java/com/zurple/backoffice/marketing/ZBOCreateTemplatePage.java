/**
 * 
 */
package com.zurple.backoffice.marketing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.forms.zurple.backoffice.ZBOAttachFileForm;
import resources.forms.zurple.backoffice.ZBOInsertImageForm;
import resources.forms.zurple.backoffice.ZBOPlaceHolderForm;
import resources.forms.zurple.backoffice.ZBOPreviewForm;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOCreateTemplatePage extends Page {
	
	@FindBy(xpath="//h3[text()='Create Template']")
	WebElement createTemplate_heading;
	
	@FindBy(id="title")
	WebElement templateName_input;
	
	@FindBy(id="subject")
	WebElement templateSubject_input;
	
	@FindBy(xpath="//html/descendant::body[@contenteditable='true']")
	WebElement templateBody_html;
	
	@FindBy(id="add-placeholder")
	WebElement placeHolder_button;
	
	@FindBy(xpath="//label[@for='attachment']")
	WebElement attachFile_button;
	
	@FindBy(id="attachment-remove")
	WebElement attachment_remove_button;
	
	@FindBy(id="cke_19")
	WebElement insertImage_button;
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, body']")
	WebElement emailBody_iframe;
	
	@FindBy(id="save-template")
	WebElement save_template_button;
	
	ZBOPlaceHolderForm zboPlaceholderForm;
	ZBOPreviewForm zboPreviewForm;
	ZBOInsertImageForm zboInsertImageForm;
	ZBOAttachFileForm zboAttachFileForm;
	ZBOSucessAlert zboSuccessAlert;
	
	public ZBOCreateTemplatePage() {
		
	}
	public ZBOCreateTemplatePage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setZboPlaceholderForm();
		setZboPreviewForm();
		setZboInsertImageForm();
		setZboAttachFileForm();
		setZboSuccessAlert();
		PageFactory.initElements(driver, this);
	}
	
	public ZBOSucessAlert getZboSuccessAlert() {
		return zboSuccessAlert;
	}
	public void setZboSuccessAlert() {
		this.zboSuccessAlert = new ZBOSucessAlert(driver);
	}
	public ZBOAttachFileForm getZboAttachFileForm() {
		return zboAttachFileForm;
	}
	public void setZboAttachFileForm() {
		this.zboAttachFileForm = new ZBOAttachFileForm(driver);
	}
	public ZBOInsertImageForm getZboInsertImageForm() {
		return zboInsertImageForm;
	}
	public void setZboInsertImageForm() {
		this.zboInsertImageForm = new ZBOInsertImageForm(driver);
	}
	public void setZboInsertImageForm(WebDriver pWebDriver) {
		this.zboInsertImageForm = new ZBOInsertImageForm(pWebDriver);
	}
	public ZBOPreviewForm getZboPreviewForm() {
		return zboPreviewForm;
	}
	public void setZboPreviewForm() {
		this.zboPreviewForm = new ZBOPreviewForm(driver);
	}
	public ZBOPlaceHolderForm getZboPlaceholderForm() {
		return zboPlaceholderForm;
	}
	public void setZboPlaceholderForm() {
		zboPlaceholderForm = new ZBOPlaceHolderForm(driver);
	}
	public boolean isCreateTemplatePage() {
		return ActionHelper.waitForElementToBeVisible(driver, createTemplate_heading, 15);
	}
	public boolean typeTemplateName(String pTemplateName) {
		return ActionHelper.ClearAndType(driver, templateName_input, pTemplateName);
	}
	public boolean typeTemplateSubject(String pTemplateSubject) {
		return ActionHelper.ClearAndType(driver, templateSubject_input, pTemplateSubject);
	}
	public boolean typeTemplateBody(String pEmailBody) {
		boolean isSuccess = false;
		//We need to switch to iFrame to perform these operations
		driver.switchTo().frame(emailBody_iframe);
		if(ActionHelper.ClearAndType(driver, templateBody_html, pEmailBody)) {
			isSuccess = true;
			//Swicthing back to default content
			driver.switchTo().defaultContent();
		}
		return isSuccess;
	}
	public boolean clickOnPlaceHolderButton() {
		return ActionHelper.Click(driver, placeHolder_button);
	}
	public boolean clickOnAttachFileButton() {
		return ActionHelper.Click(driver, attachFile_button);
	}
	public boolean isAttachmentRemoveButtonVisible() {
		return ActionHelper.isElementVisible(driver, attachment_remove_button);
	}
	public boolean clickOnInsertImageButton() {
		return ActionHelper.Click(driver, insertImage_button);
	}
	public boolean clickOnSaveTemplateButton() {
		return ActionHelper.Click(driver, save_template_button);
	}
}