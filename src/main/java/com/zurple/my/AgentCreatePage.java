package com.zurple.my;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.zurple.my.resources.forms.AgentCreateForm;

public class AgentCreatePage
        extends Page
{
    AgentCreateForm agentCreateForm;

    public AgentCreatePage(){
        url = "/agent/create";
    }

    public boolean checkAgentEditForm(){
        try{
            getAgentCreateForm();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public AgentCreateForm getAgentCreateForm(){
        agentCreateForm = new AgentCreateForm();
        agentCreateForm.setDriver(driver);
        agentCreateForm.setForm(driver.findElement(By.xpath("//*[@id=\"add-agent\"]")));
        return agentCreateForm;
    }
}
