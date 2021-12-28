package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.Part;
import com.restapi.Part.PartType;
import com.zurple.admin.ZAAdminManagerPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.CacheFilePathsConstants;

/**
 * 
 * @author habibaaq
 *
 */
public class ZAUpdateAdminManagerPageTest extends PageTest{
	private WebDriver driver;
	private ZAAdminManagerPage page;
	JSONObject dataIdObject;
	JSONObject dataObject;
	String lAdminId = "";
	Map<String, Part> multiParts = new HashMap<String, Part>();

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZAAdminManagerPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZAAdminManagerPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
		
	}
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@BeforeTest
	public void backOfficeLogin() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}

	@Test(priority=243)
	public void testSetup() {
		lAdminId = EnvironmentFactory.configReader.getPropertyByName("ap_admin_id");
		page=null;
		getPage("/adminmgr/edit/admin_id/"+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testUpdateFirstName() {
		String updatedFName = updateName("fName");
		assertTrue(page.updateFirstName(updatedFName),"Unable to update admin first name "+lAdminId);
		multiParts.put("fName", new Part(updatedFName, PartType.STRING));
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testUpdateLastName() {
		String updatedLName = updateName("lName");
		assertTrue(page.updateLastName(updatedLName),"Unable to update admin last name "+lAdminId);
		multiParts.put("lName", new Part(updatedLName, PartType.STRING));
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testUpdateEmail() {
		String updatedEmail = updateEmail("nsautomation@mailinator.com");
		assertTrue(page.updatelEmail(updatedEmail),"Unable to update admin email "+lAdminId);
		multiParts.put("email", new Part(updatedEmail, PartType.STRING));
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testUpdatePhone() {
		String updatedPhone = updatePhoneNumber();
		assertTrue(page.updatePhone(updatedPhone),"Unable to update admin phone "+lAdminId);
		multiParts.put("phone", new Part(updatedPhone, PartType.STRING));
	}
	
	@Test(dependsOnMethods = { "testSetup" })
	public void testUpdateOfficeName() {
		String updatedOfficeName = updateName("officeName");
		assertTrue(page.updateOfficeName(updatedOfficeName),"Unable to update admin office name "+lAdminId);
		multiParts.put("oName", new Part(updatedOfficeName, PartType.STRING));
	}
	
	@Test(dependsOnMethods = { "testSetup" })
	public void testUpdateLicense() {
		String updatedLicense = updateName("SMRT");
		assertTrue(page.updateLicense(updatedLicense),"Unable to update admin license "+lAdminId);
		multiParts.put("dre", new Part(updatedLicense, PartType.STRING));
	}
	
	@Test(priority=244,dependsOnMethods = { "testSetup" })
	public void testSubmitUpdates() throws JsonGenerationException, JsonMappingException, IOException {
		assertTrue(page.clickUpdateButton(),"Unable to submit admin updates  "+lAdminId);
		emptyFile(CacheFilePathsConstants.APNSUpdatedData,"");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File(System.getProperty("user.dir")+CacheFilePathsConstants.APNSUpdatedData), multiParts);
	}

	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
