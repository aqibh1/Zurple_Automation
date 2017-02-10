package com.zurple.resources.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.classes.Helper;
import resources.classes.RadioButton;

public class MassEmailForm
        extends resources.forms.LoginForm
{

    private  List<RadioButton> recipientsList = new ArrayList<RadioButton>();

    @Override
    public void setForm(WebElement object){
        form = object;
    }

    //TODO submit button is outside of form
    public void setSubmitButton(WebElement object){
        submitButton = object;
    }

    /**
     * We need to override method because iframe is used on page
     * @param inputName
     * @param value
     */
    @Override
    public void setTextareaValue(String inputName, String value)
    {
        //Diving inside iframe
        getDriver().switchTo().frame(getDriver().findElement(By.xpath("//*[@id=\"cke_contents_body\"]/iframe")));
        getDriver().findElement(By.xpath("/html/body")).sendKeys(value);
        //Floating back to the surface
        getDriver().switchTo().defaultContent();
    }

    public List<RadioButton> getRecipientsList(){
        if(recipientsList.isEmpty()){
            try{
                for (WebElement element: form.findElements(By.xpath("./descendant::input[@name=\"massemail_type\"]"))) {
                    RadioButton recipient = new RadioButton();
                    recipient.setElement(element);
                    recipient.setLabel(element.findElement(By.xpath("..")).getText());
                    recipientsList.add(recipient);
                }
            }catch(StaleElementReferenceException e){}
        }
        return recipientsList;
    }

    public RadioButton getRecipientByLabel(String label)
            throws Exception
    {
        for(RadioButton recipient: getRecipientsList()){
            if(recipient.getLabel().equals(label)){
                return recipient;
            }
        }
        throw new Exception("Can't find recipient option with label '"+label+"'");
    }

    //TODO not unified loader
    private WebElement getLoader(){
        return getDriver().findElement(By.xpath("/html/body/div[@aria-labelledby=\"ui-id-4\"]"));
    }

    public Boolean waitWhileSubmitting(){
        try{
            Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Helper.generateXPATH(getLoader(),""))));
            return true;
        }catch(StaleElementReferenceException e){
             return false;
        }catch(TimeoutException e){
             return false;
        }
    }

}
