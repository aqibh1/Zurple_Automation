package us.zengtest1;

import org.testng.annotations.Test;
import resources.AbstractPageTest;

import static org.testng.Assert.assertEquals;

public class HomePageTest extends AbstractPageTest
{

    private static HomePage page;

    public Page getPage(){
        if(page == null){
            page = new HomePage();
            page.setDriver(getDriver());
        }
        return page;
    }

    @Test
    public void testTitle() {
        assertEquals("San Diego Homes for Sale | zengtest1.us", getPage().getTitle());
    }

    @Test
    public void testHeader() {
        assertEquals("6,873 HOMES FOR SALE IN SAN DIEGO, CA AND NEARBY", getPage().getHeader().getText());
    }

    @Test
    public void testBrand() {
        assertEquals("ZENG TEST PROPERTIES", getPage().getBrand().getText());
    }

}
