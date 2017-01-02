package resources;

import java.security.Key;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import resources.interfaces.HavingHeader;

public abstract class AbstractPage
{
    protected WebDriver driver;
    protected String url;

    public void setDriver(WebDriver driver){
        this.driver=driver;
        driver.get(getUrl());
        focusOnPage();
    }

    public String getUrl()
    {
        return url;
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void pressButton(Keys key){
        pressButton(key,0);
    }

    public void focusOnPage()
    {
        driver.findElement(By.xpath("/html/body")).click();
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
}
