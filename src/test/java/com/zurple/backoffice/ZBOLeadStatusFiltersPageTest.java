package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;

public class ZBOLeadStatusFiltersPageTest extends PageTest{

	private ZBOLeadStatusFiltersPage page;
	private WebDriver driver;
	ZBOMarketingEmailPageTest marketingEmailObject = new ZBOMarketingEmailPageTest();
	
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	public AbstractPage getPage() {
    	page=null;
    	if(page == null){
        	driver = getDriver();
			page = new ZBOLeadStatusFiltersPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
        }
        return page;
	}
    
    public AbstractPage getPage(String pUrl){
        if(page == null){
        	driver = getDriver();
			page = new ZBOLeadStatusFiltersPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
        }
        return page;
    }
    
    @BeforeTest
	public void backOfficeLogin() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}
    
    @Test
    @Parameters({"dataFile"})
    public void testLeadStatusFilter(String pDataFile) { 
    	getPage();
    	JSONObject lDataObject = getDataFile(pDataFile);
    	updateLeadStatus(lDataObject); 
    }
    
    public boolean verifyLeadFilter(JSONObject pDataObject, String pfilterName, String pPageTitle) {
    	page=null;
    	getPage("/leads");
    	page.selectFilter(pfilterName);
    	assertEquals(page.pageTitle().trim(),pPageTitle);
    	String leadName = pDataObject.optString("leadNameEmail");
    	return page.searchStatusLead(leadName);
    }
    
    public boolean verifyCRMLeadsFilter(JSONObject pDataObject, String pfilterName, String pPageTitle) {
    	page=null;
    	getPage("/leads/crm");
    	page.selectCRMFilter(pfilterName);
    	assertEquals(page.pageTitle().trim(),pPageTitle);
    	String leadName = pDataObject.optString("leadNameEmail");
    	return page.searchCRMStatusLead(leadName);
    }
    
    public void updateLeadStatus(JSONObject lDataObject) {
    	String statusFilter, leadStatus;
    	for(int i=0; i<6; i++) {
    		marketingEmailObject.redirectToLeadsPage(lDataObject);
    		leadStatus = lDataObject.optString("lead_prospect").split(",")[i];
    		if(!leadStatus.trim().equalsIgnoreCase(page.getLeadStatus().trim())) {
    			marketingEmailObject.leadStatus(lDataObject,i);
    		}
    		statusFilter = lDataObject.optString("lead_filters");
    		statusFilter = statusFilter.split(",")[i];
    		assertTrue(verifyLeadFilter(lDataObject,statusFilter,"With Status: "+leadStatus),"Unable to verify customized leads list filters..");
    		assertTrue(verifyCRMLeadsFilter(lDataObject,statusFilter,leadStatus),"Unable to verify customized leads list filters..");
    	}
    }
    
    @AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
