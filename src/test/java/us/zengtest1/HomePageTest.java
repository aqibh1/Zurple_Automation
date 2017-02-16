package us.zengtest1;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import resources.AbstractPageTest;

import static org.testng.Assert.assertEquals;

public class HomePageTest extends AbstractPageTest
{

    private static HomePage page;
    private static String source_in_url;

    @Parameters("source_in_url")
    @BeforeTest
    public void globalSetUp(String source_in_url){
        this.source_in_url = source_in_url;
    }

    public Page getPage(){
        if(page == null){
            page = new HomePage(this.source_in_url);
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
