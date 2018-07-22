package resources.alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public abstract class AbstractAlert
{

    public static String alertXpath = "//div[contains(@class,\"sweet-alert\")]";

    protected WebElement alert;
    protected WebElement okButton;
    protected String message;

    public void setAlert(WebElement object){
        alert = object;
        try{
            okButton = alert.findElement(By.xpath("./descendant::button[@class=\"confirm\"]"));
            message = alert.findElement(By.xpath("./descendant::h2")).getText();
        }catch(NoSuchElementException e){}
    }

    public WebElement getAlert()
    {
        return alert;
    }

    public String getMessage()
    {
        return message;
    }

    public void setOkButton(WebElement object)
    {
        okButton = object;
    }

    public void clickOkButton()
    {
        okButton.click();
    }

    public boolean isVisible(){
        return alert.isDisplayed();
    }

    public void close()
    {
        alert.sendKeys(Keys.ESCAPE);
    }




}
