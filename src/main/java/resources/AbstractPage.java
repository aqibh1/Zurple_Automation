package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.alerts.BootstrapModal;
import resources.alerts.SweetAlertNotification;
import resources.classes.Asset;

public abstract class AbstractPage
{
    protected WebDriver driver;
    protected String url;

    public void setDriver(WebDriver driver){
        this.driver=driver;
        if(!this.driver.getCurrentUrl().equals(getFullUrl())){
            driver.get(getFullUrl());
        }
        focusOnPage();
    }
    public WebDriver getWebDriver() {
    	return driver;
    }
    protected abstract String getBaseUrl();

    public String getCurrentUrl(){
        return this.driver.getCurrentUrl();
    }

    public List<Asset> getAssets(){

        ArrayList<Asset> list = new ArrayList<Asset>();

        for (WebElement style: driver.findElements(By.xpath("/html/descendant::link[@type='text/css']"))) {
            Asset asset = new Asset();
            asset.setType(style.getAttribute("type"));
            asset.setUrl(style.getAttribute("href"));
            list.add(asset);
        }

        for (WebElement js: driver.findElements(By.xpath("/html/descendant::script"))) {
            Asset asset = new Asset();
            asset.setType(js.getAttribute("type"));
            asset.setUrl(js.getAttribute("src"));
            list.add(asset);
        }

        return list;
    };

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String u){
        url = u;
    }

    public String getFullUrl()
    {
        return getBaseUrl() + url;
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void pressButton(Keys key){
        pressButton(key,0);
    }

    public void focusOnPage()
    {
        String currentWindow = driver.getWindowHandle();
        driver.switchTo().window(currentWindow);
    }

    public void pressButton(Keys key, int timeout)
    {
        Actions builder = new Actions(driver);
        Action button= builder
                .sendKeys(key)
                .build();
        button.perform();

        try {
            Thread.sleep(timeout);
        } catch (InterruptedException qq) {
            qq.printStackTrace();
        }
    }

//    protected BootstrapModal bootstrapModal;
//
//    public BootstrapModal getBootstrapModal(){
// 
//        if(null == bootstrapModal){
//            bootstrapModal = new BootstrapModal();
//
//            Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BootstrapModal.alertXpath)));
//
//            bootstrapModal.setAlert(driver.findElement(
//                    By.xpath(BootstrapModal.alertXpath)));
//            bootstrapModal.setDriver(driver);
//        }
//        return bootstrapModal;
//    }
//    
//    public void clearBootstrapModal()
//    {
//        bootstrapModal = null;
//    }
//
//    public boolean checkBootsrapModalIsShown(){
//    	try{
//    		if(null==bootstrapModal && !driver.findElement(By.xpath(BootstrapModal.alertXpath)).isDisplayed()) {
//
//    			return false;
//
//    		}else {
//    			getBootstrapModal();
//
//    			return bootstrapModal.isVisible();
//    		}
//    	}catch(StaleElementReferenceException e){
//    		return false;
//    	}catch(NoSuchElementException e){
//    		return false;
//    	}
//    }
//    public boolean checkBootsrapModalIsShown(){
//        try{
//        	if(isBootStrapModelxist()) {
//        	getBootstrapModal();
//            return bootstrapModal.isVisible();
//        	}else {
//        		return false;
//        	}
//        }catch(StaleElementReferenceException e){
//            return false;
//        }catch(NoSuchElementException e){
//            return false;
//        }
//    }
}
