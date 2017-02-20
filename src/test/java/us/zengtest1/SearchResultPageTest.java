package us.zengtest1;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import resources.AbstractPageTest;
import resources.classes.SearchResult;

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

    @Test(priority=10)
    public void testTitle() {
        assertEquals(getPage().getTitle(), "San Diego Homes for Sale | zengtest1.us");
    }

    @Test(priority=10)
    public void testHeader() {
        assertEquals(getPage().getHeader().getText(), "San Diego Homes for Sale");
    }

    @Test(priority=20)
    public void testBrand() {
        assertEquals(getPage().getBrand().getText(), "ZENG TEST PROPERTIES");
    }

    @Test(priority=30)
    public void testSearchResultsList(){
        ArrayList<SearchResult> searchResultsList = getPage().getSearchResultsBlock().getSearchResultsList();
        assertFalse(searchResultsList.isEmpty());
        searchResultsList.get(1).getElement().click();
    }

}
