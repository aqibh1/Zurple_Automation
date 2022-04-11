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
import com.zurple.website.ZWRegisterUserPageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

public class ZBORedirectedLeadTest extends PageTest {
	private WebDriver driver;
	private JSONObject dataObject;
	ZBOLeadPage page;
	ZBOLeadDetailPage leadDetails;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOLeadPage(driver);
			leadDetails = new ZBOLeadDetailPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOLeadPage(driver);
			leadDetails = new ZBOLeadDetailPage(driver);
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
	@Parameters({"registerUserDataFile"})
	public void testVerifyRegisterLeadWithSellC(String pDataFile) {
		getPage();
		String lName,lEmail,lId="";
		testVerifyRegisterLeadWithParam(pDataFile,"sell-c");
		lName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);
		lEmail = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.RegisterFormLeadEmail);
		lId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		testVerifyRedirectToLeadDetails(lId);
		assertEquals(leadDetails.getLeadSource().trim(),"Zurple Seller+"); //We can optimize this by creating a mapping file for lead sources for various params
		assertTrue(leadDetails.verifyLeadEmail(lEmail),"Lead email is not present for user_id "+lId);
		assertTrue(leadDetails.isLeadNameExist(lName),"Lead name is not present for user_id "+lId);
		assertEquals(leadDetails.getTransactionGoalsValue().trim(),"Buyer & Seller Goals");
		assertTrue(!leadDetails.getWebSite().isEmpty(),"Lead website is not assigned for user_id "+lId);
	}
	
	@Test
	@Parameters({"registerUserDataFile"})
	public void testVerifyRegisterLeadWithMlb(String pDataFile) {
		getPage();
		String lName,lEmail,lId="";
		testVerifyRegisterLeadWithParam(pDataFile,"mlb");
		lName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);
		lEmail = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.RegisterFormLeadEmail);
		lId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		testVerifyRedirectToLeadDetails(lId);
		assertEquals(leadDetails.getLeadSource().trim(),"Zurple Auto Leads");
		assertTrue(leadDetails.verifyLeadEmail(lEmail),"Lead email is not present for user_id "+lId);
		assertTrue(leadDetails.isLeadNameExist(lName),"Lead name is not present for user_id "+lId);
		assertEquals(leadDetails.getTransactionGoalsValue().trim(),"Buyer Goals");
		assertTrue(!leadDetails.getWebSite().isEmpty(),"Lead website is not assigned for user_id "+lId);
	}
	
	
	public void testVerifyRegisterLeadWithParam(String pDataFile, String param) {
		getPage();
		ZWRegisterUserPageTest register = new ZWRegisterUserPageTest();
		ActionHelper.openUrlInCurrentTab(driver, EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")+"?"+param);
		register.testRegisterUser(pDataFile);
	}
	
	public void testVerifyRedirectToLeadDetails(String lId) {
		page=null;
		getPage("/lead/"+lId);
		assertTrue(leadDetails.isLeadDetailPage(),"Lead details page is not opened for user_id "+lId);
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
