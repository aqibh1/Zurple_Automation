package com.zurple.my.resources.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.classes.Helper;
import resources.classes.Property;

public class CraigsListBlock
        extends resources.blocks.AbstractBlock
{

    public WebElement getPropertiesList(){
        return block.findElement(By.xpath("./descendant::table[@id=\"propertiesGrid\"]"));
    }

    public List<Property> getCraigsList(){

        ArrayList<Property> list = new ArrayList<Property>();

        try{

            Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(Helper.generateXPATH(getPropertiesList(), "")+"/tbody/tr")));

            List<WebElement> allPropertiesRows = block.findElements(By.xpath(Helper.generateXPATH(getPropertiesList(), "")+"/tbody/tr"));

            for (WebElement row: allPropertiesRows) {

                try{
                    Property property = new Property();
                    Pattern p = Pattern.compile("(\\d+)$");
                    Matcher m = p.matcher(row.findElement(By.xpath("./td[1]/a")).getAttribute("href"));

                    if (m.find()) {
                        property.setId(Integer.parseInt(m.group()));
                    }

                    list.add(property);
                }catch(NoSuchElementException e){}

            }
            return list;
        }catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}
        return list;
    }

}
