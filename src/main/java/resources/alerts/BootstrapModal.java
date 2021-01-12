package resources.alerts;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

public class BootstrapModal extends AbstractAlert
{
	protected BootstrapModal bootstrapModal;
	
    public static String alertXpath = "//div[contains(@class,\"modal fade in\")]";

    @FindBy(xpath="//div[@class='modal fade in']/descendant::button[@class='close']")
    WebElement close_modal;
    
    public void close() {
        alert.findElement(By.xpath("./descendant::button[@class=\"close\"]")).click();

        List<WebElement> els = new ArrayList<>();
        els.add(alert);

        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        wait.until(ExpectedConditions.invisibilityOfAllElements(els));

    }
    public BootstrapModal(){
    }
    public BootstrapModal(WebDriver pWebDriver){
    	driver=pWebDriver;
    	PageFactory.initElements(driver, this);
    }
    public BootstrapModal getBootstrapModal(){
    	 
        if(null == bootstrapModal){
            bootstrapModal = new BootstrapModal();

            Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BootstrapModal.alertXpath)));

            bootstrapModal.setAlert(driver.findElement(
                    By.xpath(BootstrapModal.alertXpath)));
            bootstrapModal.setDriver(driver);
        }
        return bootstrapModal;
    }
    
    public void clearBootstrapModal()
    {
        bootstrapModal = null;
    }

    public boolean checkBootsrapModalIsShown(){
    	try{
    		if(null==bootstrapModal && !driver.findElement(By.xpath(BootstrapModal.alertXpath)).isDisplayed()) {

    			return false;

    		}else {
    			getBootstrapModal();

    			return bootstrapModal.isVisible();
    		}
    	}catch(StaleElementReferenceException e){
    		return false;
    	}catch(NoSuchElementException e){
    		return false;
    	}
    }
    public boolean closeModal() {
    	try {
    		WebElement crossButton = driver.findElement(By.xpath("//div[@class='modal fade in']/descendant::button[@class='close']"));
    		ActionHelper.Click(driver, crossButton);
    		return !ActionHelper.isElementVisible(driver, crossButton);
    	}catch(Exception ex) {
    		AutomationLogger.info("No cross button to close lead capture form..");
        	return false;
        }

    }
    public boolean closeBootstrapModal() {
    	boolean isSuccess = false;
    	if(ActionHelper.waitForElementToBeVisible(driver, close_modal, 30)) {
    		isSuccess = ActionHelper.Click(driver, close_modal);
    	}
    	return isSuccess;
    }

}
