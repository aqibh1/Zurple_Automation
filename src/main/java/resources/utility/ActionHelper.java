package resources.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionHelper {
	protected static WebDriverWait wait;
	private static long GLOBAL_WAIT_COUNT=10;
	
	public static boolean Type(WebDriver pWebDriver,WebElement pInputField, String pStringToType) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			try {
				if(wait.until(ExpectedConditions.visibilityOf(pInputField))!=null) {
					pInputField.sendKeys(pStringToType);
					isSuccessfull=true;
				}
				
			}catch(Exception ex) {
				AutomationLogger.info("Unable to type in input field "+pInputField.getAttribute("xpath"));
				AutomationLogger.info("String to type : "+pStringToType);
			}
			return isSuccessfull;
		}
		
	   public static boolean Click(WebDriver pWebDriver,WebElement pElementToBeClicked) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			try {
				if(wait.until(ExpectedConditions.visibilityOf(pElementToBeClicked))!=null) {
					pElementToBeClicked.click();
					isSuccessfull=true;
				}
				
			}catch(Exception ex) {
				AutomationLogger.info("Unable to Click on "+pElementToBeClicked.getAttribute("xpath"));
			}
			return isSuccessfull;
		}
	   
	   public static void MouseHoverOnElement(WebDriver pWebDriver, WebElement pElementToBeHoveredOn) {
			Actions action = new Actions(pWebDriver);
			action.moveToElement(pElementToBeHoveredOn).build().perform();
			AutomationLogger.info("Mouse hover is successfull");
		}
	   
	   public static boolean waitForElementToBeDisappeared(WebDriver pWebDriver,WebElement pElementToBeDisappeared) {			
		   wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
		   boolean isElementVisible = false;
		   try {
			   isElementVisible=wait.until(ExpectedConditions.invisibilityOf(pElementToBeDisappeared));
		   }catch(Exception ex) {
			   AutomationLogger.error("Element is not visible.  Wait max limit is "+GLOBAL_WAIT_COUNT+" seconds");
		   }
		   return isElementVisible;

	   }
	   
	   public static boolean isElementVisible(WebDriver pWebDriver,WebElement pElement) {
		   wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
		   boolean isElementVisible = false;
		   try {
			   isElementVisible=wait.until(ExpectedConditions.visibilityOf(pElement))!=null;
		   }catch(Exception ex) {
			   AutomationLogger.error("Element is not visible.  Wait max limit is "+GLOBAL_WAIT_COUNT+" seconds");
		   }
		   return isElementVisible;
	   }
	    
	    

}
