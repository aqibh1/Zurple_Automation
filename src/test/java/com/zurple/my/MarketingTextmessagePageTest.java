package com.zurple.my;

/**
 * todo
 *
 * @author Vladimir
 */
public class MarketingTextmessagePageTest  extends PageTest
{
    private MarketingTextmessagePage page;

    public MarketingTextmessagePage getPage(){
        if(page == null){
            page = new MarketingTextmessagePage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
