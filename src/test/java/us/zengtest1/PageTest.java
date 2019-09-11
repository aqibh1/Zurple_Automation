package us.zengtest1;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import resources.AbstractPageTest;
import resources.forms.zurple.website.ZWLeadCaptureForm;
import resources.interfaces.TestHavingHeader;
import resources.interfaces.UsingPage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public abstract class PageTest extends AbstractPageTest  implements UsingPage, TestHavingHeader
{

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

}
