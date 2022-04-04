package com.zurple.my.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvException;
import com.zurple.admin.ZBOImportToolPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.CacheFilePathsConstants;

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
		ActionHelper.staticWait(10);
		assertTrue(page.clickLeadName(),"Unable to click lead name..");
		assertTrue(emailVerification(),"Unable to verify lead source..");
		writeCacheFile(CacheFilePathsConstants.ImportLeadOneOptionCache, currentUrl);
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
		ActionHelper.staticWait(10);
		assertTrue(page.clickLeadName(),"Unable to click lead name..");
		assertTrue(emailVerification(),"Unable to verify lead source..");
		writeCacheFile(CacheFilePathsConstants.ImportLeadTwoOptionCache, currentUrl);
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
		ActionHelper.staticWait(10);
		assertTrue(page.clickLeadName(),"Unable to click lead name..");
		assertTrue(emailVerification(),"Unable to verify lead source..");
		writeCacheFile(CacheFilePathsConstants.ImportLeadThreeOptionCache, currentUrl);
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
		ActionHelper.staticWait(10);
		assertTrue(page.clickLeadName(),"Unable to click lead name..");
		assertTrue(emailVerification(),"Unable to verify lead source..");
		writeCacheFile(CacheFilePathsConstants.ImportLeadFourOptionCache, currentUrl);
	}
	
	@Test
	public void testVerifyMassAndCheckinEmailWhenNeitherOptionsAreChecked() {
		dataObject = getDataFile(CacheFilePathsConstants.ImportLeadOneOptionCache);
		String l_lead_id = dataObject.getString("lead_id");
		getPage(l_lead_id);
		if(!getIsProd()) {
			assertTrue(page.isImportCheckinEmailGenerated(),"Unable to find import lead checkin email..");
		}
		assertEquals(page.getMassEmailSettings(),"Yes");
	}
	
	@Test
	public void testVerifyMassAndCheckinEmailWhenAllOptionsAreChecked() {
		dataObject = getDataFile(CacheFilePathsConstants.ImportLeadTwoOptionCache);
		String l_lead_id = dataObject.getString("lead_id");
		getPage(l_lead_id);
		if(!getIsProd()) {
			assertTrue(page.isImportCheckinEmailGenerated(),"Unable to find import lead checkin email..");
		}
		assertEquals(page.getMassEmailSettings(),"No");
	}
	
	@Test
	public void testVerifyMassAndCheckinEmailWhenUnsubOptionsAreChecked() {
		dataObject = getDataFile(CacheFilePathsConstants.ImportLeadThreeOptionCache);
		String l_lead_id = dataObject.getString("lead_id");
		getPage(l_lead_id);
		if(!getIsProd()) {
			assertTrue(page.isImportCheckinEmailGenerated(),"Unable to find import lead checkin email..");
		}
		assertEquals(page.getMassEmailSettings(),"No");
	}
	
	@Test
	public void testVerifyMassAndCheckinEmailWhenNextDayOptionsAreChecked() {
		dataObject = getDataFile(CacheFilePathsConstants.ImportLeadFourOptionCache);
		String l_lead_id = dataObject.getString("lead_id");
		getPage(l_lead_id);
		if(!getIsProd()) {
			assertTrue(page.isImportCheckinEmailGenerated(),"Unable to find import lead checkin email..");
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
			ActionHelper.switchToOriginalWindow(driver);
			currentUrl = driver.getCurrentUrl();
			currentUrl = currentUrl.split(".com")[1];
			leadSource = page.getLeadSource();
			if(leadSource.contains(dataObject.optString("lead_source"))) {
				AutomationLogger.info("Lead source valid..");
				isVerified = true;
			}
//			if(!getIsProd()) {
//				page=null;
//				getPage("/admin/processemailqueue");
//				page.processEmailQueue();
//			}
		}
		return isVerified;
	}
	public void writeCacheFile(String pFileToWrite, String pContentToWrite) {
		JSONObject cacheObject = new JSONObject();
		cacheObject.put("lead_id", pContentToWrite);
		emptyFile(pFileToWrite, "");
		writeJsonToFile(pFileToWrite, cacheObject);
	}
	
}
