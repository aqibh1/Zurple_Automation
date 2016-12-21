package us.zengtest1.alerts;

import java.util.concurrent.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractAlert
{

    protected WebElement alert;
    protected WebElement okButton;

    public void setAlert(WebElement object){
        alert = object;
        okButton = alert.findElement(By.xpath("//descendant::button[@class=\"confirm\"]"));
    }

    public WebElement getAlert()
    {
        return alert;
    }

}
