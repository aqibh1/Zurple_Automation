package com.zurple.my.resources.forms;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
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

    public LeadStatus getStatus(){
        LeadStatus status = new LeadStatus();

        try{
            WebElement st = form.findElements(By.xpath("//descendant::select[@id=\"lead_status\"]/option[@selected]")).get(0);

            status.setValue(st.getAttribute("value"));
            status.setDescription(st.getText());

        }
        catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}

        return status;
    }

    public WebElement getStatusAutomationIcon(){
        WebElement icon = null;

        try{
            icon = form.findElements(By.xpath("//div[@id=\"automation_marker\"]")).get(0);

        }
        catch (StaleElementReferenceException e) {}
        catch( NoSuchElementException e ) {}

        return icon;
    }


}
