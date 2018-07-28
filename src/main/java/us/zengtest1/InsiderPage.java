package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.elements.Button;
import resources.forms.ContactAgentForm;
import us.zengtest1.resources.forms.InquireContactForm;
import us.zengtest1.resources.forms.InquireForm;

public class InsiderPage
        extends Page
{

    private InquireForm inquireForm;
    private InquireContactForm inquireContactForm;
    
    public InsiderPage(){
        url = "/insider/4";
    }

    public WebElement getHeader(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div/div[1]/h3"));
    }

    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/nav/div/div[1]/a"));
    }

    public WebElement getTopMenu(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/nav/div/div[2]/ul"));
    }

    public InquireForm getInquireForm(){
        if(null == inquireForm){
            inquireForm = new InquireForm();
            inquireForm.setForm(driver.findElement(By.xpath("//*[@id=\"question-0\"]/ancestor::div[@class='block col-md-5']")));
        }
        return inquireForm;
    }

    public InquireContactForm getInquireContactForm(){
        if(null == inquireContactForm){
            inquireContactForm = new InquireContactForm();
            inquireContactForm.setForm(driver.findElement(By.xpath("//*[@id=\"ask_box_container\"]")));
        }
        return inquireContactForm;
    }

}
