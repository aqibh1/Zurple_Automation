package com.zurple.my;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.zurple.my.resources.forms.LoginForm;

public class ForgotPage
        extends Page
{

    private LoginForm loginForm;

    public ForgotPage(){
        url = "/forgot";
    }

    //TODO - this method is marker that we should refactor our templates
    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"logo\"]/a/img"));
    }

}
