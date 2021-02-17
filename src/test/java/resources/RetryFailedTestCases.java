/**
 * 
 */
package resources;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class RetryFailedTestCases implements IRetryAnalyzer{

	private int RETRY_COUNT = 0;
	private int MAX_RETRY_COUNT = 2;
	
	@Override
	public boolean retry(ITestResult result) {
		if (RETRY_COUNT < MAX_RETRY_COUNT) {
			AutomationLogger.info("Retrying "+ result.getName() +"again and the count is" + (RETRY_COUNT+1));
            RETRY_COUNT++;
            return true;
        }
        return false;
	}

}
