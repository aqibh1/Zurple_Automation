package us.zengtest1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import resources.JssorSlider;
import resources.forms.ContactAgentForm;

public class PropertyDetailsPage
        extends Page
{

    private ContactAgentForm contactAgentForm;
    private JssorSlider jssorSlider;
    private Integer propertyId;

    public PropertyDetailsPage(String state, String city, String prop_id){
        url = "/" + state + "/" + city + "/"+prop_id;
        propertyId = Integer.parseInt(prop_id);
    }

    public PropertyDetailsPage(String prop_id){
        url = "/CA/San_Diego/"+prop_id;
        propertyId = Integer.parseInt(prop_id);
    }

    public Integer getPropertyId(){
        if ( propertyId == null){

            Pattern p = Pattern.compile("(\\d{2,})");
            Matcher m = p.matcher(driver.getCurrentUrl());
            while (m.find()) {
                propertyId = Integer.parseInt(m.group(0));
            }
        }
        return propertyId;
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

    public Integer getPrice(){
        String price_text = driver.findElement(By.xpath("//div[contains(concat(\" \",normalize-space(@class),\" \"),\" price \")]/h2")).getText();

        String price = price_text.replace("$","");
        Integer sPrice = new Integer(price);
        return sPrice;
    }

}
