package com.zurple.my.resources.blocks;

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

public class LeadsDetailsBlock
        extends resources.blocks.AbstractBlock
{

    private String leadSource;

    public List<Lead> getLeadList(){

        ArrayList<Lead> list = new ArrayList<Lead>();

        try{

            List<WebElement> allLeadRows = block.findElements(By.xpath("./descendant::table[@id=\"DataTables_Table_0\"]/tbody/tr"));

            for (WebElement row: allLeadRows) {
                Lead lead = new Lead();
                lead.setLeadLink(row.findElement(By.xpath("./td[3]/a")).getAttribute("href"));
                lead.setName(row.findElement(By.xpath("./td[3]")).getText());

                lead.setEmail(row.findElement(By.xpath("./td[4]/a")).getText());
                lead.setSearchLocation(row.findElement(By.xpath("./td[5]")).getText());
                DateFormat df = new SimpleDateFormat("mm/dd/yy hh:mma");
                try
                {
                    lead.setLastVisit(df.parse(row.findElement(By.xpath("./td[7]")).getText().replace("at","")));
                    lead.setDateCreated(df.parse(row.findElement(By.xpath("./td[8]")).getText().replace("at","")));
                }
                catch (ParseException e) {}
                lead.setAgent(row.findElement(By.xpath("./td[9]")).getText());
                lead.setPriorityRank(row.findElement(By.xpath("./td[10]/span")).getText());
                lead.setFlagsList(parseHotAlertsFlags(row.findElement(By.xpath("./td[11]"))));
                list.add(lead);
            }
            return list;
        }catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}
        return list;
    }

    public String getLeadSource()
    {
        return block.findElement(By.xpath("./descendant::div[2]/div[3]/div[2]/span[2]")).getText();
    }
}
