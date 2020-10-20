package com.zurple.backoffice;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ConfigReader;
import resources.classes.MenuItem;
import resources.classes.Alert;
import resources.orm.hibernate.models.zurple.User;
import resources.utility.AutomationLogger;
import resources.utility.DataConstants;
import resources.utility.GmailEmailVerification;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class ZBODashboardTest extends PageTest
{

    private ZBODashboardPage page;
    private WebDriver driver;
    private JSONObject dataObject;
    
    public AbstractPage getPage() {
    	page=null;
    	if(page == null){
        	driver = getDriver();
			page = new ZBODashboardPage(driver);
			page.setUrl("");
			page.setDriver(driver);
        }
        return page;
	}
    
    public AbstractPage getPage(String pUrl){
        if(page == null){
        	driver = getDriver();
			page = new ZBODashboardPage(driver);
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
    @Parameters({"registerUserDataFile"})
    public void testPhoneNumber(String pDataFile){
    	AutomationLogger.startTestCase("Verify lead phone number from dashboard");
    	
    	getPage("/dashboard");
    	dataObject = getDataFile(pDataFile);
    	String pNum = dataObject.optString(DataConstants.Phone);
    	
        assertTrue(page.verifyPhoneNumberText(pNum), "Phone numbers didn't match..");
        assertTrue(page.verifyPhoneAlert(), "Phone tap Alert not present..");
    }
    
    @Test
    public void testLogoutFromBackOffice() {
    	getPage();
    	assertTrue(page.doLogout(), "Logout failed");
    }
    
    @Test
    public void testVerifyDashboardStatsAreDisplayed() {
    	getPage();
//    	assertTrue(page.hardRefreshThePage(), "Unable to do hard refresh..");
    	assertTrue(page.isKeyStatsVisible(), "Key stats are not displayed..");
    	assertTrue(page.isLeadsManagedStatsVisible(), "Leads managed stats are not displayed..");
    	assertTrue(page.isMessagesSentVisible(), "Messages sent are not displayed..");
    	assertTrue(page.isMessagesOpenRateVisible(), "Messages open rate stats are not displayed..");
    	assertTrue(page.allVisitesTodayWorking(), "All visits today graph is not working..");
    	assertTrue(page.allVisitesPastWeekWorking(), "All visits past week graph is not working..");
    	assertTrue(page.allVisitesPastMontWorking(), "All visits past month graph is not working..");
    	assertTrue(page.allVisitesPastYearWorking(), "All visits past year graph is not working..");
    	assertTrue(page.isZurpleUpdateVisible(), "Zurple updates are not displayed..");
    	assertTrue(page.isZurpleUpdatesTextVisible(), "Zurple updates text is not displayed..");
    	assertTrue(page.isNewLeadsHeadingAndStatsDisplayed(), "New leads heading and stats are not displayed..");
    	assertTrue(page.isLeadNamesDisplayed(), "Lead names are not displayed..");
    	assertTrue(page.isHotBehaviorsDisplayed(), "Hot Behaviors is not displayed..");
    	assertTrue(page.clickOnViewLeadsButton(), "View leads button is not working..");
    	
    }
    @Test
    public void testEmailIsReceived() {
    	GmailEmailVerification gmailObject = new GmailEmailVerification();
    	gmailObject.isEmailPresentAndReply("z57testuser.zurpleqa@gmail.com", "uznhhalkthskjpyx", "Quick Question", "aqib.zurple.production@zengtest2.us",true);    	
    }

}