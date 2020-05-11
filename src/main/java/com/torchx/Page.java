/**
 * 
 */
package com.torchx;

import resources.AbstractPage;
import resources.ConfigReader;

/**
 * @author adar
 *
 */
public class Page extends AbstractPage{

	protected String baseUrl = null;
	protected String getBaseUrl(){
		String project = System.getProperty("project");
		if (baseUrl == null){
			ConfigReader configReader = ConfigReader.load();
			if(project.equalsIgnoreCase("torchx")) {
				baseUrl = configReader.getPropertyByName("torchx_bo_base_url");
			}
		}
		return baseUrl;
	}

}
