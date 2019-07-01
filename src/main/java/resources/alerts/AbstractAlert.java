package resources.alerts;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

public abstract class AbstractAlert {

    protected WebElement alert;
    protected WebElement okButton;
    protected WebElement cancelButton;
    protected String message;
    protected WebDriver driver;
	protected ActionHelper actionHelper;

    public void setAlert(WebElement object) {
        alert = object;
    }

    public WebElement getAlert() {
        return alert;
    }

    public String getMessage() {
        return message;
    }

    public void setOkButton(WebElement object) {
        okButton = object;
    }

    public void clickOkButton() {
        okButton.click();
    }

    public void setCancelButton(WebElement object) {
        cancelButton = object;
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public boolean isVisible() {
        return alert.isDisplayed();
    }

    public void close() {
        alert.sendKeys(Keys.ESCAPE);
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    protected void setPageObject(WebDriver pWebDriver, Object pObject){
		 driver = pWebDriver;
		 actionHelper = new ActionHelper(driver);
		 PageFactory.initElements(driver, pObject); 
	 }
}
