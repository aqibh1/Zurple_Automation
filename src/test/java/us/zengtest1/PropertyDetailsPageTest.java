package us.zengtest1;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import us.zengtest1.alerts.SweetAlertNotification;

import static java.lang.Thread.sleep;

public class PropertyDetailsPageTest
        extends AbstractPageTest
{

    private static PropertyDetailsPage page;

    public PropertyDetailsPage getPage(){
        if(page == null){
            page = new PropertyDetailsPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void testHeader() {
        assertEquals("Shell Canyon Rd.,  Ocotillo  (92259)", getPage().getHeader().getText());
    }

    public void testTitle() {
        assertEquals("Shell Canyon Rd. Ocotillo, CA - MLS#: 160046005 | zengtest1.us", getPage().getTitle());
    }


    public void testBrand() {
        assertEquals("ZENG TEST PROPERTIES", getPage().getBrand().getText());
    }

    public void testSubmittingEmptyContactAgentForm(){
        getPage().getContactAgentForm().clearFields();
        getPage().getContactAgentForm().submit();

        for (WebElement input: getPage().getContactAgentForm().getRequiredInputs()) {
            assertFalse(getPage().getContactAgentForm().checkInputHasCorrectValue(input));
        }

    }

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

    public void testAddingPropertyToFavorites()
    {
        getPage().getFavoriteButton().click();

        Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SweetAlertNotification.alertXpath)));

        assertTrue(getPage().getSweetAlertNotification().getAlert().isDisplayed());
        assertEquals("Please sign in to use all features.", getPage().getSweetAlertNotification().getMessage());

        /*
        Wait wait2 = new FluentWait(driver)
                .withTimeout(1, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        wait2.until(new Function() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("foo"));
            }
        });*/
    }

}
