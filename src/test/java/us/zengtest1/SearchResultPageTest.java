package us.zengtest1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.classes.SearchResult;
import resources.orm.hibernate.models.Property;
import resources.orm.hibernate.models.SessionAnonymous;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SearchResultPageTest
        extends PageTest
{

    private static SearchResultsPage page;

    public SearchResultsPage getPage(){
        if(page == null){
            page = new SearchResultsPage();
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
            "views",
            "results_expected"
    })
    public void testSearchResultsList(
            @Optional("") String search_by,
            @Optional("") String search_criteria,
            @Optional("0") String min_price,
            @Optional("0") String max_price,
            @Optional("0") String bedrooms,
            @Optional("0") String bathrooms,
            @Optional("0") String square_feet,
            @Optional("0") String year_built,
            @Optional("0") String lot_sqft,
            @Optional("") String types,
            @Optional("") String features,
            @Optional("") String styles,
            @Optional("") String views,
            @Optional("") Integer results_expected
    ){

        assertTrue(results_expected <= getPage().getNumberOfResults());

        Cookie cks = driver.manage().getCookieNamed("PHPSESSID");
        SessionAnonymous sessionAnonymous = getEnvironment().getSessionAnonymous(cks.getValue());

        JSONObject real = new JSONArray(sessionAnonymous.getSessionAnonymousData()).getJSONObject(0).getJSONObject("user_activity_search");

        if ( !"0".equals(min_price) ){
            assertEquals(Integer.parseInt(min_price),real.get("user_activity_search_price_minimum"));
        }

        if ( !"0".equals(max_price) ){
            assertEquals(Integer.parseInt(max_price),real.get("user_activity_search_price_maximum"));
        }

        assertEquals(Integer.parseInt(bedrooms),real.get("user_activity_search_bedrooms_minimum"));
        assertEquals(Integer.parseInt(bathrooms),real.get("user_activity_search_bathrooms_minimum"));
        assertEquals(Integer.parseInt(square_feet),real.get("user_activity_search_square_feet_minimum"));
        assertEquals(Integer.parseInt(lot_sqft),real.get("user_activity_search_lot_square_feet_minimum"));

        do {
            ArrayList<SearchResult> searchResultsList = getPage().getSearchResultsBlock().getSearchResultsList();
            assertFalse(searchResultsList.isEmpty());

            List<String> typeList = null;
            if( types.isEmpty() == false)
            {
                typeList = Arrays.asList(types.split(","));
            }

            List<String> featureList = null;
            if( features.isEmpty() == false)
            {
                featureList = Arrays.asList(features.split(","));
            }

            List<String> viewList = null;
            if( views.isEmpty() == false)
            {
                viewList = Arrays.asList(views.split(","));
            }

            for(SearchResult result:searchResultsList)
            {
                Property property = getEnvironment().getDetailedProperty(result.getId());
                assertEquals(property.getStatus(),"Active");
                assertTrue(typeList.contains(property.getPropType()));

                if("city".equals(search_by))
                {
                    assertTrue(search_criteria.toLowerCase().contains(property.getCity().toLowerCase()));
                }

                assertTrue(property.getBedrooms() >= Integer.parseInt(bedrooms));
                assertTrue(property.getBathrooms() >= Integer.parseInt(bathrooms));

                if ( !"0".equals(min_price) && !"0".equals(max_price) ){
                    assertTrue(property.getPrice() >= Integer.parseInt(min_price) && property.getPrice() <= Integer.parseInt(max_price));
                }

                assertTrue(property.getYearBuilt() >= Integer.parseInt(year_built));
                assertTrue(property.getSquareFeet() >= Integer.parseInt(square_feet));
                assertTrue(property.getLotSqft() >= Integer.parseInt(lot_sqft));

                List<String> propertiesFeatures = property.buildFeaturesList();
                for(String feature:featureList){
                    assertTrue(propertiesFeatures.contains(feature));
                }

            }

            try{
                getPage().goNextPage();
            }catch(Exception e){
                break;
            }

        } while (true);
    }

}
