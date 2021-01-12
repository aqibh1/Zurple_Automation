package com.z57.site.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.AbstractPage;
import resources.ConfigReader;
import resources.alerts.SweetAlertNotification;
import resources.interfaces.HasHeader;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;
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
    protected static String DYNAMIC_VARIABLE="[--Dynamic Variable--]";
    protected WebDriverWait wait;
    
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
    public void waitForLoadingOfPage(long pWaitTimeForPageToGetLoad) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, pWaitTimeForPageToGetLoad);
        wait.until(pageLoadCondition);
    }
   
   
    protected boolean type(WebElement pInputField, String pStringToType) {
		boolean isSuccessfull=false;
		try {
			if(wait.until(ExpectedConditions.visibilityOf(pInputField))!=null) {
				pInputField.sendKeys(pStringToType);
				isSuccessfull=true;
			}
			
		}catch(Exception ex) {
			AutomationLogger.info("Unable to type in input field "+pInputField.getAttribute("xpath"));
			AutomationLogger.info("String to type : "+pStringToType);
		}
		return isSuccessfull;
	}
	
    protected boolean click(WebElement pElementToBeClicked) {
		boolean isSuccessfull=false;
		try {
			if(wait.until(ExpectedConditions.visibilityOf(pElementToBeClicked))!=null) {
				pElementToBeClicked.click();
				isSuccessfull=true;
			}
			
		}catch(Exception ex) {
			AutomationLogger.info("Unable to Click on "+pElementToBeClicked.getAttribute("xpath"));
		}
		return isSuccessfull;
	}
	
    protected boolean waitForElementToBeDisappeared(WebElement pElementToBeDisappeared) {
		return wait.until(ExpectedConditions.invisibilityOf(pElementToBeDisappeared));
		
	}
    protected WebElement getDynamicElement(String pXpath,String pDynamicVariable) {
  		try {
  		return driver.findElement(By.xpath(pXpath.replace(FrameworkConstants.DYNAMIC_VARIABLE, pDynamicVariable)));
  		}catch(Exception ex) {
  			AutomationLogger.error("Unable to get dynamic webelement for xpath "+pXpath);
  			return null;
  		}
  	}
}
