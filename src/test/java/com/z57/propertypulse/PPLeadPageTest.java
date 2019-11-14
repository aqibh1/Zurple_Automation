package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.hibernate.validator.AssertTrueValidator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.z57.propertypulse.PPLeadsPage;
import com.zurple.my.LeadDetailPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.data.z57.LeadData;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

public class PPLeadPageTest extends PageTest{
	WebDriver driver;
	private PPLeadsPage page;
	private String url = "/leads/manager";
	LeadData leadData;
	private String lLeadName;
	private String lPhoneNum;
	private String lEmail;
	private PPHeader header;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPLeadsPage(driver);
			header = new PPHeader(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new PPLeadsPage(driver);
			header = new PPHeader(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	private void setLeadData(LeadData pLeadData) {
		lLeadName=updateName(pLeadData.getLeadName());
		lPhoneNum=pLeadData.getLeadPhone();
		lEmail=updateEmail(pLeadData.getLeadEmail());
		leadData.setLeadEmail(lEmail);
	}
	
	@Parameters({"dataFile"})
	@Test
	public void testAddLead(String pDataFile) {
		leadData = new LeadData(pDataFile).getLeadData();
		setLeadData(leadData);
		ModuleCommonCache.setModuleCommonCache(leadData.getLeadEmail(), leadData);
		getPage(url);
		assertTrue(page.isLeadPage(), "Lead page is not opened");
		assertTrue(page.clickOnManualEntryDropDown(), "Unable to click on Manual Entry drop down button");
		assertTrue(page.getAddNewLeadForm().isAddNewLeadFormVisible(), "Add New Lead form is not displayed");
		assertTrue(page.getAddNewLeadForm().typeLeadName(lLeadName), "Unable to type lead name ["+lLeadName+"] in the [*Name:] field.");
		assertTrue(page.getAddNewLeadForm().typeLeadEmail(lEmail), "Unable to type lead email ["+lEmail+"] in the [*Email:] field.");
		if(!lPhoneNum.isEmpty()) {
			assertTrue(page.getAddNewLeadForm().typeLeadPhone(lPhoneNum), "Unable to type lead phone ["+lPhoneNum+"] in the [Phone:] field.");
		}
		assertTrue(page.getAddNewLeadForm().clickOnContinueButton(), "Unable to click on Continue button.");
		//assertTrue(page.getAddNewLeadForm().isLeadAddedSuccessfully(), "Lead is not added. Form is still visible.");	
		
		DBHelperMethods dbHelperMethods = new DBHelperMethods(getEnvironment());
		assertTrue(dbHelperMethods.verifyLeadByEmailInDB(lEmail), "Lead is not added. Verification from DB failed.");
		
		PPLeadsDetailPage leadDetailPage = new PPLeadsDetailPage(driver);
		assertTrue(leadDetailPage.isLeadDetailsPage(), "Lead Details Page is not visible.");
		if(!leadData.getLeadStatus().isEmpty()) {
			assertTrue(leadDetailPage.selectLeadStatus(leadData.getLeadStatus()), "Unable to select Lead's status.");
		}
		assertTrue(leadDetailPage.clickOnSaveButton(), "Unable to click on Save button.");
		assertTrue(leadDetailPage.isLeadUpdatedSuccessfully(), "Unable to update Lead.");
		
		assertTrue(header.clickOnLeads(), "Unable to click on Lead Tab.");
		assertTrue(page.isLeadPage(), "Lead page is not opened");
		
		applyFilters();
		assertTrue(page.isLeadExistInTable(lEmail), "The lead doesn't exist in filter search. Lead Email ["+leadData.getLeadEmail()+"]");
		//Resets the filter
		assertTrue(page.clickOnResetFilterButton(), "Unable to click on reset button");
		assertTrue(page.typeInSearch(lEmail), "Unable to type in Search Field");
		assertTrue(page.isLeadExistInTable(lEmail), "The lead doesn't exist in filter search. Lead Email ["+leadData.getLeadEmail()+"]");
		
		assertTrue(page.clickOnEditButton(), "Unable to click on Edit button");
	}
	@Test(priority=1)
	@Parameters({"dataFile"})
	public void testSearchLead(String pDataFile) {
		leadData = new LeadData(pDataFile).getLeadData();
		if(ModuleCommonCache.getModuleCommonCache(leadData.getLeadEmail())!=null) {
			leadData =(LeadData) ModuleCommonCache.getModuleCommonCache(leadData.getLeadEmail());
			page = null;
		}else {
			setLeadData(leadData);
		}
		getPage(url);
		applyFilters();
		assertTrue(page.isLeadExistInTable(leadData.getLeadEmail()), "The lead doesn't exist in filter search. Lead Email ["+leadData.getLeadEmail()+"]");
		//Resets the filter
		assertTrue(page.clickOnResetFilterButton(), "Unable to click on reset button");
		assertTrue(page.typeInSearch(leadData.getLeadEmail()), "Unable to type in Search Field");
		assertTrue(page.isLeadExistInTable(leadData.getLeadEmail()), "The lead doesn't exist in filter search. Lead Email ["+leadData.getLeadEmail()+"]");	
	}
	
	@Test
	public void testSearchAndDeleteLead() {
		AutomationLogger.startTestCase("Search and Delete Lead");
		getPage("/leads");
		String lLeadEmail = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.FacebookLeadEmail);
		assertTrue(page.isLeadPage(), "Lead page is not visible...");
		ActionHelper.waitForAjaxToBeCompleted(driver);
		assertTrue(page.typeInSearch(lLeadEmail), "Unable to type in Search Field");
		ActionHelper.waitForAjaxToBeCompleted(driver);
		assertTrue(page.isLeadExistInTable(lLeadEmail), "The lead doesn't exist in filter search. Lead Email ["+lLeadEmail+"]");	
		assertTrue(page.deleteLead(),"Unable to delete the lead "+lEmail);
		AutomationLogger.endTestCase();
		
	}
	
	private void applyFilters() {
		assertTrue(page.clickOnFilterArrow(), "Filters options are not visible");
		assertTrue(page.selectSourcesOption(), "Unable to select the Sources option");
		assertTrue(page.selectStatus(leadData.getLeadStatus()), "Unable to select the Status option");
		assertTrue(page.clickOnApplyFilterButton(), "Unable to click on Apply button");

	}
	
	@Test(priority=2)
	@Parameters({"dataFile"})
	public void testEditLead(String pDataFile) {
		getPage();
		assertTrue(page.clickOnEditButton(), "Unable to click on Edit button");
		
	}

}
