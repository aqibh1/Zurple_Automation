package com.zurple.backoffice;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.backoffice.marketing.ZBOMarketingEmailMessagePage;
import com.zurple.my.PageTest;
import com.zurple.rest.zapier.ZapierRestImportLead;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;

public class ZBOVerifyZapierLeadImportPageTest extends PageTest {
	private WebDriver driver;
	ZapierRestImportLead zapier = new ZapierRestImportLead();
	ZBOLeadCRMPage page;
	private JSONObject dataObject;
	int counter = 0;
	ZBOLeadDetailPage leadDetails;
	String lFName,lEmail = "";
	
	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		if(page==null) {
			driver = getDriver();
			page = new ZBOLeadCRMPage(driver);
			page.setUrl("");
			page.setDriver(driver);
			setLoginPage(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOLeadCRMPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
			setLoginPage(driver);
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
	@Parameters({"datafile"})
	public void testVerifyZapierImportedLeads(String pDataFile) throws IOException, GeneralSecurityException {
		getPage();
		dataObject = getDataFile(pDataFile);
		zapier.testVerifyGoogleSheet(dataObject);
		leadDetails = new ZBOLeadDetailPage(driver);
		page=null;
		getPage("/leads/crm");
		lEmail = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZapierLeadEmail);
		lFName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZapierLeadFName);
		boolean isLeadFound = false;
		while(!isLeadFound || counter!=2) {
			isLeadFound = page.searchLeadByEmail(lEmail);
			if(isLeadFound) {
				break;
			}
			ActionHelper.staticWait(30);
			counter++;
		}
		assertTrue(page.clickSearchedLeadName(),"Unable to click searched lead name..");
		ActionHelper.staticWait(7);
		ActionHelper.switchToOriginalWindow(driver);
		assertTrue(leadDetails.isLeadDetailPage(),"Unable to open lead details page..");
	}
	
	@Test(dependsOnMethods = { "testVerifyZapierImportedLeads" })
	public void testVerifyZapierLeadName() {
		assertTrue(leadDetails.isLeadNameExist(lFName+" "+dataObject.optString("last_name")),"Unable to verify zapier lead name..");
	}
	
	@Test(dependsOnMethods = { "testVerifyZapierImportedLeads" })
	public void testVerifyZapierLeadPhone() {
		assertTrue(!leadDetails.getPhoneNum().isEmpty(),"Unable to verify zapier lead phone number..");
	}
	
	@Test(dependsOnMethods = { "testVerifyZapierImportedLeads" })
	public void testVerifyZapierLeadAddress() {
		assertTrue(leadDetails.verifyLeadEmail(lEmail),"Unable to verify zapier lead email..");
	}
	
	@Test(dependsOnMethods = { "testVerifyZapierImportedLeads" })
	public void testVerifyZapierLeadEmail() {
		assertTrue(leadDetails.isLeadNameExist(lFName+" "+dataObject.optString("last_name")),"Unable to verify zapier lead name..");
	}
	
	@Test(dependsOnMethods = { "testVerifyZapierImportedLeads" })
	public void testVerifyZapierLeadSource() {
		assertTrue(leadDetails.verifyLeadSource(dataObject.optString("source")),"Unable to verify zapier lead source..");
	}
	
	@Test(dependsOnMethods = { "testVerifyZapierImportedLeads" })
	public void testVerifyZapierLeadNotes() {
		assertTrue(leadDetails.isZapierManualNoteEmpty(),"Unable to verify zapier manually added lead note..");
		assertTrue(leadDetails.isZapierSystemNoteEmpty(),"Unable to verify zapier system added lead note..");
		assertTrue(leadDetails.isZapierSourceNoteEmpty(),"Unable to verify zapier system added lead note for source..");
	}

	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}

}
