package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import resources.AbstractPage;

public class HomePage extends Page
{

    public HomePage(){
        url = "http://dev.zengtest1.us/";
    }

    public HomePage(String source_in_url){
        url = "http://dev.zengtest1.us/"+source_in_url;
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
