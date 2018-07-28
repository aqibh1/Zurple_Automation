package com.zurple.my;

public class LeadEditPage
        extends Page
{
    private Integer leadId;

    public LeadEditPage(){
        url = "/lead/edit/user_id/";
    }

    public Integer getLeadId() {
        return leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    public String getFullUrl()
    {
        return getBaseUrl() + url + getLeadId();
    }
}
