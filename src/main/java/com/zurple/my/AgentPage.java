package com.zurple.my;

import com.zurple.my.resources.forms.AgentEditForm;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class AgentPage
        extends Page
{
    private AgentEditForm agentEditForm;

    public boolean checkAgentEditFormExists(){
        try{
            getAgentEditForm();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public AgentEditForm getAgentEditForm(){
        agentEditForm = new AgentEditForm();
        agentEditForm.setDriver(driver);
        agentEditForm.setForm(driver.findElement(By.xpath("//*[@id=\"edit-admin-form\"]")));
        return agentEditForm;
    }
}
