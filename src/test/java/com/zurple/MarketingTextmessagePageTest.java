package com.zurple;

/**
 * todo
 *
 * @author Vladimir
 */
public class MarketingTextmessagePageTest  extends PageTest
{
    private static MarketingTextmessagePage page;

    public MarketingTextmessagePage getPage(){
        if(page == null){
            page = new MarketingTextmessagePage();
            page.setDriver(getDriver());
        }
        return page;
    }
}