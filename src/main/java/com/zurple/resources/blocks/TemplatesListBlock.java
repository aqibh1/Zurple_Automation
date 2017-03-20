package com.zurple.resources.blocks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import resources.classes.Lead;
import resources.classes.Template;

public class TemplatesListBlock
        extends resources.blocks.AbstractBlock
{


    public List<Template> getTemlatesList(){

        ArrayList<Template> list = new ArrayList<Template>();

        try{

            List<WebElement> allTemplateRows = block.findElements(By.xpath("./descendant::table/tbody/tr"));

            for (WebElement row: allTemplateRows) {
                Template template = new Template();
                template.setId(Integer.parseInt(row.findElement(By.xpath("./td[1]")).getText()));
                template.setDescription(row.findElement(By.xpath("./td[2]/a")).getText());
                template.setVariationOf(row.findElement(By.xpath("./td[3]")).getText());
                DateFormat df = new SimpleDateFormat("mm/dd/yy hh:mma");
                try
                {
                    template.setDateCreated(df.parse(row.findElement(By.xpath("./td[4]")).getText()));
                }
                catch (ParseException e) {}
                template.setForPackages(row.findElement(By.xpath("./td[5]")).getText());
                template.setSequence(Integer.parseInt(row.findElement(By.xpath("./td[6]/input")).getAttribute("value")));
                if(row.findElement(By.xpath("./td[7]")).getText()=="No"){
                    template.setOverride(false);
                }else{
                    template.setOverride(true);
                }
                template.setActive(row.findElement(By.xpath("./td[8]")).getText());

                list.add(template);
            }
            return list;
        }catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}
        return list;
    }

}
