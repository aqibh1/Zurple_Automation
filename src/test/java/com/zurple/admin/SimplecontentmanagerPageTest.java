package com.zurple.admin;

import com.zurple.Admin.SimplecontentmanagerPage;
import com.zurple.Admin.SimpletmplmgrPage;
import com.zurple.PageTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SimplecontentmanagerPageTest
        extends PageTest
{
    private static SimplecontentmanagerPage page;

    public SimplecontentmanagerPage getPage(){
        if(page == null){
            page = new SimplecontentmanagerPage();
            page.setUrl("https://my.dev.zurple.com/simplecontentmanager/edit/?key=zurple_updates_v2");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
