package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage
{

    protected WebDriver driver;
    protected String url;

    public HomePage(){
        url = "http://dev.zengtest1.us/";
    }

    public String getUrl()
    {
        return url;
    }

    public void setDriver(WebDriver driver){
        this.driver=driver;
        driver.get(getUrl());
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public WebElement getHeader(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div/div[1]/h3"));
    }

    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/nav/div/div[1]/a"));
    }

    public WebElement getTopMenu(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/nav/div/div[2]/ul"));
    }

}
