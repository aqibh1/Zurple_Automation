package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractPage
{

    public LoginPage(){
        url = "http://dev.zengtest1.us/login";
    }

    public WebElement getLoginForm(){
        return driver.findElement(By.xpath("//*[@id=\"form\"]"));
    }

    public WebElement getSignUpLink(){
        return driver.findElement(By.xpath("//*[@id=\"form\"]/p[1]/a"));
    }

    public WebElement getTopMenu(){
        return driver.findElement(By.xpath("//*[@id=\"newnavi\"]/ul"));
    }

    public WebElement getHeader(){
        return driver.findElement(By.xpath("//*[@id=\"search_box\"]/div[3]/div/div/h1"));
    }

    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"mainlogo\"]"));
    }

}
