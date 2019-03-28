/**
 * 
 */
package com.z57.propertypulse;

import resources.AbstractPage;
import resources.ConfigReader;

/**
 * @author adar
 *
 */
public class Page extends AbstractPage{
	protected String baseUrl = null;
	protected String getBaseUrl(){
		if (baseUrl == null){
			ConfigReader configReader = ConfigReader.load();
			baseUrl = configReader.getPropertyByName("z57_pp_base_url");
		}
		return baseUrl;
	}
}
