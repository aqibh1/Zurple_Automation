package com.zurple.backoffice;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ConfigReader;
import resources.classes.MenuItem;
import resources.classes.Alert;
import resources.orm.hibernate.models.zurple.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class ZBODashboardTest extends PageTest
{

    ZBODashboard page;
    WebDriver driver;
    
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
    public void testPhoneNumber(){
    	getPage("/dashboard");
        assertTrue(page.getPhoneNumberText("202-555-0149"),"Phone numbers didn't match..");
    }
}