package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import resources.AbstractPage;
import resources.JssorSlider;
import resources.alerts.SweetAlertNotification;
import resources.forms.ContactAgentForm;
import resources.forms.LoginForm;

public class PropertyDetailsPage
        extends Page
{

    private ContactAgentForm contactAgentForm;
    private JssorSlider jssorSlider;

    public PropertyDetailsPage(){
        url = "/CA/San_Diego/101217";
    }

    public ContactAgentForm getContactAgentForm(){
        if(null == contactAgentForm){
            contactAgentForm = new ContactAgentForm();
            contactAgentForm.setForm(driver.findElement(By.xpath("//*[@id=\"askq_and_schedule_showing\"]")));
        }
        return contactAgentForm;
    }

    public JssorSlider getSlider(){
        if(null == jssorSlider){
            jssorSlider = new JssorSlider();
            jssorSlider.setSlider(driver.findElement(By.xpath("//*[@id=\"slider_container\"]")));
        }
        return jssorSlider;
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

}
