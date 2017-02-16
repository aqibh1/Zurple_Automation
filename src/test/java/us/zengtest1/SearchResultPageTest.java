package us.zengtest1;

import org.testng.annotations.Test;
import resources.AbstractPageTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class SearchResultPageTest
        extends AbstractPageTest
{

    private static SearchResultsPage page;

    public SearchResultsPage getPage(){
        if(page == null){
            page = new SearchResultsPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    @Test(priority=10)
    public void testTitle() {
        assertEquals("San Diego Homes for Sale | zengtest1.us", getPage().getTitle());
    }

    @Test(priority=10)
    public void testHeader() {
        assertEquals("San Diego Homes for Sale", getPage().getHeader().getText());
    }

    @Test(priority=20)
    public void testBrand() {
        assertEquals(getPage().getBrand().getText(), "ZENG TEST PROPERTIES");
    }

    @Test(priority=30)
    public void testSearchResultsList(){
        assertFalse(getPage().getSearchResultsBlock().getSearchResultsList().isEmpty());

    }

}
