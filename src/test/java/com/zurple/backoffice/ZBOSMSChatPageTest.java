package com.zurple.backoffice;

import static org.testng.Assert.assertFalse;
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
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * 
 * @author habibaaq
 *
 */

public class ZBOSMSChatPageTest extends PageTest {

	private ZBOSMSChatPage page;
	private WebDriver driver;
	private JSONObject dataObject;
	private String messageText;
	
	public AbstractPage getPage() {
    	page=null;
    	if(page == null){
        	driver = getDriver();
			page = new ZBOSMSChatPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
        }
        return page;
	}
    
    public AbstractPage getPage(String pUrl){
        if(page == null){
        	driver = getDriver();
			page = new ZBOSMSChatPage(driver);
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
    
    @Test(groups="testsetup")
    public void testSetup() {
    	page = null;
		getPage("/lead/smschatlog/"+EnvironmentFactory.configReader.getPropertyByName("zurple_lead_id"));
    }
	
	@Test(dependsOnGroups={"testsetup"})
	@Parameters({"dataFile"})
	public void testSMSChatPageTitle(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		assertTrue(page.pageTitle(lDataObject.optString("page_title")), "Page title is not visible..");
	}
	
	@Test(dependsOnGroups={"testsetup"})
	@Parameters({"dataFile"})
	public void testSMSChatPageLeadName(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		assertTrue(page.leadName(lDataObject.optString("lead_name")), "Lead name is not visible..");
	}
	
	@Test(dependsOnGroups={"testsetup"})
	@Parameters({"dataFile"})
	public void testSMSChatPagPhone(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		assertTrue(page.leadPhoneNumber(lDataObject.optString("lead_phone")), "Lead phone is not visible..");
	}
	
	@Test(dependsOnGroups={"testsetup"})
	@Parameters({"dataFile"})
	public void testSMSChatPageHeader(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		assertTrue(page.chatPageHeader(lDataObject.optString("chat_header")), "Chat header is not visible..");
	}
	
	@Test(groups="sentMessage",dependsOnGroups={"testsetup"})
	@Parameters({"dataFile"})
	public void testSendSMSMessage(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		messageText = updateName(lDataObject.optString("message_text"));
		assertTrue(page.typeMessage(messageText), "Unable to send sms to lead..");
		assertTrue(page.sendMessage(), "Unable to click send sms button..");
	}
	
	@Test(dependsOnGroups={"testsetup"})
	@Parameters({"dataFile"})
	public void testAdminInitials(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		assertTrue(page.getInitials(lDataObject.optString("admin_initials")), "Admin initials are not visible..");
	}
	
	@Test(dependsOnGroups={"testsetup","sentMessage"})
	public void testVerifySentMessage() {
		assertTrue(page.getMessage(messageText), "Sent SMS is not visible..");
	}
	
	@Test(dependsOnGroups={"testsetup","sentMessage"})
	public void testVerifyTimestamp() {
		assertTrue(page.getMessageTimestamp(getTodaysDate()), "Timestamp for sent sms is not visible not visible..");
	}
	
	@Test(dependsOnGroups={"testsetup"})
	public void testVerifyLeadDetailsButton() {
		assertTrue(page.clickLeadDetails(), "Lead details button is not working..");
	}
	///////////////////////
	@Test(groups="unenroll")
	@Parameters({"registerUserDataFile"})
	public void leadRegisterPreCondition(String pDataFile) {
		page=null;
		getPage();
		String lId = "";
		ZBORedirectedLeadTest register = new ZBORedirectedLeadTest();
		register.testVerifyRegisterLeadWithParam(pDataFile,"mlb");
		lId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		page=null;
		getPage("/lead/smschatlog/"+lId);
		ActionHelper.staticWait(15);
		ActionHelper.RefreshPage(driver);
	}
	
	@Test(dependsOnGroups={"unenroll"})
	@Parameters({"dataFile"})
	public void testUnEnrollLabelIsVisible(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		assertTrue(page.getUnenrollLabel(lDataObject.optString("unenroll_label").trim()),"Unable to get unenroll label..");
	}
	
	@Test(dependsOnGroups={"unenroll"})
	public void testUnEnrollCampaignNameIsVisible() {
		assertTrue(page.getUnenrollCampaignName(),"Unable to get unenroll campaign name..");
	}
	
	@Test(dependsOnGroups={"unenroll"})
	@Parameters({"dataFile"})
	public void testUnEnrollMessageIsVisible(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		assertTrue(page.getUnEnrollMessage(lDataObject.optString("unenroll_message").trim()),"Unable to get unenroll message..");
	}
	
	@Test(dependsOnGroups={"unenroll"})
	@Parameters({"dataFile"})
	public void testUnEnrollButtonText(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		assertTrue(page.getUnEnrollButtonText(lDataObject.optString("unenroll_button_text").trim()),"Unable to get unenroll button text..");
	}
	
	@Test(dependsOnGroups={"unenroll"})
	public void testLeadUnEnrollButtonIsWorking() {
		assertTrue(page.clickUnEnrollButton(),"Unable to click unenroll button");
	}
	
	@Test(dependsOnGroups={"unenroll"})
	@Parameters({"dataFile"})
	public void testLeadIsUnEnrolledFromCampaign(String pDataFile) {
		ActionHelper.staticWait(5);
		ActionHelper.RefreshPage(driver);
		JSONObject lDataObject = getDataFile(pDataFile);
		assertFalse(page.getUnEnrollButtonText(lDataObject.optString("unenroll_button_text").trim()),"Lead is not unenrolled from campaign");
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
