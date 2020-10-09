package com.zurple.my.admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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

public class ZBOImportToolPageTest extends PageTest{
	ZBOImportToolPage page;
	WebDriver driver;

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
	
	@Test
	@Parameters({"importLeadCSV"})
	public void testNeitherOptionsChecked(String pDataFile) {
		getPage("/leadmgr/import");
		try {
			page.updateCSV(pDataFile, updateEmail("autoimport_zurpleqa@mailinator.com".trim()), 1, 4);
		} catch (IOException | CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.selectPackage("57: 57John Doeedit");
		page.selectAdmin("12666: Aqib Site Owner");
		page.selectSite("3215: zengtest6.us");
		page.selectUnsubMassEmails();
		page.selectSkipNextDay();
		page.selectCity("29 Palms, CA");
		page.uploadFile(pDataFile);
		page.importButton();
	}
	
	@Test
	@Parameters({"importLeadCSV"})
	public void testAllOptionsChecked(String pDataFile) {
		getPage("/leadmgr/import");
		try {
			page.updateCSV(pDataFile, updateEmail("autoimport_zurpleqa@mailinator.com".trim()), 1, 4);
		} catch (IOException | CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.selectPackage("57: 57John Doeedit");
		page.selectAdmin("12666: Aqib Site Owner");
		page.selectSite("3215: zengtest6.us");
		page.selectUnsubMassEmails();
		page.selectSkipNextDay();
		page.selectCity("29 Palms, CA");
		page.uploadFile(pDataFile);
		page.importButton();
	}
	
	@Test
	@Parameters({"importLeadCSV"})
	public void testUnsubChecked(String pDataFile) {
		getPage("/leadmgr/import");
		try {
			page.updateCSV(pDataFile, updateEmail("autoimport_zurpleqa@mailinator.com".trim()), 1, 4);
		} catch (IOException | CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.selectPackage("57: 57John Doeedit");
		page.selectAdmin("12666: Aqib Site Owner");
		page.selectSite("3215: zengtest6.us");
		page.selectUnsubMassEmails();
		page.selectSkipNextDay();
		page.selectCity("29 Palms, CA");
		page.uploadFile(pDataFile);
		page.importButton();
	}
	
	@Test
	@Parameters({"importLeadCSV"})
	public void testNextDayChecked(String pDataFile) {
		getPage("/leadmgr/import");
		try {
			page.updateCSV(pDataFile, updateEmail("autoimport_zurpleqa@mailinator.com".trim()), 1, 4);
		} catch (IOException | CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.selectPackage("57: 57John Doeedit");
		page.selectAdmin("12666: Aqib Site Owner");
		page.selectSite("3215: zengtest6.us");
		page.selectUnsubMassEmails();
		page.selectSkipNextDay();
		page.selectCity("29 Palms, CA");
		page.uploadFile(pDataFile);
		page.importButton();
	}
	
}
