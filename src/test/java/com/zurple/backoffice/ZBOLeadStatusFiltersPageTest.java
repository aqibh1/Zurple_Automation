package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.backoffice.marketing.ZBOMarketingEmailMessagePage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.utility.ActionHelper;

public class ZBOLeadStatusFiltersPageTest extends PageTest{

	private ZBOLeadFiltersPage page;
	private WebDriver driver;
	ZBOMarketingEmailPageTest object = new ZBOMarketingEmailPageTest();
	
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	public AbstractPage getPage() {
    	page=null;
    	if(page == null){
        	driver = getDriver();
			page = new ZBOLeadFiltersPage(driver);
			page.setUrl("");
			page.setDriver(driver);
        }
        return page;
	}
    
    public AbstractPage getPage(String pUrl){
        if(page == null){
        	driver = getDriver();
			page = new ZBOLeadFiltersPage(driver);
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
    	if(object.leadStatus(lDataObject,0)==false) {
    		object.leadStatus(lDataObject,1);
    		ActionHelper.RefreshPage(driver);
    		object.leadStatus(lDataObject,0);
    	}
    	verifyLeadFilter(lDataObject,"prospect",lDataObject.optString("pageHeaderProspect"));
    	if(object.leadStatus(lDataObject,1)==false) {
    		object.leadStatus(lDataObject,0);
    		ActionHelper.RefreshPage(driver);
    		object.leadStatus(lDataObject,1);
    	}
    	verifyLeadFilter(lDataObject,"client",lDataObject.optString("pageHeaderClient"));
    }
    
    public void verifyLeadFilter(JSONObject pDataObject, String pfilterName, String pPageTitle) {
    	page=null;
    	getPage("/leads");
    	assertTrue(page.selectFilter(pfilterName),"Unable to select filter..");
    	assertEquals(page.pageTitle().trim(),pPageTitle);
    	String leadName = pDataObject.optString("leadNameEmail");
    	assertTrue(page.searchStatusLead(leadName),"Unable to search lead..");
//    	page.searchActiveStatusLead();
    }
}
