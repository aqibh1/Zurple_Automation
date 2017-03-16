package com.zurple.admin;

import com.zurple.Admin.MailgunmgrReportPage;
import com.zurple.Admin.PackagemgrPage;
import com.zurple.PageTest;

public class MailgunmgrReportPageTest
        extends PageTest
{
    private static MailgunmgrReportPage page;

    public MailgunmgrReportPage getPage(){
        if(page == null){
            page = new MailgunmgrReportPage();
            page.setUrl("https://my.dev.zurple.com/mailgunmgr/report");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
