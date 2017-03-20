package us.zengtest1;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class InsiderPageTest
        extends PageTest
{

    private static InsiderPage page;

    public InsiderPage getPage(){
        if(page == null){
            page = new InsiderPage();
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
