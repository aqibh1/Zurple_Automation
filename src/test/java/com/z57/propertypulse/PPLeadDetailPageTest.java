/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.ModuleCommonCache;
import resources.data.z57.LeadData;
import resources.orm.hibernate.HibernateUtil;
import resources.orm.hibernate.models.z57.Lead;

/**
 * @author adar
 *
 */
public class PPLeadDetailPageTest extends PageTest{
	WebDriver driver;
	PPLeadsDetailPage page;
	LeadData leadData;
	
	private String lLeadName="";
	private String lPhoneNum="";
	private String lEmail="";
	private String lDob="";
	private String lAddress="";
	private String lCity ="";
	private String lState="";
	private String lZip="";
	private String lStatus ="";
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPLeadsDetailPage(driver);
//			page.setUrl("");
//			page.setDriver(driver);
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
		lDob=pLeadData.getLeadDOB();
		lAddress=pLeadData.getLeadAddress();
		lCity =pLeadData.getLeadCity();
		lState=pLeadData.getLeadState();
		lZip=pLeadData.getLeadZip();
		lStatus =pLeadData.getLeadStatus();
	}
	
	@Parameters({"dataFile"})
	@Test
	public void testAddLeadDetail(String pDataFile) {
		leadData = new LeadData(pDataFile).getLeadData();
		setLeadData(leadData);
		ModuleCommonCache.setModuleCommonCache(leadData.getLeadEmail(), leadData);
		//Verifies lead is successfully landed on Lead Details page
		getPage();
		
		enterLeadDetails();
		
	}
	
	@Parameters({"dataFile2"})
	@Test
	public void testEditLeadDetail(String pDataFile) {
		leadData = new LeadData(pDataFile).getLeadData();
		setLeadData(leadData);
		//Verifies lead is successfully landed on Lead Details page
		getPage();
		
		enterLeadDetails();
		
	}
	
	private void enterLeadDetails() {
		assertTrue(page.isLeadDetailsPage(), "Lead Details Page is not visible.");
		
		if(!lLeadName.isEmpty()) {
			assertTrue(page.typeLeadName(lLeadName), "Unable to update the Lead Name.");
		}
		if(!lEmail.isEmpty()) {
			assertTrue(page.typeLeadEmail(lEmail), "Unable to update the Lead email.");
		}
		if(!lPhoneNum.isEmpty()) {
			assertTrue(page.typeLeadPhone(lPhoneNum), "Unable to update the Lead phone number.");
		}
		if(!lDob.isEmpty()) {
			assertTrue(page.typeLeadDOB(lDob), "Unable to type Lead's DOB.");
		}
		if(!lAddress.isEmpty()) {
			assertTrue(page.typeLeadAddress(lAddress), "Unable to type Lead's address");
		}
		if(!lCity.isEmpty()) {
			assertTrue(page.typeLeadCity(lCity), "Unable to type Lead's city.");
		}
		if(!lStatus.isEmpty()) {
			assertTrue(page.selectLeadStatus(lStatus), "Unable to select Lead's status.");
		}
		if(!lState.isEmpty()) {
			assertTrue(page.selectState(lState.split(",")[0]), "Unable to select Lead's state.");
		}
		if(!lZip.isEmpty()) {
			assertTrue(page.typeLeadZip(lZip), "Unable to type Lead's zip.");
		}
		assertTrue(page.clickOnSaveButton(), "Unable to click on Save button.");
		assertTrue(page.isLeadUpdatedSuccessfully(), "Unable to update Lead.");
		
		HibernateUtil.setSessionFactoryEmpty();
		
//		DBHelperMethods dbHelperMethods = new DBHelperMethods(getEnvironment());
//		verifyLeadDetailsFromDatabase(dbHelperMethods.getLeadObject(lEmail));
	}
	
	private void verifyLeadDetailsFromDatabase(Lead pLeadDBObject) {
		assertTrue(lLeadName.equalsIgnoreCase(pLeadDBObject.getNameFull()), "Lead NAME mismatched. Expected ["+lLeadName+"] :: Actual ["+pLeadDBObject.getNameFull()+"]");
		assertTrue(lEmail.equalsIgnoreCase(pLeadDBObject.getEmail()), "Lead EMAIL mismatched. Expected ["+lEmail+"] :: Actual ["+pLeadDBObject.getNameFull()+"]");
		
		if(!lPhoneNum.isEmpty()) {
			assertTrue(lPhoneNum.equalsIgnoreCase(pLeadDBObject.getPhone()), "Lead PHONE mismatched. Expected ["+lPhoneNum+"] :: Actual ["+pLeadDBObject.getPhone()+"]");
		}
		if(!lAddress.isEmpty()) {
			assertTrue(lAddress.equalsIgnoreCase(pLeadDBObject.getAddress()), "Lead ADDRESS mismatched. Expected ["+lAddress+"] :: Actual ["+pLeadDBObject.getAddress()+"]");
		}
		if(!lCity.isEmpty()) {
			assertTrue(lCity.equalsIgnoreCase(pLeadDBObject.getCity()), "Lead CITY mismatched. Expected ["+lCity+"] :: Actual ["+pLeadDBObject.getCity()+"]");
		}
		if(!lState.isEmpty()) {
			assertTrue(lState.split(",")[1].equalsIgnoreCase(pLeadDBObject.getState()), "Lead STATE mismatched. Expected ["+lState+"] :: Actual ["+pLeadDBObject.getState()+"]");
		}
		if(!lZip.isEmpty()) {
			assertTrue(lZip.equalsIgnoreCase(pLeadDBObject.getZip()), "Lead ZIP mismatched. Expected ["+lZip+"] :: Actual ["+pLeadDBObject.getZip()+"]");
		}
		
		
		System.out.println(pLeadDBObject.getDob());
		
	}
}
