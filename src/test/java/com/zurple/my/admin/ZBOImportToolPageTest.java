package com.zurple.my.admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.opencsv.exceptions.CsvException;
import com.zurple.admin.ZBOImportToolPage;
import com.zurple.backoffice.ZBOSocialIntegrationAndSettingsPage;
import com.zurple.backoffice.marketing.ZBOCreateTemplatePage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.utility.AutomationLogger;
import resources.utility.DataConstants;

public class ZBOImportToolPageTest extends PageTest{
	ZBOImportToolPage page;
	WebDriver driver;
	private String importLeadEmail; 
	private String currentUrl;
	private JSONObject dataObject;
	String leadSource;
	
	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		if(page==null) {
			driver = getDriver();
			page = new ZBOImportToolPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		// TODO Auto-generated method stub
		if(page==null) {
			driver = getDriver();
			page = new ZBOImportToolPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Parameters({"importLeadCSV"})
	public void updateCSV(String pCSVDataFile) {
		try {
			importLeadEmail = updateEmail("autoimport_zurpleqa@mailinator.com".trim());
			page.updateCSV(pCSVDataFile, importLeadEmail, 1, 4);
		} catch (IOException | CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Parameters({"importLeadCSV","importLeadData"})
	public void testNeitherOptionsChecked(String pCSVDataFile, String pImportDataFile) {
		page=null;
		getPage("/leadmgr/import");
		updateCSV(pCSVDataFile);
		dataObject = getDataFile(pImportDataFile);
		importSettings("",pCSVDataFile);
		page = null;
		getPage("/leads/crm");
		assertTrue(page.searchImportedLead(importLeadEmail),"Unable to search imported lead..");
		assertTrue(page.clickLeadName(),"Unable to click lead name..");
		assertTrue(emailVerification(),"Unable to verify lead source..");
		page=null;
		getPage(currentUrl);
		if(!getIsProd()) {
			assertTrue(page.isImportCheckinEmailGenerated(),"Unable to find import lead checkin email..");
		}
		assertEquals(page.getMassEmailSettings(),"Yes");
	}
	
	@Test
	@Parameters({"importLeadCSV","importLeadData"})
	public void testAllOptionsChecked(String pCSVDataFile, String pImportDataFile) {
		page=null;
		getPage("/leadmgr/import");
		updateCSV(pCSVDataFile);
		dataObject = getDataFile(pImportDataFile);
		importSettings("all",pCSVDataFile);
		page = null;
		getPage("/leads/crm");
		assertTrue(page.searchImportedLead(importLeadEmail),"Unable to search imported lead..");
		assertTrue(page.clickLeadName(),"Unable to click lead name..");
		assertTrue(emailVerification(),"Unable to verify lead source..");
		page=null;
		getPage(currentUrl);
		if(!getIsProd()) {
			assertFalse(page.isImportCheckinEmailGenerated(),"Unable to find import lead checkin email..");
		}
		assertEquals(page.getMassEmailSettings(),"No");
	}
	
	@Test
	@Parameters({"importLeadCSV","importLeadData"})
	public void testUnsubChecked(String pCSVDataFile, String pImportDataFile) {
		page=null;
		getPage("/leadmgr/import");
		updateCSV(pCSVDataFile);
		dataObject = getDataFile(pImportDataFile);
		importSettings("unSubMassEmails",pCSVDataFile);
		page = null;
		getPage("/leads/crm");
		assertTrue(page.searchImportedLead(importLeadEmail),"Unable to search imported lead..");
		assertTrue(page.clickLeadName(),"Unable to click lead name..");
		assertTrue(emailVerification(),"Unable to verify lead source..");
		page=null;
		getPage(currentUrl);
		if(!getIsProd()) {
			assertTrue(page.isImportCheckinEmailGenerated(),"Unable to find import lead checkin email..");
		}
		assertEquals(page.getMassEmailSettings(),"No");
	}
	
	@Test
	@Parameters({"importLeadCSV","importLeadData"})
	public void testNextDayChecked(String pCSVDataFile, String pImportDataFile) {
		page=null;
		getPage("/leadmgr/import");
		updateCSV(pCSVDataFile);
		dataObject = getDataFile(pImportDataFile);
		importSettings("skipNextDay",pCSVDataFile);
		page = null;
		getPage("/leads/crm");
		assertTrue(page.searchImportedLead(importLeadEmail),"Unable to search imported lead..");
		assertTrue(page.clickLeadName(),"Unable to click lead name..");
		assertTrue(emailVerification(),"Unable to verify lead source..");
		page=null;
		getPage(currentUrl);
		if(!getIsProd()) {
			assertFalse(page.isImportCheckinEmailGenerated(),"Unable to find import lead checkin email..");
		}
		assertEquals(page.getMassEmailSettings(),"Yes");
	}
	
	public void importSettings(String checkBoxes, String pCSVDataFile) {
		if(!getIsProd()) {
			assertTrue(page.selectPackage(dataObject.optString("package_id_stage")),"Unable to select package..");
			assertTrue(page.selectAdmin(dataObject.optString("admin_id_stage")),"Unable to select admin..");
			assertTrue(page.selectSite(dataObject.optString("site_stage")),"Unable to select site..");
			assertTrue(page.selectCity(dataObject.optString("city_stage")),"Unable to select city..");
		} else {
			assertTrue(page.selectPackage(dataObject.optString("package_id_prod")),"Unable to select package..");
			assertTrue(page.selectAdmin(dataObject.optString("admin_id_prod")),"Unable to select admin..");
			assertTrue(page.selectSite(dataObject.optString("site_prod")),"Unable to select site..");
			assertTrue(page.selectCity(dataObject.optString("city_prod")),"Unable to select city..");
		}
		switch(checkBoxes) {
			case "skipNextDay":
				page.selectSkipNextDay();
				break;
			case "unSubMassEmails":
				page.selectUnsubMassEmails();
				break;
			case "all":
				page.selectSkipNextDay();
				page.selectUnsubMassEmails();
				break;
			case "": 
				break;
		}
		page.uploadFile(pCSVDataFile);
		assertTrue(page.importButton(),"Unable to click import button..");
	}
	
	public boolean emailVerification() {
		boolean isVerified = false;
		if(page.checkEmailVerified()) {
			currentUrl = driver.getCurrentUrl();
			currentUrl = currentUrl.split(".com")[1];
			leadSource = page.getLeadSource();
			if(!leadSource.contains(dataObject.optString("lead_source"))) {
				AutomationLogger.info("Lead source is not valid..");
				isVerified = true;
			}
			if(!getIsProd()) {
				page=null;
				getPage("/admin/processemailqueue");
				page.processEmailQueue();
				isVerified = true;
			}
		}
		return isVerified;
	}
	
}
