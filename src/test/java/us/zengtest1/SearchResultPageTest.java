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
            @Optional("") Integer min_price,
            @Optional("") Integer max_price,
            @Optional("") Integer bedrooms,
            @Optional("") Integer bathrooms,
            @Optional("") Integer square_feet,
            @Optional("") Integer year_built,
            @Optional("") Integer lot_sqft,
            @Optional("") String types,
            @Optional("") String features,
            @Optional("") String styles,
            @Optional("") String views,
            @Optional("") Integer results_expected
    ){

        Cookie cks = driver.manage().getCookieNamed("PHPSESSID");
        SessionAnonymous sessionAnonymous = getEnvironment().getSessionAnonymous(cks.getValue());
        JSONObject expected = new JSONArray("[  \n" +
                "   {  \n" +
                "      \"site_id\":\"1\",\n" +
                "      \"user_activity_type\":\"search\",\n" +
                "      \"user_activity_target\":\"\",\n" +
                "      \"user_activity_search\":{  \n" +
                "         \"user_activity_search_price_minimum\":"+min_price+",\n" +
                "         \"user_activity_search_price_maximum\":"+max_price+",\n" +
                "         \"user_activity_search_bedrooms_minimum\":"+bedrooms+",\n" +
                "         \"user_activity_search_bathrooms_minimum\":"+bathrooms+",\n" +
                "         \"user_activity_search_square_feet_minimum\":"+square_feet+",\n" +
                "         \"user_activity_search_lot_square_feet_minimum\":"+lot_sqft+",\n" +
                "         \"user_activity_search_cities\":[  \n" +
                "            \"25158\"\n" +
                "         ],\n" +
                "         \"user_activity_search_communities\":[  \n" +
                "\n" +
                "         ],\n" +
                "         \"user_activity_search_areas\":\"\",\n" +
                "         \"user_activity_search_property_types\":[  \n" +
                "            \"home\",\n" +
                "            \"condo\",\n" +
                "            \"land\"\n" +
                "         ],\n" +
                "         \"user_activity_search_extra\":[  \n" +
                "\n" +
                "         ],\n" +
                "         \"user_activity_search_fsv\":[  \n" +
                "\n" +
                "         ]\n" +
                "      },\n" +
                "      \"user_activity_time\":\"2017-08-09 00:07:53\"\n" +
                "   }\n" +
                "]").getJSONObject(0).getJSONObject("user_activity_search");

        JSONObject real = new JSONArray(sessionAnonymous.getSessionAnonymousData()).getJSONObject(0).getJSONObject("user_activity_search");
        assertEquals(expected.get("user_activity_search_price_minimum"),real.get("user_activity_search_price_minimum"));
        assertEquals(expected.get("user_activity_search_price_maximum"),real.get("user_activity_search_price_maximum"));
        assertEquals(expected.get("user_activity_search_bedrooms_minimum"),real.get("user_activity_search_bedrooms_minimum"));
        assertEquals(expected.get("user_activity_search_bathrooms_minimum"),real.get("user_activity_search_bathrooms_minimum"));
        assertEquals(expected.get("user_activity_search_square_feet_minimum"),real.get("user_activity_search_square_feet_minimum"));
        assertEquals(expected.get("user_activity_search_lot_square_feet_minimum"),real.get("user_activity_search_lot_square_feet_minimum"));

        do {
            assertTrue(results_expected <= getPage().getNumberOfResults());
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

                assertTrue(property.getBedrooms() >= bedrooms);
                assertTrue(property.getBathrooms() >= bathrooms);
                assertTrue(property.getPrice() >= min_price && property.getPrice() <= max_price);
                assertTrue(property.getYearBuilt() >= year_built);
                assertTrue(property.getSquareFeet() >= square_feet);
                assertTrue(property.getLotSqft() >= lot_sqft);

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
