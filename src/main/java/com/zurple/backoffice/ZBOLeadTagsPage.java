package com.zurple.backoffice;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOLeadTagsPage extends Page{
	boolean isRefreshPageRequired = true;
	
	@FindBy(className="fa-tags")
	WebElement groupTag;
	
	@FindBy(className="lead-assign")
	WebElement selectTag;
	
	@FindBy(xpath="//div[@id='lead-groups']/descendant::input[@value]")
	WebElement typeTagName;
	
	@FindBy(xpath="//div[@class='lead-tag-group-controls']/button[contains(@class,'plus-button') and not(contains(@style,'display: none'))]")
	WebElement plusTag;
	
	@FindBy(id="save-lead-groups")
	WebElement saveTag;
		
	@FindBy(xpath="//button[contains(text(),'Close')]")
	WebElement closeModal;
	
	@FindBy(id="lead-groups")
	WebElement modalMessage;
	
	@FindBy(className="lead-tag")
	WebElement leadTag;
	
	String countTags = "lead-tag";
	
	@FindBy(className="remove")
	WebElement removeTag;
	
	@FindBy(id="swal2-content")
	WebElement removeTagMessage;
	
	String leadName = "full_name";
	
	@FindBy(className="swal2-confirm")
	WebElement confirmRemoveTag;
	
	@FindBy(id="tag-lead-groups-button")
	WebElement addTagFromLeadDetails;
	
	public List<WebElement> agentsList;
	
	public ZBOLeadTagsPage() {
	}
	
	public ZBOLeadTagsPage(WebDriver pWebdriver) {
		driver = pWebdriver;
		PageFactory.initElements(driver, this);
	}
	
	public int countTags() {
		ActionHelper.staticWait(2);
		agentsList = ActionHelper.getListOfElementByClassName(driver, countTags);
		ActionHelper.staticWait(2);
		int count = agentsList.size();
		return count;
	}
	
	public boolean addTagFromLeadDetails() {
		ActionHelper.waitForElementToBeVisible(driver, addTagFromLeadDetails, 30);
		if(countTags()>0) {
			removingTag();
		}
		return ActionHelper.Click(driver, addTagFromLeadDetails);
	}
	
	public boolean addTagFromLeadsCRM() {
		ActionHelper.waitForElementToBeVisible(driver, groupTag, 30);
		if(countTags()>0) {
			removingTag();
		}
		return ActionHelper.Click(driver, groupTag);
	}
	
	public boolean addEmptyTag() {
		ActionHelper.waitForElementToBeVisible(driver, plusTag, 30);
		return ActionHelper.Click(driver, plusTag);
	}
	
	public boolean typeTagName() {
		ActionHelper.waitForElementToBeVisible(driver, typeTagName, 30);		
		return ActionHelper.ClearAndType(driver, typeTagName, "Auto-Tag");
	}
		
	public boolean selectingTag() {
		ActionHelper.waitForElementToBeVisible(driver, selectTag, 30);
		return ActionHelper.Click(driver, selectTag);
	}
	
	public boolean savingTag() {
		ActionHelper.waitForElementToBeVisible(driver, saveTag, 30);
		return ActionHelper.Click(driver, saveTag);
	}
	
	public String confirmationModalText() {
		ActionHelper.staticWait(5);
		return ActionHelper.getText(driver, modalMessage).trim();
	}
	
	public boolean confirmationModalClose() {
		ActionHelper.waitForElementToBeVisible(driver, closeModal, 30);
		return ActionHelper.Click(driver, closeModal);					
	}
	
	public String tagNameText() {
		ActionHelper.waitForElementToBeVisible(driver, leadTag, 30);
		return ActionHelper.getText(driver, leadTag).replace("x","").trim();
	}
	
	public boolean selectLeadName() {
		ActionHelper.staticWait(2);
		return ActionHelper.ClickByIndex(driver, leadName, 0);
	}
	
	public boolean removingTag() {
		ActionHelper.waitForElementToBeVisible(driver, removeTag, 30);
		return ActionHelper.Click(driver, removeTag);
	}
	
	public String confirmationModalRemoveTagText() {
		ActionHelper.waitForElementToBeVisible(driver, removeTagMessage, 30);
		return ActionHelper.getText(driver, removeTagMessage).trim();
	}
	
	public boolean confirmRemoveTag() {
		ActionHelper.waitForElementToBeVisible(driver, confirmRemoveTag, 30);
		return ActionHelper.Click(driver, confirmRemoveTag);
	}
	
}
