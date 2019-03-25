package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.hibernate.validator.AssertTrueValidator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.z57.propertypulse.PPLeadsPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.ModuleCommonCache;
import resources.data.z57.LeadData;

public class PPLeadPageTest extends PageTest{
	WebDriver driver;
	private PPLeadsPage page;
	private String url = "/leads/manager";
	LeadData leadData;
	private String lLeadName;
	private String lPhoneNum;
	private String lEmail;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPLeadsPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new PPLeadsPage(driver);
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
	}
	
	@Parameters({"dataFile"})
	@Test
	public void testAddLead(String pDataFile) {
		leadData = new LeadData(pDataFile).getLeadData();
		setLeadData(leadData);
		
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
		
	}
	
	public void testSearchLead(String pDataFile) {
		leadData = new LeadData(pDataFile).getLeadData();
		if(ModuleCommonCache.getModuleCommonCache(leadData.getLeadEmail())!=null) {
			leadData =(LeadData) ModuleCommonCache.getModuleCommonCache(leadData.getLeadEmail());
		}else {
			setLeadData(leadData);
		}
		getPage(url);
		
	}
	
	private void applyFilters() {
		assertTrue(page.clickOnFilterArrow(), "Filters options are not visible");
		assertTrue(page.selectSourcesOption(), "Unable to select the Sources option");
		assertTrue(page.selectStatus(leadData.getLeadStatus()), "Unable to select the Sources option");
		assertTrue(page.selectStatus(leadData.getLeadStatus()), "Unable to select the status option");

	}

}
