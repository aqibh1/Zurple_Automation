package us.zengtest1;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cactoos.scalar.False;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.AbstractPageTest;
import resources.elements.Select2Dropdown;
import us.zengtest1.resources.forms.SearchForm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

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
        Pattern pattern = Pattern.compile("/search/page/1");
        Matcher matcher = pattern.matcher(getDriver().getCurrentUrl());
        assertTrue(matcher.find());

    }

    @Test
    @Parameters({
            "search_by",
            "search_criteria",
            "min_price",
            "max_price",
            "bedrooms",
            "bathrooms",
            "square_feet",
            "year_built",
            "lot_sqft",
            "types",
            "features",
            "styles",
            "views"
    })
    public void testSearch(
            @Optional("") String search_by,
            @Optional("") String search_criteria,
            @Optional("") String min_price,
            @Optional("") String max_price,
            @Optional("") String bedrooms,
            @Optional("") String bathrooms,
            @Optional("") String square_feet,
            @Optional("") String year_built,
            @Optional("") String lot_sqft,
            @Optional("") String types,
            @Optional("") String features,
            @Optional("") String styles,
            @Optional("") String views
    ){

        SearchForm form = getPage().getSearchForm();
        form.setDriver(getDriver());

        assertTrue(form.checkElementExistsById("expand-search-fields"));
        form.expand();

        assertTrue(form.getElementById("by_" + search_by).isDisplayed());
        form.getElementById("by_" + search_by).click();

        assertTrue(form.getElementById(search_by).isDisplayed());
        form.setInputValue(search_by,search_criteria);

        assertTrue(form.getElementById("min_price").isDisplayed());
        form.setSelectValueByValue("min_price",min_price);

        assertTrue(form.getElementById("max_price").isDisplayed());
        form.setSelectValueByValue("max_price",max_price);

        assertTrue(form.getElementById("bedrooms").isDisplayed());
        form.setSelectValueByValue("bedrooms",bedrooms);

        assertTrue(form.getElementById("bathrooms").isDisplayed());
        form.setSelectValueByValue("bathrooms",bathrooms);

        assertTrue(form.getElementById("square_feet").isDisplayed());
        form.setSelectValueByValue("square_feet",square_feet);

        assertTrue(form.getElementById("year_built").isDisplayed());
        form.setSelectValueByValue("year_built",year_built);

        assertTrue(form.getElementById("lot_sqft").isDisplayed());
        form.setSelectValueByValue("lot_sqft",lot_sqft);

        for(String type:types.split(",")){
            assertTrue(form.getElementById("type_" + type).isDisplayed());
            if ( form.getElementById("type_" + type).isEnabled() == false)
            {
                form.getElementById("type_" + type).click();
            }
        }

        Select2Dropdown featuresInput = form.getFeaturesInput();
        List<String> featuresList = featuresInput.getVariants();
        assertFalse(featuresList.isEmpty());
        if (features.isEmpty() == false)
        {
            for(String feature:features.split(",")){
                featuresInput.selectValue(feature);
            }
        }


        Select2Dropdown stylesInput = form.getStylesInput();
        List<String> stylesList = stylesInput.getVariants();
        assertFalse(stylesList.isEmpty());
        if ( styles.isEmpty() == false)
        {
            for( String style:styles.split(",")){
                stylesInput.selectValue(style);
            }
        }


        Select2Dropdown viewsInput = form.getViewsInput();
        List<String> viewsList = viewsInput.getVariants();
        assertFalse(viewsList.isEmpty());
        if (views.isEmpty() == false)
        {
            for(String view:views.split(",")){
                featuresInput.selectValue(view);
            }
        }


        form.submit();

    }
}
