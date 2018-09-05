package com.zurple.my;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class ExportLeadsPageTest
        extends PageTest
{

    private ExportLeadsPage page;

    public ExportLeadsPage getPage(){
        if(page == null){
            page = new ExportLeadsPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
