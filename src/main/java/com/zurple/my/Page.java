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
    	String project = System.getProperty("project");
    	if (baseUrl == null){
    		ConfigReader configReader = ConfigReader.load();
    		if(project.equalsIgnoreCase("z57")) {
    			baseUrl = configReader.getPropertyByName("z57_pp_base_url");
    		}else {
    			baseUrl = configReader.getPropertyByName("zurple_bo_base_url");
    		}
    	}
    	return baseUrl;
    }
}
