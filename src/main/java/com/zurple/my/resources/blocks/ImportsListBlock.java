package com.zurple.my.resources.blocks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import resources.classes.Import;
import resources.classes.Property;

public class ImportsListBlock
        extends resources.blocks.AbstractBlock
{

    public List<Import> getImportsList(){

        ArrayList<Import> list = new ArrayList<Import>();

        try{

            List<WebElement> allImportsRows = block.findElements(By.xpath("./descendant::tbody/tr"));

            for (WebElement row: allImportsRows) {

                try{
                    Import i = new Import();

                    i.setFileName(row.findElement(By.xpath("./td[2]")).getText());
                    System.out.println(row.findElement(By.xpath("./td[2]")).getText());
                    System.out.println(row.findElement(By.xpath("./td[3]")).getText());
                    try{
                        i.setDataRows(Integer.parseInt(row.findElement(By.xpath("./td[3]")).getText()));
                    }catch(NumberFormatException e){
                        i.setDataRows(0);
                    }

                    try{
                        i.setNewLeads(Integer.parseInt(row.findElement(By.xpath("./td[4]")).getText()));
                    }catch(NumberFormatException e){
                        i.setNewLeads(0);
                    }

                    try{
                        i.setIgnoredLeads(Integer.parseInt(row.findElement(By.xpath("./td[5]")).getText()));
                    }catch(NumberFormatException e){
                        i.setIgnoredLeads(0);
                    }

                    try{
                        i.setIgnoredLeads(Integer.parseInt(row.findElement(By.xpath("./td[5]")).getText()));
                    }catch(NumberFormatException e){
                        i.setIgnoredLeads(0);
                    }

                    i.setImporter(row.findElement(By.xpath("./td[6]")).getText());
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try
                    {
                        i.setDate(df.parse(row.findElement(By.xpath("./td[7]")).getText()));
                    }
                    catch (ParseException e) {}
                    catch(NoSuchElementException e){}

                    list.add(i);
                }catch(NoSuchElementException e){}

            }
            return list;
        }catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}
        return list;
    }

}
