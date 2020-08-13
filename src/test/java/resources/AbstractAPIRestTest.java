/**
 * 
 */
package resources;

/**
 * @author adar
 *
 */
public class AbstractAPIRestTest extends AbstractTest{

	private String restApiBaseUrl = "";
	
	public String getBaseUrl() {
		EnvironmentFactory.configReader.load();
		String l_project = System.getProperty("project");
		if(l_project.equalsIgnoreCase("zurple")) {
			restApiBaseUrl = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url");
		}
		return restApiBaseUrl;
	}
}
