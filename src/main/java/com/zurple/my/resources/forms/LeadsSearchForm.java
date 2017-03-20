package com.zurple.my.resources.forms;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import resources.classes.LeadSearchCriteria;

public class LeadsSearchForm
        extends resources.forms.LoginForm
{

    public List<LeadSearchCriteria> getSearchCriteriaVariants(Integer filter_id){

        ArrayList<LeadSearchCriteria> list = new ArrayList<LeadSearchCriteria>();

        try{
            List<WebElement> allCriteriaRows = form.findElements(By.xpath("//descendant::select[@id=\"location-parent-"+filter_id+"\"]/option"));

            for (WebElement row: allCriteriaRows) {
                LeadSearchCriteria l = new LeadSearchCriteria();
                l.setValue(row.getAttribute("value"));
                l.setDescription(row.getText());
                list.add(l);
            }

        }
        catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}

        return list;
    }

    /**
     * Function toggles second search criteria
     */
    public void toggleSecondFilter(){
        form.findElement(By.xpath("./descendant::*[@id=\"leads-search-filter-plus\"]")).click();
    }
}
