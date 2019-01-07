package com.z57.site.v2;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.AbstractPage;
import resources.ConfigReader;
import resources.alerts.BootstrapModal;
import resources.alerts.SweetAlertNotification;
import resources.interfaces.HasHeader;
import us.zengtest1.resources.forms.RegisterForm;

/**
 * todo
 *
 * @author Vladimir
 */
public abstract class Page extends AbstractPage implements HasHeader
{

    private String baseUrl = null;
    private RegisterForm registerForm;

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

    public RegisterForm getRegisterForm(){
        if(null == registerForm){
            registerForm = new RegisterForm();
            registerForm.setForm(driver.findElement(By.xpath("//*[@id=\"zfs_idx_lead_reg_form\"]")));
            registerForm.setSubmitButton(driver.findElement(By.xpath("//*[@id=\"wp-submit-register_topbar\"]")));
        }
        return registerForm;
    }
    
}
