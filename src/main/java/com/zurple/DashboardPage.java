package com.zurple;

import com.zurple.resources.forms.LoginForm;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class DashboardPage
        extends Page
{

    public DashboardPage(){
        url = "https://my.dev.zurple.com/dashboard";
    }


}
