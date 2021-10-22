package com.zurple.backoffice.marketing;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

public class ZBOTemplateManagerPage extends Page{
	
	@FindBy(xpath="//h1[text()='Template Manager']")
	WebElement templateManager_heading;
	
	@FindBy(id="template-create-button")
	WebElement createTemplate_button;
	
	String template_row = "//a[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::tr[@data-template-id]";
	
	@FindBy(xpath="//li[@id='templates-table_next' and @class='paginate_button next']/a[text()='Next']")
	WebElement next_button;
	
	String template_list = "//table[@id='templates-table']/descendant::a[@class='template-title']";
	
	@FindBy(id="preview_box")
	WebElement preview_box;
	
	@FindBy(xpath="//table[@id='templates-table']/descendant::th[contains(@aria-label,'Template Name')]")
	WebElement template_name_sorting;
	@FindBy(xpath="//table[@id='templates-table']/descendant::th[contains(@aria-label,'Created By')]")
	WebElement created_by_sorting;
	@FindBy(xpath="//table[@id='templates-table']/descendant::th[contains(@aria-label,'Subject')]")
	WebElement subject_sorting;
	@FindBy(xpath="//table[@id='templates-table']/descendant::th[contains(@aria-label,'Type')]")
	WebElement type_sorting;
	@FindBy(xpath="//table[@id='templates-table']/descendant::th[@aria-sort]")
	WebElement template_name_sorting_activated;
	@FindBy(xpath="//table[@id='templates-table']/descendant::th[@aria-sort]")
	WebElement created_by_sorting_activated;
	@FindBy(xpath="//table[@id='templates-table']/descendant::th[@aria-sort]")
	WebElement subject_sorting_activated;
	@FindBy(xpath="//table[@id='templates-table']/descendant::th[@aria-sort]")
	WebElement type_sorting_activated;
	
	String disabled_edit_button = "//tr/descendant::td[contains(text(),'Zurple')]/following::a[@class='btn z-btn-inactive btn-default ' and text()='Edit']";
	
	
	public ZBOTemplateManagerPage() {
		
	}
	public ZBOTemplateManagerPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isTemplateManagerPageVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, templateManager_heading, 15);
	}
	public boolean clickOnCreateTemplateButton() {
		return ActionHelper.Click(driver, createTemplate_button);
	}
	public boolean clickOnEditButton(String pTemplateName) {
		boolean isClicked = false;
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, template_row, pTemplateName);
		if(element!=null) {
			isClicked = ActionHelper.Click(driver, element.findElement(By.xpath("/descendant::a[text()='Edit']")));
		}
		return isClicked;
	}
	public boolean searchAndClickEditButton(String pTemplateName) {
		AutomationLogger.info("Template Name :: "+pTemplateName );
		boolean isClicked = false;
		do {
		
			isClicked = clickOnEditButton(pTemplateName);
			if(!isClicked) {
				ActionHelper.Click(driver, next_button);
				ActionHelper.staticWait(10);
			}else {
				break;
			}
		}while(ActionHelper.isElementVisible(driver, next_button));
		return isClicked;
	}
	public boolean isTemplateNameLinkVisible() {
		return ActionHelper.getListOfElementByXpath(driver, template_list).size()>0;
	}
	public boolean isPreviewDisplayed() {
		boolean isDisplayed = false;
		List<WebElement> list_elements = ActionHelper.getListOfElementByXpath(driver, template_list);
		int l_random_num = generateRandomInt(list_elements.size());
		if(ActionHelper.Click(driver, list_elements.get(l_random_num))) {
			isDisplayed = ActionHelper.isElementVisible(driver, preview_box);
		}
		return isDisplayed;
	}
	public boolean isTemplateNameSortingWorking() {
		return verifySortingIsWorking(template_name_sorting, template_name_sorting_activated);
	}public boolean isCreatedBySortingWorking() {
		return verifySortingIsWorking(created_by_sorting, created_by_sorting_activated);
	}public boolean isSubjectSortingWorking() {
		return verifySortingIsWorking(subject_sorting, subject_sorting_activated);
	}public boolean isTypeSortingWorking() {
		return verifySortingIsWorking(type_sorting, type_sorting_activated);
	}
	public boolean verifyEditButtonIsDisabledForGlobalTemplates() {
		return verifyEditButtonIsDisabledForGlobalTemplates(disabled_edit_button);
	}
	private boolean verifySortingIsWorking(WebElement pColumnName, WebElement pSortingActivated) {
		boolean isSortingActivated = false;
		if(ActionHelper.Click(driver, pColumnName)) {
			isSortingActivated = ActionHelper.isElementVisible(driver, pSortingActivated);
		}
		return isSortingActivated;
	}
	private boolean verifyEditButtonIsDisabledForGlobalTemplates(String pElement) {
		boolean isVerifed = false;
		do {
			if(ActionHelper.getListOfElementByXpath(driver, pElement).size()>0) {
				isVerifed = true;
			}else {
				ActionHelper.Click(driver, next_button);
			}
		}while(ActionHelper.isElementVisible(driver, next_button) && !isVerifed);
		return isVerifed;
	}
}
