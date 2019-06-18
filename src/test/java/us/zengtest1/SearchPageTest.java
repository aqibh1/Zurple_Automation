package us.zengtest1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import resources.ParametersFactory;
import resources.elements.Select2Dropdown;
import us.zengtest1.resources.forms.SearchForm;

public class SearchPageTest
        extends PageTest
{

    private SearchPage page;

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
        Pattern pattern = Pattern.compile("/search/page/1");
        Matcher matcher = pattern.matcher(getDriver().getCurrentUrl());
        assertTrue(matcher.find());

    }

    @Test
    public void testSearch(){

        Long thread_id = Thread.currentThread().getId();
        HashMap<String,String> params = ParametersFactory.getSearchParameters(thread_id);

        if ( params == null)
        {
            return;
        }

        String search_by = params.get("search_by");
        String search_criteria = params.get("search_criteria");
        String min_price = params.get("min_price");
        String max_price = params.get("max_price");
        String bedrooms = params.get("bedrooms");
        String bathrooms = params.get("bathrooms");
        String square_feet = params.get("square_feet");
        String lot_sqft = params.get("lot_sqft");
        String year_built = params.get("year_built");
        String types = params.get("types");
        String features = params.get("features");
        String styles = params.get("styles");
        String views = params.get("views");

        SearchForm form = getPage().getSearchForm();
        form.setDriver(getDriver());

        assertTrue(form.checkElementExistsById("expand-search-fields"));
        form.expand();

        assertTrue(form.getElementById("by_" + search_by).isDisplayed());
        form.getElementById("by_" + search_by).click();

        assertTrue(form.getElementById("city").isDisplayed());
        form.setInputValue("city",search_criteria);

        assertTrue(form.getElementById("min_price").isDisplayed());
        if ( min_price != null) {
            form.setSelectValueByValue("min_price", min_price);
        }

        assertTrue(form.getElementById("max_price").isDisplayed());
        if ( max_price != null ) {
            form.setSelectValueByValue("max_price", max_price);
        }

        assertTrue(form.getElementById("bedrooms").isDisplayed());
        if ( bedrooms != null) {
            form.setSelectValueByValue("bedrooms", bedrooms);
        }
        assertTrue(form.getElementById("bathrooms").isDisplayed());
        if ( bathrooms  != null ) {
            form.setSelectValueByValue("bathrooms", bathrooms);
        }

        assertTrue(form.getElementById("square_feet").isDisplayed());
        if ( square_feet != null) {
            form.setSelectValueByValue("square_feet", square_feet);
        }
        assertTrue(form.getElementById("year_built").isDisplayed());
        if ( year_built != null){
            assertTrue(form.getElementById("year_built").isDisplayed());
            form.setSelectValueByValue("year_built",year_built);
        }

        assertTrue(form.getElementById("lot_sqft").isDisplayed());
        if ( lot_sqft != null) {
            form.setSelectValueByValue("lot_sqft", lot_sqft);
        }

        if ( types != null) {
            for (String type : types.split(",")) {
                assertTrue(form.getElementById("type_" + type).isDisplayed());
                if (form.getElementById("type_" + type).isEnabled() == false) {
                    form.getElementById("type_" + type).click();
                }
            }
        }

        Select2Dropdown featuresInput = form.getFeaturesInput();
        List<String> featuresList = featuresInput.getVariants();
        assertFalse(featuresList.isEmpty());
        if (features != null )
        {
            for(String feature:features.split(",")){
                featuresInput.selectValue(feature);
            }
        }


        Select2Dropdown stylesInput = form.getStylesInput();
        List<String> stylesList = stylesInput.getVariants();
        assertFalse(stylesList.isEmpty());
        if ( styles != null )
        {
            for( String style:styles.split(",")){
                stylesInput.selectValue(style);
            }
        }


        Select2Dropdown viewsInput = form.getViewsInput();
        List<String> viewsList = viewsInput.getVariants();
        assertFalse(viewsList.isEmpty());
        if (views != null )
        {
            for(String view:views.split(",")){
                featuresInput.selectValue(view);
            }
        }


        form.submit();

    }
}
