package com.z57.site.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import resources.AbstractPage;
import resources.ConfigReader;
import resources.alerts.SweetAlertNotification;
import resources.interfaces.HasHeader;

/**
 * todo
 *
 * @author Vladimir
 */
public abstract class Page extends AbstractPage implements HasHeader
{

    private String baseUrl = null;
    protected static String DYNAMIC_VARIABLE="[--Dynamic Variable--]";
    
    protected String getBaseUrl(){
        if (baseUrl == null){
            ConfigReader configReader = ConfigReader.load();
            baseUrl = configReader.getPropertyByName("z57_site_v2_base_url");
        }
        return baseUrl;
    }

    private SweetAlertNotification sweetAlertNotification;

    public SweetAlertNotification getSweetAlertNotification(){
        if(null == sweetAlertNotification){
            sweetAlertNotification = new SweetAlertNotification();
            sweetAlertNotification.setAlert(driver.findElement(
                    By.xpath(SweetAlertNotification.alertXpath)));
        }
        return sweetAlertNotification;
    }

    public WebElement getTopMenu(){
        return driver.findElement(By.xpath("//*[@id=\"menu-top-navigation\"]"));
    }

    public WebElement getUserMenu(){
        return driver.findElement(By.xpath("//div[contains(concat(\" \",normalize-space(@class),\" \"),\" user_menu \")]"));
    }


}
