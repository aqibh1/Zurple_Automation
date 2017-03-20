package com.zurple.my;

import com.zurple.my.resources.forms.LoginForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PropertiesPage
        extends Page
{

    public PropertiesPage(){
        url = "https://my.dev.zurple.com/properties";
    }

    //TODO - this method is marker that we should refactor our templates
    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"logo\"]/a/img"));
    }

}
