package resources;
import java.util.List;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;
/**
 * {@link IAlterSuiteListener} requires that you use TestNG 6.9.10 or higher.
 * This class picks threadCount value from Command Line Arguments and overrides 
 * xml file thread count value.
 */
public class ThreadCountChanger implements IAlterSuiteListener{

	@Override
	public void alter(List<XmlSuite> suites) {
		 System.err.println("**ThreadCountChanger class is invoked**");
	        int count = Integer.parseInt(System.getProperty("threads", "-1"));
	        if (count <= 0) {
	            return;
	        }
	        for (XmlSuite suite : suites) {
	            suite.setThreadCount(count);
	        }
	    }
}
