package com.zurple.my.admin;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.AliasCityManagerEditPage;

public class AliasCityManagerEditPageTest
        extends PageTest
{
    private AliasCityManagerEditPage page;

    public AliasCityManagerEditPage getPage(){
        if(page == null){
            page = new AliasCityManagerEditPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
