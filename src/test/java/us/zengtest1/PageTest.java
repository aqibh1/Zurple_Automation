package us.zengtest1;

import org.openqa.selenium.By;
import resources.AbstractPageTest;
import resources.interfaces.TestHavingHeader;
import resources.interfaces.UsingPage;

public abstract class PageTest extends AbstractPageTest  implements UsingPage, TestHavingHeader
{

    public abstract Page getPage();

    public void testTopMenu() {
        assertEquals("",getPage().getTopMenu().findElement(By.xpath("//li[1]/a")).getText());
        assertEquals("SEARCH",getPage().getTopMenu().findElement(By.xpath("//li[2]/a")).getText());
        assertEquals("REAL ESTATE NOTES",getPage().getTopMenu().findElement(By.xpath("//li[3]/a")).getText());
        assertEquals("SOLD HOMES",getPage().getTopMenu().findElement(By.xpath("//li[4]/a")).getText());
        //TODO - assert below is invalid because trim method is used. This is the marker that html markup is invalid
        assertEquals("LOG IN",getPage().getTopMenu().findElement(By.xpath("//li[5]/a")).getText().trim());
    }

}
