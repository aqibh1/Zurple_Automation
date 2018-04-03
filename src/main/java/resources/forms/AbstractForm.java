package resources.forms;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import resources.classes.Alert;
import resources.classes.FormErrorMessage;
import resources.interfaces.UsesDriver;

public abstract class AbstractForm implements UsesDriver
{
    protected WebElement form;
    protected WebElement submitButton;
    protected WebDriver driver;

    public void setForm(WebElement object){
        form = object;
        try{
            setSubmitButton(form.findElement(By.xpath("./descendant::*[@type=\"submit\"]")));
        }catch(NoSuchElementException e){}
    }

    public void setSubmitButton(WebElement object){
        submitButton = object;
    }

    public void submit(){
        submitButton.click();
    }

    public void clearFields(){

        List<WebElement> allInputs = form.findElements(
                By.xpath("./descendant::input[@type=\"text\" or @type=\"input\" or @type=\"email\" or @type=\"password\"]"));

        for (WebElement input: allInputs) {
            try{
                input.clear();
            }catch(InvalidElementStateException e){}
        }

    }

    public void clearFieldById(String id){

        form.findElement(By.xpath("./descendant::input[@id=\""+id+"\"]")).clear();

    }
    //TODO - we should add required attribute to inputs
    public List<WebElement> getRequiredInputs(){
        try{
            return form.findElements(By.xpath("./descendant::input[@required]"));
        }catch (StaleElementReferenceException e){
            List<WebElement> emptyList = Collections.emptyList();
            return emptyList;
        }

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

    public boolean checkElementExistsById(String id){

        try{
             form.findElement(By.xpath("./descendant::*[@id=\""+id+"\"]"));
             return true;
        }catch(NoSuchElementException e){
            return false;
        }

    }

    public String getInputValue(String id){

         return form.findElement(By.xpath("./descendant::*[@id=\""+id+"\"]")).getAttribute("value");

    }

    public void setInputValue(String inputName, String value)
    {
        form.findElement(By.xpath("./descendant::input[@id=\""+inputName+"\"]")).sendKeys(value);
    }

    public void setSelectValue(String inputName, Integer index)
    {
        Select dropdown = new Select(form.findElement(By.xpath("./descendant::select[@id=\""+inputName+"\"]")));
        dropdown.selectByIndex(index);
    }

    public void setSelectValueByValue(String inputName, String value)
    {
        Select dropdown = new Select(form.findElement(By.xpath("./descendant::select[@id=\""+inputName+"\"]")));
        dropdown.selectByValue(value);
    }

    public void toggleCheckboxValue(String inputName)
    {
        form.findElement(By.xpath("./descendant::input[@id=\""+inputName+"\"]")).click();
    }

    public void toggleRadioButtonValue(String inputName)
    {
        form.findElement(By.xpath("./descendant::input[@id=\""+inputName+"\"]")).click();
    }

    public void setTextareaValue(String inputName, String value)
    {
        form.findElement(By.xpath("./descendant::textarea[@id=\""+inputName+"\"]")).sendKeys(value);
    }

    public void setDriver(WebDriver object){
        driver = object;
    }

    public WebDriver getDriver(){
        return driver;
    }

    public List<FormErrorMessage> getFormErrorMessagesList(){
        List<FormErrorMessage> errorsList = new ArrayList<FormErrorMessage>();
        try{
            for(WebElement error: form.findElements(By.xpath("//descendant::label[@class=\"error\"]"))){
                FormErrorMessage e = new FormErrorMessage();
                e.setMessage(error.getText());
            }
        }catch(NoSuchElementException e){}

        return errorsList;
    }

    public void uploadFile(String path)
            throws AWTException
    {
        setClipboard(path);
        form.findElement(By.xpath("./descendant::input[@type=\"file\"]")).click();

        Robot robot = new Robot();
        // Ctrl-V + Enter on Win
        robot.delay(3000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);

    }

    public static void setClipboard(String str) {
        StringSelection ss = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    }

    public WebElement getElementById(String id){
        return form.findElement(By.xpath("./descendant::*[@id=\""+id+"\"]"));
    }

}
