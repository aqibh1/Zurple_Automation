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

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.classes.Helper;
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

            Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("./descendant::table[@id=\"DataTables_Table_0\"]/tbody/tr")));

            List<WebElement> allLeadRows = block.findElements(By.xpath("./descendant::table[@id=\"DataTables_Table_0\"]/tbody/tr"));

            for (WebElement row: allLeadRows) {
                Lead lead = new Lead();
                lead.setLeadLink(this.getCellByTitle(row,"Name").findElement(By.xpath("./a")).getAttribute("href"));
                lead.setName(this.getCellByTitle(row,"Name").findElement(By.xpath("./a")).getText());

                lead.setEmail(this.getCellByTitle(row,"Email").findElement(By.xpath("./a")).getText());
                lead.setSearchLocation(this.getCellByTitle(row,"Search Location").getText());
                DateFormat df = new SimpleDateFormat("mm/dd/yy hh:mma");
                try
                {
                    lead.setLastVisit(df.parse(this.getCellByTitle(row,"Last Visit").getText().replace("at","")));
                    lead.setDateCreated(df.parse(this.getCellByTitle(row,"Date Created").getText().replace("at","")));
                }
                catch (ParseException e) {}
                lead.setPriorityRank(this.getCellByTitle(row,"Priority Rank").getText());
                lead.setFlagsList(parseHotAlertsFlags(this.getCellByTitle(row,"Hot Behaviors")));
                list.add(lead);
            }
            return list;
        }catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}
        return list;
    }

    public WebElement getCellByTitle(WebElement row, String title){

        List<WebElement> allColumnTitles = block.findElements(By.xpath("./descendant::table[@id=\"DataTables_Table_0\"]/thead/tr/th"));
        WebElement targetColumn = block.findElement(By.xpath("./descendant::table[@id=\"DataTables_Table_0\"]/thead/tr/th[contains(text(),\""+title+"\")]"));
        Integer position = allColumnTitles.indexOf(targetColumn)+1;

        return row.findElement(By.xpath("./td["+position+"]"));
    }

    public WebElement getLeadRowByUserId(Integer user_id){
        return block.findElement(By.xpath("./descendant::table[@id=\"DataTables_Table_0\"]/tbody/tr/td/input[@value=\""+user_id+"\"]/ancestor::tr"));
    }

    public WebElement getUserStatusAutomationIcon(Integer user_id){
        WebElement icon = null;

        try{
            WebElement row = this.getLeadRowByUserId(user_id);
            WebElement cell = this.getCellByTitle(row,"Last Modified");
            icon = cell.findElement(By.xpath("./div[contains(@class,\"z-lead-automation-marker\")]"));

        }
        catch (StaleElementReferenceException e) {}
        catch( NoSuchElementException e ) {}

        return icon;
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
