package resources.alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public abstract class AbstractAlert {

    protected WebElement alert;
    protected WebElement okButton;
    protected WebElement cancelButton;
    protected String message;

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
}
