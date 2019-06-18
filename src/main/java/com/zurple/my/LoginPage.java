package com.zurple.my;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.zurple.my.resources.forms.LoginForm;

public class LoginPage
        extends Page
{

    private LoginForm loginForm;

    public LoginPage(){
        url = "/access/login";
    }

    public boolean checkLoginFormExists(){
        try{
            getLoginForm();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public LoginForm getLoginForm(){
        loginForm = new LoginForm();
        loginForm.setForm(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/form")));
        return loginForm;
    }

    public WebElement getForgotPasswordLink(){
        return driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/small/a"));
    }
    //TODO - this method is marker that we should refactor our templates
    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"logo\"]/a/img"));
    }

}
