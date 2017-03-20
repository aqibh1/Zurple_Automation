package com.zurple.my.admin;

import com.zurple.my.Admin.ProcessEmailQueuePage;
import com.zurple.my.PageTest;

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
