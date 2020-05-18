package com.zurple.backoffice;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class ZBOLeadTagsTest extends PageTest{
WebDriver driver;
ZBOLeadTagsPage page;

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
	addLeadTag(1);
	removeConfirmationModal();
	ActionHelper.RefreshPage(driver);
	assertEquals(page.tagNameText(), "Auto-Tag");
	deleteTag();
	ActionHelper.RefreshPage(driver);
	AutomationLogger.endTestCase();
}

@Test
public void addRemoveTagFromLeadDetails() {
	AutomationLogger.startTestCase("Lead Tags");
	getPage("/leads/crm");
	page.selectLeadName();
	driver.close();
	ActionHelper.switchToOriginalWindow(driver);
	addLeadTag(2);
	removeConfirmationModal();
	ActionHelper.RefreshPage(driver);
	assertEquals(page.tagNameText(), "Auto-Tag");
	deleteTag();
	ActionHelper.RefreshPage(driver);
	AutomationLogger.endTestCase();
}

public void addLeadTag(int choice) {
	if(choice==1) {
		assertTrue(page.addTagFromLeadsCRM(), "Unable to add Tag...");
	} else {
		assertTrue(page.addTagFromLeadDetails(), "Unable to add Tag...");
	}
	assertTrue(page.addEmptyTag(), "Unable to add Empty Tag...");
	assertTrue(page.typeTagName(), "Unable to type Tag Name...");
	assertTrue(page.selectingTag(), "Unable to select Tag...");
	assertTrue(page.savingTag(), "Unable to save Tag...");
}

public void removeConfirmationModal() {
	assertEquals(page.confirmationModalText(), "The Lead Tag Groups have successfully saved.");
	assertTrue(page.confirmationModalClose(), "Unable to confirm Tag Addition...");
}

public void deleteTag() {
	assertTrue(page.removingTag(), "Unable to remove Tag...");
	assertEquals(page.confirmationModalRemoveTagText(), "This will remove this lead from " +page.tagNameText()+ " group");
	assertTrue(page.confirmRemoveTag(), "Unable to confirm Tag Removal...");
}

}
