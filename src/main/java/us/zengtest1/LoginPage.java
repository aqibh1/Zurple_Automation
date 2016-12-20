package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends HomePage
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
    @Override
    public WebElement getTopMenu(){
        return driver.findElement(By.xpath("//*[@id=\"newnavi\"]/ul"));
    }
    @Override
    public WebElement getHeader(){
        return driver.findElement(By.xpath("//*[@id=\"mainlogo\"]"));
    }

}
