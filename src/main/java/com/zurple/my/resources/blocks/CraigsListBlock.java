package com.zurple.my.resources.blocks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import resources.classes.Property;
import resources.classes.Template;

public class CraigsListBlock
        extends resources.blocks.AbstractBlock
{


    public List<Property> getCraigsList(){

        ArrayList<Property> list = new ArrayList<Property>();

        try{

            List<WebElement> allPropertiesRows = block.findElements(By.xpath("./descendant::table[@id=\"propertiesGrid\"]/tbody/tr"));

            for (WebElement row: allPropertiesRows) {
                Property property = new Property();
                property.setId(Integer.parseInt(row.findElement(By.xpath("./td[1]")).getText()));

                list.add(property);
            }
            return list;
        }catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}
        return list;
    }

}
