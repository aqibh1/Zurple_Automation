package us.zengtest1;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;
import resources.ParametersFactory;
import resources.classes.SearchResult;
import resources.orm.hibernate.models.zurple.Property;
import resources.orm.hibernate.models.zurple.SessionAnonymous;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SearchResultPageTest
        extends PageTest
{

    private SearchResultsPage page;

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
    public void testSearchResultsList(){

        Long thread_id = Thread.currentThread().getId();
        HashMap<String,String> params = ParametersFactory.getSearchParameters(thread_id);

        if ( params == null)
        {
            return;
        }

        String search_by = params.get("search_by");
        String search_criteria = params.get("search_criteria");
        String min_price = ((params.get("min_price") == null) ? "0" : params.get("min_price"));
        String max_price = ((params.get("max_price") == null) ? "0" : params.get("max_price"));
        String bedrooms = ((params.get("bedrooms") == null) ? "0" : params.get("bedrooms"));
        String bathrooms = ((params.get("bathrooms") == null) ? "0" : params.get("bathrooms"));
        String square_feet = ((params.get("square_feet") == null) ? "0" : params.get("square_feet"));
        String lot_sqft = ((params.get("lot_sqft") == null) ? "0" : params.get("lot_sqft"));
        String year_built = ((params.get("year_built") == null) ? "0" : params.get("year_built"));
        String types = ((params.get("types") == null) ? "" : params.get("types"));
        String features = ((params.get("features") == null) ? "" : params.get("features"));
        String styles = ((params.get("styles") == null) ? "" : params.get("styles"));
        String views = ((params.get("views") == null) ? "" : params.get("views"));

        if ( params.get("results_expected") != null )
        {
            Integer results_expected = Integer.parseInt(params.get("results_expected"));
            assertTrue(results_expected <= getPage().getNumberOfResults());
        }

        ParametersFactory.removeSearchParameters(thread_id);

        Cookie cks = getDriver().manage().getCookieNamed("PHPSESSID");
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
            if( !"".equals(types) )
            {
                typeList = Arrays.asList(types.split(","));
            }

            List<String> featureList = null;
            if( !"".equals(features) )
            {
                featureList = Arrays.asList(features.split(","));
            }

            List<String> viewList = null;
            if( !"".equals(views) )
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
