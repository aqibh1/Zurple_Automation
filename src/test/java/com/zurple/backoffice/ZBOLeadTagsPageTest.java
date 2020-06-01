package com.zurple.backoffice;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class ZBOLeadTagsPageTest extends PageTest{
WebDriver driver;
ZBOLeadTagsPage page;
String updatedTagname = "";

@Override
public AbstractPage getPage() {
	// TODO Auto-generated method stub
	if(page==null) {
		driver = getDriver();
		page = new ZBOLeadTagsPage(driver);
		page.setUrl("");
	}
	return page;
}

public AbstractPage getPage(String pUrl) {
	if(page==null) {
		driver = getDriver();
		page = new ZBOLeadTagsPage(driver);
		page.setUrl(pUrl);
		page.setDriver(driver);
	}
	return page;
}

@Override
public void clearPage() {
	// TODO Auto-generated method stub
	
}

@Test
public void addRemoveTagFromLeadsCRM() {
	AutomationLogger.startTestCase("Lead Tags");
	getPage("/leads/crm");
	assertTrue(page.addTagFromLeadsCRM(), "Unable to add Tag...");
	createLeadTag();
	addTagConfirmationModal();
	ActionHelper.RefreshPage(driver);
	assertEquals(page.tagNameText(), updatedTagname);
	deleteTag();
	ActionHelper.RefreshPage(driver);
	removeUsedTag(1);
	AutomationLogger.endTestCase();
}

@Test
public void addRemoveTagFromLeadDetails() {
	AutomationLogger.startTestCase("Lead Tags");
	getPage("/leads/crm");
	page.selectLeadName();
	driver.close();
	ActionHelper.switchToOriginalWindow(driver);
	assertTrue(page.addTagFromLeadDetails(), "Unable to add Tag...");
	createLeadTag();
	addTagConfirmationModal();
	ActionHelper.RefreshPage(driver);
	assertEquals(page.tagNameText(), updatedTagname);
	deleteTag();
	ActionHelper.RefreshPage(driver);
	removeUsedTag(2);
	AutomationLogger.endTestCase();
}

public void addLeadTag(int choice) {
	if(choice==1) {
		assertTrue(page.addTagFromLeadsCRM(), "Unable to add Tag...");
	} else {
		assertTrue(page.addTagFromLeadDetails(), "Unable to add Tag...");
	}
}

public void createLeadTag() {
	assertTrue(page.addEmptyTag(), "Unable to add Empty Tag...");
	updatedTagname = updateName("Auto").replaceAll("\\s+","");
	assertTrue(page.typeTagName(updatedTagname), "Unable to type Tag Name...");
	try {
		assertTrue(page.selectingTag(), "Unable to select Tag...");
	} catch (Exception e) {
		e.printStackTrace();
	}
	assertTrue(page.savingTag(), "Unable to save Tag...");
}

public void addTagConfirmationModal() {
	assertEquals(page.confirmationModalText(), "The Lead Tag Groups have successfully saved.");
	assertTrue(page.confirmationModalClose(), "Unable to confirm Tag Addition...");
}

public void deleteTag() {
	assertTrue(page.removingTag(), "Unable to remove Tag...");
	assertEquals(page.confirmationModalRemoveTagText(), "This will remove this lead from " +page.tagNameText()+ " group");
	assertTrue(page.confirmRemoveTag(), "Unable to confirm remove Tag...");
}

public void removeUsedTag(int choice) {
	addLeadTag(choice);
	assertTrue(page.removeUsedTag(), "Unable to remove Tag...");
	assertEquals(page.confirmationModalRemoveTagText(), "You are about to delete your group. Please cancel if you do not wish to do this.");
	assertTrue(page.confirmRemoveTag(), "Unable to confirm remove Tag...");
}

}
