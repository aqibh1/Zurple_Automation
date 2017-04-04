package com.zurple.my;

import com.zurple.my.resources.alerts.AgentCreateWarning;
import com.zurple.my.resources.forms.AgentCreateForm;
import com.zurple.my.resources.forms.DistributionRulesForm;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

public class AgentCreatePage
        extends Page
{
    AgentCreateForm agentCreateForm;


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
