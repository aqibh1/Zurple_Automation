package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import resources.alerts.SweetAlertNotification;
import us.zengtest1.resources.forms.InquireContactForm;
import us.zengtest1.resources.forms.InquireForm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class InsiderPageTest
        extends PageTest
{

    private InsiderPage page;

    public InsiderPage getPage(){
        if(page == null){
            page = new InsiderPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test(priority=10)
    public void testTitle() {
        assertEquals("San Diego Sold Homes", getPage().getTitle());
    }

    @Test(priority=10)
    public void testHeader() {
        assertEquals("6,873 HOMES FOR SALE IN SAN DIEGO, CA AND NEARBY", getPage().getHeader().getText());
    }

    @Test(priority=20)
    public void testBrand() {
        assertEquals(getPage().getBrand().getText(), "ZENG TEST PROPERTIES");
    }
    
    @Test
    public void testAgentInquire() {
        
        InquireForm inquireForm = getPage().getInquireForm();
        assertTrue(inquireForm.checkElementExistsById("question-0"));
        assertTrue(inquireForm.checkElementExistsById("ask_question-0"));
        inquireForm.setTextareaValue("question-0","My very important question!");
        WebElement submitButton =  inquireForm.getElementById("ask_question-0");
        inquireForm.setSubmitButton(submitButton);
        
        inquireForm.submit();
        
        InquireContactForm inquireContactForm = getPage().getInquireContactForm();
        assertTrue(inquireContactForm.checkElementExistsById("first_name"));
        assertTrue(inquireContactForm.checkElementExistsById("last_name"));
        assertTrue(inquireContactForm.checkElementExistsById("username"));
        assertTrue(inquireContactForm.checkElementExistsById("email"));
        assertTrue(inquireContactForm.checkElementExistsById("phone"));
        assertTrue(inquireContactForm.checkElementExistsById("ask_question_send_button"));
        
        submitButton =  inquireContactForm.getElementById("ask_question_send_button");
        inquireContactForm.setSubmitButton(submitButton);
        inquireContactForm.submit();

        Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SweetAlertNotification.alertXpath)));

        assertTrue(getPage().getSweetAlertNotification().getAlert().isDisplayed());
        assertEquals("Your question has been sent.", getPage().getSweetAlertNotification().getMessage());

        getPage().getSweetAlertNotification().clickOkButton();

        
    }
    
    

}
