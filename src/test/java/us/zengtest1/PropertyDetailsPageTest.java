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
import resources.ParametersFactory;
import resources.alerts.SweetAlertNotification;
import resources.orm.hibernate.models.zurple.Property;
import resources.orm.hibernate.models.zurple.SessionAnonymous;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.*;

public class PropertyDetailsPageTest
        extends PageTest
{

    private PropertyDetailsPage page;

    public void clearPage(){
        page=null;
    };

    public PropertyDetailsPage getPage(){
        if(page == null){

            Pattern p = Pattern.compile("([A-Z]{2})\\/([a-zA-Z\\_]+)\\/(\\d+)$");
            Matcher m = p.matcher(getDriver().getCurrentUrl());
            while (m.find()) {
                page = new PropertyDetailsPage(m.group(1),m.group(2),m.group(3));
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
    public void testSearchResult(){

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

        Property property = getEnvironment().getDetailedProperty(getPage().getPropertyId());
        assertEquals(property.getStatus(),"Active");

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
