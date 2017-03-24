package resources.forms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import resources.classes.FormErrorMessage;
import resources.interfaces.UsesDriver;

public abstract class ZendFrameworkForm extends AbstractForm
{

    @Override
    public List<FormErrorMessage> getFormErrorMessagesList(){
        List<FormErrorMessage> errorsList = new ArrayList<FormErrorMessage>();
        try{
            List<WebElement> allErrorRows = form.findElements(By.xpath("//descendant::p[@class=\"form-element-error\"]"));
            for(WebElement error: allErrorRows){
                FormErrorMessage e = new FormErrorMessage();
                e.setMessage(error.getText());
                errorsList.add(e);
            }
        }catch(NoSuchElementException e){}

        return errorsList;
    }

}
