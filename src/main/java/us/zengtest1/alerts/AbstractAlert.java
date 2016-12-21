package us.zengtest1.alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class AbstractAlert
{

    protected WebElement alert;
    protected WebElement okButton;
    protected String message;

    public void setAlert(WebElement object){
        alert = object;
        okButton = alert.findElement(By.xpath("//descendant::button[@class=\"confirm\"]"));
        message = alert.findElement(By.xpath("//descendant::div[contains(@class,\"sweet-alert\")]/h2")).getText();
    }

    public WebElement getAlert()
    {
        return alert;
    }

    public String getMessage()
    {
        return message;
    }


}
