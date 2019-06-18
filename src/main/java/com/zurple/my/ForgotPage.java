package com.zurple.my;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ForgotPage
        extends Page
{

    public ForgotPage(){
        url = "/forgot";
    }

    //TODO - this method is marker that we should refactor our templates
    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"logo\"]/a/img"));
    }

}
