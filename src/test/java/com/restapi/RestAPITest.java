/**
 * 
 */
package com.restapi;

import java.io.IOException;
import java.net.ProtocolException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

import resources.AbstractAPIRestTest;
import resources.AbstractPage;
import resources.AbstractPageTest;
import resources.EnvironmentFactory;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public abstract class RestAPITest extends AbstractPageTest {

	private String restApiBaseUrl = "";
	public abstract boolean validateMapResp(RestResponse httpCallResp) throws Exception;
	public String getBaseUrl() {
		EnvironmentFactory.configReader.load();
		String l_project = System.getProperty("project");
		if(l_project.equalsIgnoreCase("zurple")) {
			restApiBaseUrl = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url");
		}
		return restApiBaseUrl;
	}

	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	public String getValidationAction(JSONObject pDataObject, String pClassName) {
		String lValidationAction ="";
		JSONArray jArray = pDataObject.getJSONArray("validation");
		for(int i=0;i<jArray.length();i++) {
			JSONObject jObject = jArray.getJSONObject(i);
			if(jObject.getString("key").equalsIgnoreCase(pClassName)) {
				lValidationAction = jObject.getString("value");
				break;
			}
		}
		return lValidationAction;
	}
	
	public String getDataFileContentJsonArray(String pDataFile) throws IOException {
        String data = ""; 
        data = new String(Files.readAllBytes(Paths.get(pDataFile))); 
        return "["+data+"]"; 
    }
	
}
