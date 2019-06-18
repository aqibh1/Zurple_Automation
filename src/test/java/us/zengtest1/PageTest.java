package us.zengtest1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import resources.AbstractPageTest;
import resources.interfaces.TestHavingHeader;
import resources.interfaces.UsingPage;

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

}
