package com.zurple.my;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PropertiesPage
        extends Page
{

    public PropertiesPage(){
        url = "/properties";
    }

    //TODO - this method is marker that we should refactor our templates
    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"logo\"]/a/img"));
    }

}
