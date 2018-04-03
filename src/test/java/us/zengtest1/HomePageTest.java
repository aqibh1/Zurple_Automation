package us.zengtest1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.annotations.Test;
import resources.AbstractPageTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePageTest extends PageTest
{

    private static HomePage page;

    public void clearPage(){
        page=null;
    };

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
        Pattern pattern = Pattern.compile("\\d,\\d{3} HOMES FOR SALE IN SAN DIEGO, CA AND NEARBY");
        Matcher matcher = pattern.matcher(getPage().getHeader().getText());
        assertTrue(matcher.find());
    }

    @Test
    public void testBrand() {        assertEquals("ZENG TEST PROPERTIES", getPage().getBrand().getText());    }

}
