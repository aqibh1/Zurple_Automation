package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import resources.forms.LoginForm;
import us.zengtest1.resources.forms.RegisterForm;

public class RegisterPage
        extends Page
{

    private RegisterForm registerForm;

    public RegisterPage(){
        url = "http://dev.zengtest1.us/register";
    }

    public RegisterForm getRegisterForm(){
        if(null == registerForm){
            registerForm = new RegisterForm();
            registerForm.setForm(driver.findElement(By.xpath("//*[@id=\"register_form\"]")));
        }
        return registerForm;
    }

    //TODO - this method is marker that we should refactor our templates
    public WebElement getTopMenu(){
        return driver.findElement(By.xpath("//*[@id=\"newnavi\"]/ul"));
    }
    //TODO - this method is marker that we should refactor our templates
    public WebElement getHeader(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div/div/div[2]/div[1]/div/h3"));
    }
    //TODO - this method is marker that we should refactor our templates
    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@class=\"navbar-brand\"]"));
    }

}
