package resources.utility;
import org.apache.log4j.Logger;

public class AutomationLogger {
	
	private static Logger AutomationLogger = Logger.getLogger(AutomationLogger.class.getName());
	 // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
	 
	 public static void startTestCase(String pTestCaseName){
	 
		 AutomationLogger.info("****************************************************************************************");
	 
		 AutomationLogger.info("****************************************************************************************");
	 
		 AutomationLogger.info("$$$$$$$$$$$$$$$$$$$$$                 "+pTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
	 
		 AutomationLogger.info("****************************************************************************************");
	 
		 AutomationLogger.info("****************************************************************************************");
	 
	 }
	 
	 //This is to print log for the ending of the test case
	 
	 public static void endTestCase(String sTestCaseName){
	 
		 AutomationLogger.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
	 
		 AutomationLogger.info("X");
	 
		 AutomationLogger.info("X");
	 
		 AutomationLogger.info("X");
	 
		 AutomationLogger.info("X");
	 
	 }
	 
	 // Need to create these methods, so that they can be called  
	 
	 public static void info(String message) {
	 
		 AutomationLogger.info("***************" +message+ "***************");
	 
	 }
	 
	 public static void warn(String message) {
	 
		 AutomationLogger.warn(message);
	 
	 }
	 
	 public static void error(String message) {
	 
		 AutomationLogger.error(message);
	 
	 }
	 
	 public static void fatal(String message) {
	 
		 AutomationLogger.fatal(message);
	 
	 }
	 
	 public static void debug(String message) {
	 
		 AutomationLogger.debug(message);
	 
	 }
	 

}
