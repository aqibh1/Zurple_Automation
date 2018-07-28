package com.zurple.my.Admin;

import com.zurple.my.Page;

public class SimpletmplmgrEditPage
        extends Page
{

    private Integer templateId;

    public SimpletmplmgrEditPage(){
        url = "/simpletemplmgr/edit/id/";
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getFullUrl()
    {
        return getBaseUrl() + url + getTemplateId();
    }
}
