package com.zurple.my.admin;

import com.zurple.my.Admin.AliasCityManagerEditPage;
import com.zurple.my.PageTest;

public class AliasCityManagerEditPageTest
        extends PageTest
{
    private static AliasCityManagerEditPage page;

    public AliasCityManagerEditPage getPage(){
        if(page == null){
            page = new AliasCityManagerEditPage();
            page.setUrl("https://my.dev.zurple.com/aliascitymgr/edit");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
