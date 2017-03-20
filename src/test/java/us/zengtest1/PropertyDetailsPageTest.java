package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import resources.AbstractPageTest;
import resources.alerts.SweetAlertNotification;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PropertyDetailsPageTest
        extends PageTest
{

    private static PropertyDetailsPage page;

    public void clearPage(){
        page=null;
    };

    public PropertyDetailsPage getPage(){
        if(page == null){
            page = new PropertyDetailsPage();
            page.setDriver(getDriver());
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
    public void testAddingPropertyToFavorites()
    {
        /*getPage().getFavoriteButton().click();

        Wait<FirefoxDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SweetAlertNotification.alertXpath)));

        assertTrue(getPage().getSweetAlertNotification().getAlert().isDisplayed());
        assertEquals("Please sign in to use all features.", getPage().getSweetAlertNotification().getMessage());
          */
        /*
        Wait wait2 = new FluentWait(driver)
                .withTimeout(1, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        wait2.until(new Function() {
            public WebElement apply(FirefoxDriver driver) {
                return driver.findElement(By.id("foo"));
            }
        });*/
    }

}
