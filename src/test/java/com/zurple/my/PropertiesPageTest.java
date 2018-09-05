package com.zurple.my;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class PropertiesPageTest
        extends PageTest
{

    private PropertiesPage page;

    public PropertiesPage getPage(){
        if(page == null){
            page = new PropertiesPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
