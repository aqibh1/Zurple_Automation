package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import us.zengtest1.alerts.SweetAlertNotification;
import us.zengtest1.forms.ContactAgentForm;
import us.zengtest1.forms.LoginForm;

public class PropertyDetailsPage
        extends AbstractPage
{

    private ContactAgentForm contactAgentForm;
    private SweetAlertNotification sweetAlertNotification;

    public PropertyDetailsPage(){
        url = "http://dev.zengtest1.us/CA/San_Diego/101217";
    }

    public ContactAgentForm getContactAgentForm(){
        if(null == contactAgentForm){
            contactAgentForm = new ContactAgentForm();
            contactAgentForm.setForm(driver.findElement(By.xpath("//*[@id=\"askq_and_schedule_showing\"]")));
        }
        return contactAgentForm;
    }

    public WebElement getTopMenu(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/nav/div/div[2]/ul"));
    }

    public WebElement getHeader(){
        return driver.findElement(By.xpath("//*[@id=\"propdetail\"]/div[1]/div[1]/h2"));
    }

    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/nav/div/div[1]/a"));
    }

    public WebElement getFavoriteButton(){
        return driver.findElement(By.xpath("//*[@id=\"add_fav\"]/button"));
    }

    public SweetAlertNotification getSweetAlertNotification(){
        if(null == sweetAlertNotification){
            sweetAlertNotification = new SweetAlertNotification();
            sweetAlertNotification.setAlert(driver.findElement(
                    By.xpath(SweetAlertNotification.alertXpath)));
        }
        return sweetAlertNotification;
    }

}
