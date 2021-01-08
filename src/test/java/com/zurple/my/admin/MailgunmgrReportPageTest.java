package com.zurple.my.admin;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.MailgunmgrReportPage;

public class MailgunmgrReportPageTest
        extends PageTest
{
    private MailgunmgrReportPage page;

    public MailgunmgrReportPage getPage(){
        if(page == null){
            page = new MailgunmgrReportPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
