package com.zurple.my;

/**
 * todo
 *
 * @author Vladimir
 */
public class SupportPageTest  extends PageTest
{
    private SupportPage page;

    public SupportPage getPage(){
        if(page == null){
            page = new SupportPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
