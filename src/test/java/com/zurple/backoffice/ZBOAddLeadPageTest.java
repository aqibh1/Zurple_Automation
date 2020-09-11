/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.DataConstants;

/**
 * @author adar
 *
 */
public class ZBOAddLeadPageTest extends PageTest{

	private WebDriver driver;
	private JSONObject dataObject;
	private ZBOAddLeadPage page;
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOAddLeadPage(driver);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOAddLeadPage(driver);
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
	@Parameters({"addLeadData"})
	public void testAddLead(String pDataFile) {
		AutomationLogger.startTestCase("Add lead /lead/create");
		getPage("/lead/create");
		dataObject = getDataFile(pDataFile);
		String lAgentName = "";
		String ld_assignLead = dataObject.optString("assign_lead");
		if(!ld_assignLead.isEmpty()) {
			HashMap<String,String> agent_info_map  = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAgentsInfo);
			lAgentName = agent_info_map.get("agent_name");
			assertTrue(page.clickAndSelectAgent(lAgentName), "Unable to select agent name from drop down");
			ActionHelper.staticWait(3);
		}
				
		String lLeadEmail= updateEmail(dataObject.optString("email"));
		String lLeadName = updateName(dataObject.optString("first_name"));
		assertTrue(page.typeEmail(lLeadEmail), "Unable to type email address..");
		assertTrue(page.typeFirstName(lLeadName), "Unable to type first name..");
		if(!dataObject.optString("city_criteria").isEmpty()) {
			assertTrue(page.selectCity(dataObject.optString("city_criteria")), "Unable to select city");
		}
		if(!dataObject.optString("zip_criteria").isEmpty()) {
			assertTrue(page.selectZip (dataObject.optString("zip_criteria")), "Unable to select city");
		}
		
		if(!getIsProd()) {
			lLeadEmail = lLeadEmail.replace("@", "_ZurpleQA@");
		}
		
		boolean isWelcomeEmail = dataObject.optString("welcome_Email")!=null?true:false;
		if(isWelcomeEmail) {
			assertTrue(page.clickWelcomeEmailToggle(), "Unable to click on welcome email toggle button..");
		}	
		
		if(dataObject.optString("last_name") != null && dataObject.optString("cell") != null && dataObject.optString("phone") != null) {
				String lastName = dataObject.optString("last_name");
				String cell = dataObject.optString("cell");	
				String phone = dataObject.optString("phone");
				assertTrue(page.typeLastName(lastName),"Unable to type last name..");
				assertTrue(page.typeCell(cell),"Unable to type cell..");
				assertTrue(page.typePhone(phone),"Unable to type phone..");
				ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleleadLastName, lastName);
				ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleleadCell, cell);
				ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleleadPhone, phone);
				assertTrue(page.clickSaveButton(), "Unable click on save button..");
				ActionHelper.staticWait(5);
				ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleleadSource, page.getLeadSource());
			} else {
				assertTrue(page.clickSaveButton(), "Unable click on save button..");
			}
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail, lLeadEmail);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadName, lLeadName);
		String lLeadId = driver.getCurrentUrl().split("user_id/")[1];
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), lLeadEmail, lLeadId);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadId, lLeadId);
//		int lLead_id = new DBHelperMethods(getEnvironment()).getZurpleLeadId(lLeadEmail);
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), lLeadEmail, lLead_id);

		AutomationLogger.endTestCase();
	}

	

}