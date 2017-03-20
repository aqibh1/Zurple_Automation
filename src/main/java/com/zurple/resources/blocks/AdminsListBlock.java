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
import resources.classes.Admin;
import resources.classes.Template;

public class AdminsListBlock
        extends resources.blocks.AbstractBlock
{


    public List<Admin> getAdminsList(){

        ArrayList<Admin> list = new ArrayList<Admin>();

        try{

            List<WebElement> allAdminsRows = block.findElements(By.xpath("./descendant::table[@id=\"leadlist\"]/tbody/tr"));

            for (WebElement row: allAdminsRows) {
                Admin admin = new Admin();
                admin.setId(Integer.parseInt(row.findElement(By.xpath("./td[1]")).getAttribute("admin")));

                DateFormat df = new SimpleDateFormat("mm/dd/yy hh:mma");
                try
                {
                    admin.setCreatedDate(df.parse(row.findElement(By.xpath("./td[4]")).getText().replace("at","")));
                }
                catch (ParseException e) {}

                if(row.findElement(By.xpath("./td[5]")).getText()=="Y"){
                    admin.setActive(true);
                }else{
                    admin.setActive(false);
                }

                list.add(admin);
            }
            return list;
        }catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}
        return list;
    }

}
