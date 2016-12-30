package com.zurple.resources.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

public class LoginForm
        extends resources.forms.LoginForm
{
    public String getErrorMessage(){
        try{
            return form.findElement(By.xpath("//descendant::p[@class=\"alert alert-danger\"]")).getText();
        }catch(StaleElementReferenceException e){
             return "";
        }
    }
}
