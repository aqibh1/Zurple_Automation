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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class ZBODashboardTest extends PageTest
{

    private ZBODashboard page;
    private WebDriver driver;
    private JSONObject dataObject;
    
    public AbstractPage getPage() {
		// TODO Auto-generated method stub
		return null;
	}
    
    public AbstractPage getPage(String pUrl){
        if(page == null){
        	driver = getDriver();
			page = new ZBODashboard(driver);
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

}