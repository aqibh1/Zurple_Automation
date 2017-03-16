package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.ProcessEmailQueuePage;
import com.zurple.PageTest;

public class ProcessEmailQueuePageTest
        extends PageTest
{
    private static ProcessEmailQueuePage page;

    public ProcessEmailQueuePage getPage(){
        if(page == null){
            page = new ProcessEmailQueuePage();
            page.setUrl("https://my.dev.zurple.com/admin/processemailqueue");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
