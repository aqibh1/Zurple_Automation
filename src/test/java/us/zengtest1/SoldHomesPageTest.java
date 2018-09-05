package us.zengtest1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SoldHomesPageTest
        extends PageTest
{

    private SoldHomesPage page;

    public SoldHomesPage getPage(){
        if(page == null){
            page = new SoldHomesPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test(priority=10)
    public void testTitle() {
        assertEquals("San Diego Sold Homes", getPage().getTitle());
    }

    @Test(priority=10)
    public void testHeader() {
        assertEquals("6,873 HOMES FOR SALE IN SAN DIEGO, CA AND NEARBY", getPage().getHeader().getText());
    }

    @Test(priority=20)
    public void testBrand() {
        assertEquals(getPage().getBrand().getText(), "ZENG TEST PROPERTIES");
    }

}
