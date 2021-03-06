package com.zurple.my.resources.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import resources.elements.Select2Dropdown;

public class LeadEditForm
        extends resources.forms.LoginForm
{

    private Select2Dropdown cityInput;

    public Select2Dropdown getCityInput() {

        if (cityInput == null){
            WebElement element = form.findElement(By.xpath("//descendant::select[@id=\"city_id_0\"]/following-sibling::span"));
            cityInput = new Select2Dropdown();
            cityInput.setDriver(getDriver());
            cityInput.setElement(element);
        }

        return cityInput;
    }

    public void toggleWelcomeEmail(){
        form.findElement(By.xpath("./descendant::input[@id=\"welcome_email\"]/following-sibling::span")).click();
    }

}
