package resources.blocks;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.interfaces.UsesDriver;

public abstract class AbstractBlock implements UsesDriver
{
    protected WebElement block;
    protected WebDriver driver;

    public void setBlock(WebElement object){
        block = object;
    }

    public void setDriver(WebDriver object){
        driver = object;
    }

    public WebDriver getDriver(){
        return driver;
    }



}
