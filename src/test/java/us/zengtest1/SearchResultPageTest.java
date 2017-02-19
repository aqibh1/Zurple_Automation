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
            page = new SearchResultsPage(getDriver().getCurrentUrl());
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };


    public void testTitle() {
        assertEquals(getPage().getTitle(), "San Diego Homes for Sale | zengtest1.us");
    }

    @Test(priority=10)
    public void testHeader() {
        assertEquals(getPage().getHeader().getText(), "San Diego Homes for Sale");
    }


    public void testBrand() {
        assertEquals(getPage().getBrand().getText(), "ZENG TEST PROPERTIES");
    }


    public void testSearchResultsList(){
        assertFalse(getPage().getSearchResultsBlock().getSearchResultsList().isEmpty());

    }

}
