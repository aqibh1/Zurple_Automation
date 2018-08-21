package us.zengtest1;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.AbstractPageTest;
import resources.alerts.SweetAlertNotification;
import resources.classes.SearchResult;
import resources.orm.hibernate.models.Property;
import resources.orm.hibernate.models.SessionAnonymous;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.*;

public class PropertyDetailsPageTest
        extends PageTest
{

    private static PropertyDetailsPage page;

    public void clearPage(){
        page=null;
    };

    public PropertyDetailsPage getPage(){
        if(page == null){

            Pattern p = Pattern.compile("(\\d{2,})");
            Matcher m = p.matcher(driver.getCurrentUrl());
            while (m.find()) {
                page = new PropertyDetailsPage(m.group(0));
                page.setDriver(getDriver());
            }

        }
        return page;
    }

    @Test
    public void testHeader() {
        assertEquals("Shell Canyon Rd.,  Ocotillo  (92259)", getPage().getHeader().getText());
    }

    @Test
    public void testTitle() {
        assertEquals("Shell Canyon Rd. Ocotillo, CA - MLS#: 160046005 | zengtest1.us", getPage().getTitle());
    }

    @Test
    public void testBrand() {
        assertEquals("ZENG TEST PROPERTIES", getPage().getBrand().getText());
    }

    @Test
    public void testSubmittingEmptyContactAgentForm(){
        getPage().getContactAgentForm().clearFields();
        getPage().getContactAgentForm().submit();

        for (WebElement input: getPage().getContactAgentForm().getRequiredInputs()) {
            assertFalse(getPage().getContactAgentForm().checkInputHasCorrectValue(input));
        }

    }

    @Test
    public void testSlider(){

        int currentSlide;

        //We must focus on page to run slider scrolling
        getPage().focusOnPage();

        currentSlide=getPage().getSlider().getCurrentActiveSlide();

        getPage().pressButton(Keys.ARROW_RIGHT,1000);
        assertEquals(currentSlide + 1, getPage().getSlider().getCurrentActiveSlide());

        getPage().pressButton(Keys.ARROW_LEFT, 1000);
        assertEquals(currentSlide,getPage().getSlider().getCurrentActiveSlide());

        //Focusing on input
        getPage().getContactAgentForm().setInputValue("name","");

        getPage().pressButton(Keys.ARROW_LEFT, 1000);
        assertEquals(currentSlide,getPage().getSlider().getCurrentActiveSlide());

        getPage().pressButton(Keys.ARROW_RIGHT,1000);
        assertEquals(currentSlide,getPage().getSlider().getCurrentActiveSlide());

    }

    @Test
    public void testSubmittingFilledContactAgentForm(){
        getPage().getContactAgentForm().setInputValue("name","John Doe");
        getPage().getContactAgentForm().setInputValue("email","jdoe@test.com");
        getPage().getContactAgentForm().setInputValue("phone","4958435471");
        getPage().getContactAgentForm().setTextareaValue("comment","Some question");
        getPage().getContactAgentForm().submit();

        for (WebElement input: getPage().getContactAgentForm().getRequiredInputs()) {
            assertTrue(getPage().getContactAgentForm().checkInputHasCorrectValue(input));
        }

        Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SweetAlertNotification.alertXpath)));

        assertTrue(getPage().getSweetAlertNotification().getAlert().isDisplayed());
        assertEquals("Your question has been sent.", getPage().getSweetAlertNotification().getMessage());

        getPage().getSweetAlertNotification().close();
        assertFalse(getPage().getSweetAlertNotification().getAlert().isDisplayed());

    }

    @Test
    @Parameters({"property_id"})
    public void testAddingPropertyToFavorites(@Optional("0") Integer property_id)
    {
        getPage().getFavoriteButton().click();

        Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SweetAlertNotification.alertXpath)));

        assertTrue(getPage().getSweetAlertNotification().getAlert().isDisplayed());
        assertEquals("Added to your favorites.", getPage().getSweetAlertNotification().getMessage());

        getPage().getSweetAlertNotification().clickOkButton();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(SweetAlertNotification.alertXpath)));
        assertFalse(getPage().getSweetAlertNotification().getAlert().isDisplayed());

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
    public void testSearchResult(
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
            @Optional("0") String results_expected
    ){

        Property property = getEnvironment().getDetailedProperty(getPage().getPropertyId());
        assertEquals(property.getStatus(),"Active");

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

        if ( !"0".equals(min_price) ){
            assertEquals(expected.get("user_activity_search_price_minimum"),real.get("user_activity_search_price_minimum"));
        }

        if ( !"0".equals(max_price) ){
            assertEquals(expected.get("user_activity_search_price_maximum"),real.get("user_activity_search_price_maximum"));
        }

        assertEquals(expected.get("user_activity_search_bedrooms_minimum"),real.get("user_activity_search_bedrooms_minimum"));
        assertEquals(expected.get("user_activity_search_bathrooms_minimum"),real.get("user_activity_search_bathrooms_minimum"));
        assertEquals(expected.get("user_activity_search_square_feet_minimum"),real.get("user_activity_search_square_feet_minimum"));
        assertEquals(expected.get("user_activity_search_lot_square_feet_minimum"),real.get("user_activity_search_lot_square_feet_minimum"));


        List<String> typeList = null;
        if( types.isEmpty() == false)
        {
            typeList = Arrays.asList(types.split(","));
            assertTrue(typeList.contains(property.getPropType()));
        }

        List<String> featureList = null;
        if( features.isEmpty() == false)
        {
            featureList = Arrays.asList(features.split(","));
            List<String> propertiesFeatures = property.buildFeaturesList();
            for(String feature:featureList){
                assertTrue(propertiesFeatures.contains(feature));
            }
        }

        List<String> viewList = null;
        if( views.isEmpty() == false)
        {
            viewList = Arrays.asList(views.split(","));
        }

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

        if (search_by.equals("address")){
            assertTrue(search_criteria.contains(property.getAddress()));
        }

        if (search_by.equals("mls")){
            assertEquals(String.valueOf(property.getMlsNumber()),search_criteria);
        }

    }

}
