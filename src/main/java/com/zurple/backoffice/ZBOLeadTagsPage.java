package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOLeadTagsPage extends Page {
	boolean isRefreshPageRequired = true;
	
	@FindBy(className="fa-tags")
	WebElement groupTag;
	
	String selectTag = "lead-assign";
	
	@FindBy(xpath="//div[@id='lead-groups']/descendant::input[@type='text']")
	WebElement typeTagName;
	
	String selectingTag = "//div[@id='lead-groups']/descendant::input[@type='text']";
	
	String leadTagFields = "//div[@id='lead-groups']/descendant::input[@type='text']";
	
	@FindBy(xpath="//div[@class='lead-tag-group-controls']/button[contains(@class,'plus-button') and not(contains(@style,'display: none'))]")
	WebElement plusTag;
	
	@FindBy(xpath="//div[@class='lead-tag-group-controls']/button[contains(@class,'minus-button') and not(contains(@style,'display: none'))]")
	WebElement minusTag;
		
	String removeTagMinusButton = "minus-button";
	
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
	
	public List<WebElement> groupTagsList;
	
	public ZBOLeadTagsPage() {
	}
	
	public ZBOLeadTagsPage(WebDriver pWebdriver) {
		driver = pWebdriver;
		PageFactory.initElements(driver, this);
	}
	
	public int countTags() {
		ActionHelper.staticWait(2);
		groupTagsList = ActionHelper.getListOfElementByClassName(driver, countTags);
		ActionHelper.staticWait(2);
		int count = groupTagsList.size();
		return count;
	}
	
	public boolean addTagFromLeadDetails() {
		ActionHelper.waitForElementToBeVisible(driver, addTagFromLeadDetails, 30);
		return ActionHelper.Click(driver, addTagFromLeadDetails);
	}
	
	public boolean addTagFromLeadsCRM() {
		ActionHelper.waitForElementToBeVisible(driver, groupTag, 30);
		return ActionHelper.Click(driver, groupTag);
	}
	
	public boolean addEmptyTag() {
		ActionHelper.waitForElementToBeVisible(driver, plusTag, 30);
		return ActionHelper.Click(driver, plusTag);
	}
	
	public boolean removeUsedTag() {
		ActionHelper.waitForStringXpathToBeVisible(driver, selectingTag, 30);
		int position = ActionHelper.getListOfElementByXpath(driver, selectingTag).size()-1;
		return ActionHelper.ClickByIndex(driver, removeTagMinusButton, position);
	}
		
	public boolean selectingTag() {
		ActionHelper.waitForStringXpathToBeVisible(driver, selectingTag, 30);
		int position = ActionHelper.getListOfElementByXpath(driver, selectingTag).size()-1;
		return ActionHelper.ClickByIndex(driver, selectTag, position);
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
	
	public boolean switchToLeadDetailsPage() {
		try {
			selectLeadName();
			driver.close();
			ActionHelper.switchToOriginalWindow(driver);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}		
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
	
	public boolean typeTagName(String tagNameText) {
		ActionHelper.waitForElementToBeVisible(driver, typeTagName, 30);
		String str = "";
		List<WebElement> tagsList = ActionHelper.getListOfElementByXpath(driver, leadTagFields);
		ActionHelper.staticWait(2);
		for(WebElement element:tagsList) {
			str = ActionHelper.getTextByValue(driver, element);
			if(str.equals("")) {
				return ActionHelper.Type(driver, element, tagNameText);
			}
		}
		return false;
	}
	
	public void addLeadTag(int choice) {
		if(choice==1) {
			assertTrue(addTagFromLeadsCRM(), "Unable to add Tag...");
		} else {
			assertTrue(addTagFromLeadDetails(), "Unable to add Tag...");
		}
		
	}

	public boolean createLeadTag(String updatedTagname, int choice) {
		try {
			addLeadTag(choice);
			assertTrue(addEmptyTag(), "Unable to add Empty Tag...");
			assertTrue(typeTagName(updatedTagname), "Unable to type Tag Name...");
			assertTrue(selectingTag(), "Unable to select Tag...");
			assertTrue(savingTag(), "Unable to save Tag...");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addTagConfirmationModal() {
		try {
			assertEquals(confirmationModalText(), "The Lead Tag Groups have successfully saved.");
			assertTrue(confirmationModalClose(), "Unable to confirm Tag Addition...");
			ActionHelper.RefreshPage(driver);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteTag() {
		try {
			assertTrue(removingTag(), "Unable to remove Tag...");
			assertEquals(confirmationModalRemoveTagText(), "This will remove this lead from " +tagNameText()+ " group");
			assertTrue(confirmRemoveTag(), "Unable to confirm remove Tag...");
			ActionHelper.RefreshPage(driver);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean removeUsedTag(int choice) {
		try {
			addLeadTag(choice);
			assertTrue(removeUsedTag(), "Unable to remove Tag...");
			assertEquals(confirmationModalRemoveTagText(), "You are about to delete your group. Please cancel if you do not wish to do this.");
			assertTrue(confirmRemoveTag(), "Unable to confirm remove Tag...");
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
