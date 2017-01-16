package com.zurple;

import static org.junit.Assert.*;

/**
 * todo
 *
 * @author Vladimir
 */
public class SupportPageTest  extends PageTest
{
    private static SupportPage page;

    public SupportPage getPage(){
        if(page == null){
            page = new SupportPage();
            page.setDriver(getDriver());
        }
        return page;
    }
}
