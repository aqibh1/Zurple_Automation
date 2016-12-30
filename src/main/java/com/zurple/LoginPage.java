package com.zurple;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import resources.AbstractPage;
import resources.forms.LoginForm;

public class LoginPage
        extends AbstractPage
{

    private LoginForm loginForm;

    public LoginPage(){
        url = "http://my.dev.zurple.com";
    }

    public LoginForm getLoginForm(){
        if(null == loginForm){
            loginForm = new LoginForm();
            loginForm.setForm(driver.findElement(By.xpath("//*[@id=\"form\"]/form")));
        }
        return loginForm;
    }

    public WebElement getSignUpLink(){
        return driver.findElement(By.xpath("//*[@id=\"form\"]/p[1]/a"));
    }
    //TODO - this method is marker that we should refactor our templates
    public WebElement getTopMenu(){
        return driver.findElement(By.xpath("//*[@id=\"newnavi\"]/ul"));
    }
    //TODO - this method is marker that we should refactor our templates
    public WebElement getHeader(){
        return driver.findElement(By.xpath("//*[@id=\"search_box\"]/div[3]/div/div/h1"));
    }
    //TODO - this method is marker that we should refactor our templates
    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"mainlogo\"]"));
    }

}
