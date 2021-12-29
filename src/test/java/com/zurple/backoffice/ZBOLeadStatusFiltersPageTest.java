package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
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
			page.setUrl("");
			page.setDriver(driver);
        }
        return page;
	}
    
    public AbstractPage getPage(String pUrl){
        if(page == null){
        	driver = getDriver();
			page = new ZBOLeadStatusFiltersPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
        }
        return page;
    }
    
    @Test
    @Parameters({"dataFile"})
    public void testLeadStatusFilter(String pDataFile) { 
    	getPage();
    	JSONObject lDataObject = getDataFile(pDataFile);
    	String statusFilter, leadStatus;
    	for(int i=0; i<6; i++) {
    		marketingEmailObject.redirectToLeadsPage(lDataObject);
    		leadStatus = lDataObject.optString("lead_prospect").split(",")[i];
    		if(!leadStatus.trim().equalsIgnoreCase(page.getLeadStatus().trim())) {
    			marketingEmailObject.leadStatus(lDataObject,i);
    		}
    		statusFilter = lDataObject.optString("lead_filters");
    		statusFilter = statusFilter.split(",")[i];
    		verifyLeadFilter(lDataObject,statusFilter,"With Status: "+leadStatus);
    		verifyCRMLeadsFilter(lDataObject,statusFilter,leadStatus);
    	}
    }
    
    public void verifyLeadFilter(JSONObject pDataObject, String pfilterName, String pPageTitle) {
    	page=null;
    	getPage("/leads");
    	page.selectFilter(pfilterName);
    	assertEquals(page.pageTitle().trim(),pPageTitle);
    	String leadName = pDataObject.optString("leadNameEmail");
    	assertTrue(page.searchStatusLead(leadName),"Unable to search lead..");
    }
    
    public void verifyCRMLeadsFilter(JSONObject pDataObject, String pfilterName, String pPageTitle) {
    	page=null;
    	getPage("/leads/crm");
    	page.selectCRMFilter(pfilterName);
    	assertEquals(page.pageTitle().trim(),pPageTitle);
    	String leadName = pDataObject.optString("leadNameEmail");
    	assertTrue(page.searchCRMStatusLead(leadName),"Unable to search lead..");
    }
}
