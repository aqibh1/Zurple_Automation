package com.z57.site.v2;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import resources.AbstractPageTest;
import resources.interfaces.TestHavingHeader;
import resources.interfaces.UsingPage;
import com.z57.site.v2.Page;

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
        assertEquals("Home",getPage().getTopMenu().findElement(By.xpath("//li[1]/a")).getText());
        assertEquals("Listings",getPage().getTopMenu().findElement(By.xpath("//li[2]/a")).getText());
        assertEquals("Home Search",getPage().getTopMenu().findElement(By.xpath("//li[3]/a")).getText());
        assertEquals("Our Community",getPage().getTopMenu().findElement(By.xpath("//li[4]/a")).getText());
        assertEquals("Services",getPage().getTopMenu().findElement(By.xpath("//li[5]/a")).getText().trim());
        assertEquals("Real Estate Updates",getPage().getTopMenu().findElement(By.xpath("//li[6]/a")).getText().trim());
    }

    @Test
    public void testRegisterNewRegularLead() {
        assertEquals("Sign In",getPage().getUserMenu().getText());
    }

}
