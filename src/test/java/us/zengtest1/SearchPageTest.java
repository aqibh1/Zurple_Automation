package us.zengtest1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.annotations.Test;
import resources.AbstractPageTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchPageTest
        extends PageTest
{

    private static SearchPage page;

    public SearchPage getPage(){
        if(page == null){
            page = new SearchPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test(priority=10)
    public void testTitle() {
        assertEquals("Search for Homes in San Diego, CA", getPage().getTitle());
    }

    @Test(priority=10)
    public void testHeader() {
        assertEquals("6,873 HOMES FOR SALE IN SAN DIEGO, CA AND NEARBY", getPage().getHeader().getText());
    }

    @Test(priority=20)
    public void testBrand() {
        assertEquals(getPage().getBrand().getText(), "ZENG TEST PROPERTIES");
    }

    @Test(priority=30)
    public void testSubmittingDefaultSearchForm(){
        getPage().getSearchForm().submit();
        // We must be redirected
        // Checking URL, should be like this http://dev.zengtest1.us/thankyou?lead_id=102758
        Pattern pattern = Pattern.compile("http://dev\\.zengtest1\\.us/search/page/1");
        Matcher matcher = pattern.matcher(getDriver().getCurrentUrl());
        assertTrue(matcher.find());

    }

}
