package com.zurple.my;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.backoffice.ZBOLeadCRMPage;
import com.zurple.backoffice.ZBOLeadPage;
import com.zurple.backoffice.ZBOLoginPage;

import resources.AbstractPageTest;
import resources.EnvironmentFactory;
import resources.utility.ActionHelper;

public abstract class PageTest extends AbstractPageTest
{
	private String l_userName = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_user");
	private String l_password = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_pass");
	private ZBOLoginPage loginPage;
	
	public PageTest(){
		setL_userName();
		setL_password();
	}
    @Test(groups = { "asset" })
    public void testAssetsVersions() {
        assertTrue(checkAssetsVersion(getPage().getAssets()));
    }

    protected static void waitLoad(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException qq) {
            qq.printStackTrace();
        }
    }
    protected void applyMultipleFilter(String pFilterName, String pFilterValue) throws ParseException {
		ZBOLeadPage leadPage = new ZBOLeadPage(getDriver());
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(getDriver());
		String[] lFilterNameList = pFilterName.split(",");
		String[] lFilterValueList = pFilterValue.split(",");
		for(int i=0;i<lFilterNameList.length;i++) {
			assertTrue(leadPage.clickAndSelectFilterNameMultiple(lFilterNameList[i],Integer.toString(i+1)),"Unable to select the filter type "+lFilterNameList[i]);
			ActionHelper.staticWait(10);
			assertTrue(leadPage.clickAndSelectFilterValueMultiple(lFilterValueList[i],Integer.toString(i+1)),"Unable to select the filter value "+lFilterValueList[i]);
			assertTrue(leadCRMPage.clickOnAddFilterButton());
		}
		assertTrue(leadPage.clickOnSearchButton(),"Unable to click on search button..");
	}
    
    /**
     * @return simplify domain name like zengtest2.us or zengtest6.us
     */
    protected String getDomainName() {
    	String l_domain_name = "";
    	if(getIsProd()) {
    		l_domain_name = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url").replace("https://www.", "");

		}else {
			String environment = System.getProperty("environment");
			l_domain_name = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url").split(environment+".")[1];
		}
    	return l_domain_name;
    }
	public String getZurpeBOUsername() {
		return l_userName;
	}
	public void setL_userName() {
		this.l_userName = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_user");;
	}
	public String getZurpeBOPassword() {
		return l_password;
	}
	public void setL_password() {
		this.l_password = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_pass");
	}
	public ZBOLoginPage getLoginPage() {
		return loginPage;
	}
	public void setLoginPage(WebDriver pWebDriver) {
		this.loginPage = new ZBOLoginPage(pWebDriver);
	}

}
