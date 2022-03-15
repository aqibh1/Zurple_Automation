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
		getPage("/lead/smschatlog/"+EnvironmentFactory.configReader.getPropertyByName("lead_id"));
    }
	
	@Test(dependsOnGroups={"testsetup"})
	@Parameters({"smschatpage"})
	public void testSMSChatPageTitle(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		assertTrue(page.pageTitle(lDataObject.optString("page_title")), "Page title is not visible..");
	}
	
	@Test(dependsOnGroups={"testsetup"})
	@Parameters({"smschatpage"})
	public void testSMSChatPageLeadName(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		assertTrue(page.leadName(lDataObject.optString("lead_name")), "Lead name is not visible..");
	}
	
	@Test(dependsOnGroups={"testsetup"})
	@Parameters({"smschatpage"})
	public void testSMSChatPagPhone(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		assertTrue(page.leadPhoneNumber(lDataObject.optString("lead_phone")), "Lead phone is not visible..");
	}
	
	@Test(dependsOnGroups={"testsetup"})
	@Parameters({"smschatpage"})
	public void testSMSChatPageHeader(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		assertTrue(page.chatPageHeader(lDataObject.optString("chat_header")), "Chat header is not visible..");
	}
	
	@Test(groups="sentMessage",dependsOnGroups={"testsetup"})
	@Parameters({"smschatpage"})
	public void testSendSMSMessage(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		messageText = updateName(lDataObject.optString("message_text"));
		assertTrue(page.typeMessage(messageText), "Unable to send sms to lead..");
		assertTrue(page.sendMessage(), "Unable to click send sms button..");
	}
	
	@Test(dependsOnGroups={"testsetup"})
	@Parameters({"smschatpage"})
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

	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
	
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}

}
