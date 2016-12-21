package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        assertEquals("3958 Bayside Walk,  San Diego  (92109)", getPage().getHeader().getText());
    }

    public void testTitle() {
        assertEquals("3958 Bayside Walk San Diego, CA - MLS#: 160040130 - 7 Bed, 8 Bath, 4391 Sqft Home Built in 1978 | zengtest1.us", getPage().getTitle());
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

        try
        {
            sleep(3000);
            assertTrue(getPage().getSweetAlertNotification().getAlert().isDisplayed());
        }
        catch (InterruptedException e)
        {}

    }

}
