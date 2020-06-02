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
String updatedTagName = updateName("Auto").replaceAll("\\s+","");

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
	assertTrue(page.createLeadTag(updatedTagName,1), "unable to create a new lead tag");
	assertTrue(page.addTagConfirmationModal(),"unable to confirm and close confirmation modal");
	assertEquals(page.tagNameText(), updatedTagName);
	assertTrue(page.deleteTag(),"unable to delete added tag to a lead");
	assertTrue(page.removeUsedTag(1),"unable to remove used tag");
	AutomationLogger.endTestCase();
}

@Test
public void addRemoveTagFromLeadDetails() {
	AutomationLogger.startTestCase("Lead Tags");
	getPage("/leads/crm");
	assertTrue(page.switchToLeadDetailsPage(),"unable to switch to lead details page from lead details");
	assertTrue(page.createLeadTag(updatedTagName,2),"unable to create a new lead tag from lead details");
	assertTrue(page.addTagConfirmationModal(),"unable to confirm and close confirmation modal from lead details");
	assertEquals(page.tagNameText(), updatedTagName);
	assertTrue(page.deleteTag(),"unable to delete added tag to a lead from lead details");
	assertTrue(page.removeUsedTag(2),"unable to remove used tag from lead details");
	AutomationLogger.endTestCase();
}

}
