package com.zurple.my;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.zurple.my.resources.forms.AgentEditForm;

public class AgentPage
        extends Page
{
    private AgentEditForm agentEditForm;
    private Integer agentId;

    public AgentPage(){
        url = "/agent/edit/admin_id/";
    }

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

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getFullUrl()
    {
        return getBaseUrl() + url + getAgentId();
    }
}
