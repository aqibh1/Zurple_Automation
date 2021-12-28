//package resources.utility;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
//import org.testng.Reporter;
//
//public class AutomationLogger2 {
//	
//	private static Logger AutomationLogger = Logger.getLogger(AutomationLogger2.class.getName());
//	 // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
//	 public static void setLog4jPopFile() {
//		 PropertyConfigurator.configure("log4j.properties");
//	 }
//	 public static void startTestCase(String pTestCaseName){
//	 
//		 AutomationLogger.info("****************************************************************************************");
//	 
//		 AutomationLogger.info("****************************************************************************************");
//	 
//		 AutomationLogger.info("$$$$$$$$$$$$$$$$$$$$$                 "+pTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
//	 
//		 AutomationLogger.info("****************************************************************************************");
//	 
//		 AutomationLogger.info("****************************************************************************************");
//	 
//	 }
//	 
//	 //This is to print log for the ending of the test case
//	 
//	 public static void endTestCase(){
//	 
//		 AutomationLogger.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
//	 
//		 AutomationLogger.info("X");
//	 
//		 AutomationLogger.info("X");
//	 
//		 AutomationLogger.info("X");
//	 
//		 AutomationLogger.info("X");
//	 
//	 }
//	 
//	 // Need to create these methods, so that they can be called  
//	 
//	 public static void info(String message) {
//		int line_numeber = Thread.currentThread().getStackTrace()[2].getLineNumber();
//		 String l_class_name = Thread.currentThread().getStackTrace()[2].getFileName();
//
//		 AutomationLogger.info("Thread ID = "+Thread.currentThread().getId()+" "+l_class_name+":"+line_numeber+"-----=====" +message+ "=====-----");
//		 Reporter.log("-----=====" +message+ "=====-----");
//	 
//	 }
//	 
//	 public static void warn(String message) {
//	 
//		 AutomationLogger.warn(message);
//	 
//	 }
//	 
//	 public static void error(String message) {
//	 
//		 AutomationLogger.error("Thread ID = "+Thread.currentThread().getId()+" -----ERROR=====" +message+ "=====ERROR-----");
//		 Reporter.log("-----ERROR=====" +message+ "=====ERROR-----");
//	 }
//	 
//	 public static void fatal(String message) {
//	 
//		 AutomationLogger.fatal("Thread ID = "+Thread.currentThread().getId()+" -----FATAL=====" +message+ "=====FATAL-----");
//	 
//	 }
//	 
//	 public static void debug(String message) {
//		
//		 AutomationLogger.debug(message);
//	 
//	 }
//	 public static void testStep(String message) {
//		 AutomationLogger.info("Thread ID = "+Thread.currentThread().getId()+" -----***** " +message+ " *****----");
//		 Reporter.log("-----=====" +message+ "=====-----");
//	 
//	 }
//	 public static void onTestPass(String pMessage) {
//		 AutomationLogger.error("Thread ID = "+Thread.currentThread().getId()+" -----PASS=====" +pMessage+ "=====PASS-----");
//		 Reporter.log("-----PASS=====" +pMessage+ "=====PASS-----");
//	 }
//	 
//	 public static void onTestFail(String pMessage) {
//		 AutomationLogger.error("Thread ID = "+Thread.currentThread().getId()+" -----FAIL=====" +pMessage+ "=====FAIL-----");
//		 Reporter.log("-----FAIL=====" +pMessage+ "=====FAIL-----");
//	 }
//	 
//
//}
