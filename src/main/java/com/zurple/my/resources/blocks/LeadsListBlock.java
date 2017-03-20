package com.zurple.my.resources.blocks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.classes.Lead;

public class LeadsListBlock
        extends resources.blocks.AbstractBlock
{

    private Integer numberOfPages;
    private Integer currentPageNumber;
    private Integer totalLeadsNumber;

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

    public Integer getNumberOfPages(){
        if(numberOfPages==null){
            numberOfPages=Integer.parseInt(block.findElement(By.xpath("./descendant::ul[@class=\"pagination\"]/li[position()=last()-1]")).getText());
        }
        return numberOfPages;
    }

    public Integer getCurrentPageNumber(){
        if(currentPageNumber==null){
            currentPageNumber=Integer.parseInt(block.findElement(By.xpath("./descendant::ul[@class=\"pagination\"]/li[@class=\"paginate_button active\"]")).getText());
        }
        return currentPageNumber;
    }

    public Integer getTotalLeadsNumber(){
        if(totalLeadsNumber==null){
            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(block.findElement(By.xpath("./descendant::div[@id=\"DataTables_Table_0_info\"]")).getText());
            ArrayList<String> l = new ArrayList<String>();
            while (m.find()) {
                l.add(m.group());
            }
            totalLeadsNumber=Integer.parseInt(l.get(2));
        }
        return totalLeadsNumber;
    }
}
