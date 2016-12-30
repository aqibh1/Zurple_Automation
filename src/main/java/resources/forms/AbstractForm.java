package resources.forms;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class AbstractForm
{
    protected WebElement form;
    protected WebElement submitButton;

    public void setForm(WebElement object){
        form = object;
        submitButton = form.findElement(By.xpath("//descendant::button[@type=\"submit\"]"));
    }

    public void submit(){
        submitButton.click();
    }

    public void clearFields(){

        List<WebElement> allInputs = form.findElements(
                By.xpath("//descendant::input[@type=\"input\" or @type=\"email\" or @type=\"password\"]"));

        for (WebElement input: allInputs) {
            input.sendKeys("");
        }

    }
    //TODO - we should add required attribute to inputs
    public List<WebElement> getRequiredInputs(){
        return form.findElements(By.xpath("//descendant::input[@required]"));
    }

    public boolean checkInputHasCorrectValue(WebElement input){

        if(
            input.getAttribute("class").contains("error") ||
            input.findElement(By.xpath("/preceding-sibling::label")).getAttribute("class") == "required"
        ){
            return false;
        }

        return true;
    }

    public void setInputValue(String inputName, String value)
    {
        form.findElement(By.xpath("//descendant::input[@id=\""+inputName+"\"]")).sendKeys(value);
    }

    public void setTextareaValue(String inputName, String value)
    {
        form.findElement(By.xpath("//descendant::textarea[@id=\""+inputName+"\"]")).sendKeys(value);
    }

}
