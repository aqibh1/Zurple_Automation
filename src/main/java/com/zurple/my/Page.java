package com.zurple.my;

import resources.AbstractPage;
import resources.ConfigReader;

/**
 * todo
 *
 * @author Vladimir
 */
public abstract class Page
        extends AbstractPage
{
    protected String baseUrl = null;

    protected String getBaseUrl(){
        if (baseUrl == null){
            ConfigReader configReader = ConfigReader.load();
            baseUrl = configReader.getPropertyByName("bo_base_url");
        }
        return baseUrl;
    }
}
