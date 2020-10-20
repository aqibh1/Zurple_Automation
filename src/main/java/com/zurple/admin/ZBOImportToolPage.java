package com.zurple.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import com.zurple.backoffice.ZBOLeadCRMPage;
import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOImportToolPage extends Page {
	
	@FindBy(id="package")
	WebElement package_dropdown_button;
	
	@FindBy(id="admins")
	WebElement admin_dropdown_button;
	
	@FindBy(id="cities")
	WebElement cities_dropdown_button;
	
	@FindBy(id="sites")
	WebElement sites_dropdown_button;
	
	@FindBy(xpath="//div[@id='email_settings_row']/input[@name='unsubscribe_mass_emails']")
	WebElement import_settings_unsub;
	
	@FindBy(xpath="//div[@id='email_settings_row']/input[@name='skip_nextday_responder']")
	WebElement import_settings_skip;
		
	String csvFile = "csv";
	
	@FindBy(id="Import")
	WebElement import_button;
	
	ZAProcessEmailQueuesPage processEmailObject;
	ZBOLeadCRMPage leadCRMObject;
	
	public ZBOImportToolPage() {
	}
	
	public ZAProcessEmailQueuesPage getEmailQueue() {
		return processEmailObject;
	}
	public void setEmailQueue() {
		this.processEmailObject = new ZAProcessEmailQueuesPage(driver);
	}
	
	public ZBOLeadCRMPage getLeadsCRM() {
		return leadCRMObject;
	}
	public void setLeadsCRM() {
		this.leadCRMObject = new ZBOLeadCRMPage(driver);
	}
	
	public ZBOImportToolPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean selectPackage(String pPackageId) {
		return ActionHelper.selectDropDownOption(driver, package_dropdown_button,"",pPackageId);
	}
	
	public boolean selectAdmin(String pAdminId) {
		boolean isSelected = false;
		if(ActionHelper.waitforDropdownToBePopulated(driver, admin_dropdown_button, 10)) {
			isSelected = ActionHelper.selectDropDownOption(driver,admin_dropdown_button,"",pAdminId);
		}
		return isSelected;
	}
	
	public boolean selectSite(String pSite) {
		boolean isSelected = false;
		if(ActionHelper.waitforDropdownToBePopulated(driver, sites_dropdown_button, 10)) {
			isSelected = ActionHelper.selectDropDownOption(driver,sites_dropdown_button,"",pSite);
		}
		return isSelected;
	}
	
	public boolean selectUnsubMassEmails() {
		return ActionHelper.checkUncheckInputBox(driver, import_settings_unsub, true);
	}
	
	public boolean selectSkipNextDay() {
		return ActionHelper.checkUncheckInputBox(driver, import_settings_skip, true);
	}
	
	public boolean selectCity(String pCity) {
		boolean isSelected = false;
		if(ActionHelper.waitforDropdownToBePopulated(driver, cities_dropdown_button, 10)) {
			isSelected = ActionHelper.selectDropDownOption(driver,cities_dropdown_button,"",pCity);
		}
		return isSelected;
	}
	
	public void updateCSV(String pDataFile, String replace, int row, int col) throws IOException, CsvException {
		File inputFile = new File(System.getProperty("user.dir")+pDataFile);
		// Read existing file 
		CSVReader reader = new CSVReader(new FileReader(inputFile));
		List<String[]> csvBody = reader.readAll();
		// get CSV row column  and replace with by using row and column
		csvBody.get(row)[col] = replace;
		reader.close();
		
		// Write to CSV file which is open
		CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
		writer.writeAll(csvBody);
		writer.flush();
		writer.close();
	  }
	
	public void uploadFile(String pDataFile) {
		WebElement addFile = ActionHelper.getElementByID(driver, csvFile);
		addFile.sendKeys(System.getProperty("user.dir")+pDataFile);
	}
	
	public boolean importButton() {
		return ActionHelper.Click(driver, import_button);
	}
	
	public boolean searchImportedLead(String pLeadEmail) {
		return leadCRMObject.searchLeadByEmail(pLeadEmail);
	}
	
	public boolean clickLeadName() {
		return leadCRMObject.clickSearchedLeadName();
	}
	
}
	
	

