package us.zengtest1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.backoffice.ZBOLeadCRMPage;
import com.zurple.backoffice.ZBOLoginPage;

import resources.AbstractPageTest;
import resources.EnvironmentFactory;
import resources.forms.zurple.website.ZWLeadCaptureForm;
import resources.interfaces.TestHavingHeader;
import resources.interfaces.UsingPage;

public abstract class PageTest extends AbstractPageTest  implements UsingPage, TestHavingHeader
{
	private String l_userName = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_user");
	private String l_password = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_pass");
	private String zurpleBOURL = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url");
	private ZBOLoginPage ZloginPage;
	
    @Test(groups = { "asset" })
    public void testAssetsVersions() {
        assertTrue(checkAssetsVersion(getPage().getAssets()));
    }

    public abstract Page getPage();

    @Test
    public void testTopMenu() {
        assertEquals("",getPage().getTopMenu().findElement(By.xpath("//li[1]/a")).getText());
        assertEquals("SEARCH",getPage().getTopMenu().findElement(By.xpath("//li[2]/a")).getText());
        assertEquals("REAL ESTATE NOTES",getPage().getTopMenu().findElement(By.xpath("//li[3]/a")).getText());
        assertEquals("SOLD HOMES",getPage().getTopMenu().findElement(By.xpath("//li[4]/a")).getText());
        //TODO - assert below is invalid because trim method is used. This is the marker that html markup is invalid
        assertEquals("LOG IN",getPage().getTopMenu().findElement(By.xpath("//li[5]/a")).getText().trim());
    }
    public void fillLeadCaptureForm(String pName, String pEmail, String pPhone) {
    	ZWLeadCaptureForm leadCaptureForm = new ZWLeadCaptureForm(getDriver());
    	assertTrue(leadCaptureForm.isLeadCaptureFormIsVisible(),"Lead capture form is not visible..");
    	assertTrue(leadCaptureForm.typeName(pName),"Lead capture form is not visible..");
    }
    
	public String getBOUsername() {
		return l_userName;
	}
	public void setL_userName() {
		this.l_userName = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_user");;
	}
	
	public String getBOPassword() {
		return l_password;
	}
	public void setL_password() {
		this.l_password = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_pass");
	}
	
	public ZBOLoginPage getLoginPage() {
		return ZloginPage;
	}
	
	public void setLoginPage(WebDriver pWebDriver) {
		this.ZloginPage = new ZBOLoginPage(pWebDriver);
	}
		
	public void setBOURL() {
		this.zurpleBOURL = EnvironmentFactory.configReader.getPropertyByName("torchx_prod_site");
	}
	
	public String getBOURL() {
		return zurpleBOURL;
	}

	protected String getLeadIdFromBackOffice(String pEmailOrName) {
		String l_bo_url = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+"/leads/crm";
		getDriver().navigate().to(l_bo_url);
		ZBOLeadCRMPage leadCrmpage = new ZBOLeadCRMPage(getDriver());
		return leadCrmpage.searchAndGetLeadId(pEmailOrName.split(" ")[0]);

	}
}
