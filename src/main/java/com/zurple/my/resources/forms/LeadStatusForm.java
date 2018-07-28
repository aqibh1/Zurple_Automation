package com.zurple.my.resources.forms;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import resources.classes.LeadSearchCriteria;
import resources.classes.LeadStatus;

public class LeadStatusForm
        extends resources.forms.LoginForm
{

    public List<LeadStatus> getStatusVariants(Integer filter_id){

        ArrayList<LeadStatus> list = new ArrayList<LeadStatus>();

        try{
            List<WebElement> allStatusRows = form.findElements(By.xpath("//descendant::select[@id=\"lead_status\"]/option"));

            for (WebElement row: allStatusRows) {
                LeadStatus l = new LeadStatus();
                l.setValue(row.getAttribute("value"));
                l.setDescription(row.getText());
                list.add(l);
            }

        }
        catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}

        return list;
    }
    
}