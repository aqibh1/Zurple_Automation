package resources.utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ActionHelper {
	protected static WebDriverWait wait;
	private static long GLOBAL_WAIT_COUNT=10;
	
	public static boolean Type(WebDriver pWebDriver,WebElement pInputField, String pStringToType) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			try {
				AutomationLogger.info("Waiting for the visibility of element ->"+pInputField);
				if(wait.until(ExpectedConditions.visibilityOf(pInputField))!=null) {
					pInputField.sendKeys(pStringToType);
					AutomationLogger.info("String typed ->"+pStringToType);
					isSuccessfull=true;
				}
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to type in input field "+pInputField.getAttribute("xpath"));
				AutomationLogger.error("String to type : "+pStringToType);
				AutomationLogger.error(ex.toString());
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
				AutomationLogger.error("Unable to Click on "+pElementToBeClicked.getAttribute("xpath"));
				AutomationLogger.error(ex.toString());
			}
			return isSuccessfull;
		}
	   
	   public static boolean MouseHoverOnElement(WebDriver pWebDriver, WebElement pElementToBeHoveredOn) {
		   boolean status = false;
		   if(isElementVisible(pWebDriver, pElementToBeHoveredOn)) {
			   Actions action = new Actions(pWebDriver);
			   action.moveToElement(pElementToBeHoveredOn).build().perform();
			   AutomationLogger.info("Mouse hover is successfull");
		   }else {
			   AutomationLogger.error("Mouse hover is unsuccessful over element ->"+pElementToBeHoveredOn.getAttribute("xpath"));
		   }
		   return status;
		}
	   
	   public static boolean waitForElementToBeDisappeared(WebDriver pWebDriver,WebElement pElementToBeDisappeared) {			
		   wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
		   boolean isElementVisible = false;
		   try {
			   isElementVisible=wait.until(ExpectedConditions.invisibilityOf(pElementToBeDisappeared));
		   }catch(Exception ex) {
			   AutomationLogger.error("Element did not disappear.  Wait max limit is "+GLOBAL_WAIT_COUNT+" seconds");
			   AutomationLogger.error(ex.toString());
		   }
		   return isElementVisible;

	   }
	   
	   public static boolean isElementVisible(WebDriver pWebDriver,WebElement pElement) {
		   wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
		   boolean isElementVisible = false;
		   try {
			   AutomationLogger.info("Waiting for the visibility of element ->"+pElement);
			   isElementVisible=wait.until(ExpectedConditions.visibilityOf(pElement))!=null;
		   }catch(Exception ex) {
			   AutomationLogger.error("Element is not visible.  -> "+pElement);
			   AutomationLogger.error("Wait max limit is "+GLOBAL_WAIT_COUNT+" seconds");
			   AutomationLogger.error(ex.toString());
		   }
		   return isElementVisible;
	   }
	   
	   public static boolean waitForElementToBeVisible(WebDriver pWebDriver,WebElement pElement,long pWaitInSecnds) {
		   wait=new WebDriverWait(pWebDriver, pWaitInSecnds);
		   boolean isElementVisible = false;
		   try {
			   isElementVisible=wait.until(ExpectedConditions.visibilityOf(pElement))!=null;
		   }catch(Exception ex) {
			   AutomationLogger.error("Element is not visible.  -> "+pElement);
			   AutomationLogger.error("Wait max limit is "+GLOBAL_WAIT_COUNT+" seconds");
			   AutomationLogger.error(ex.toString());
		   }
		   return isElementVisible;
	   }
	   
	   public static String getText(WebDriver pWebDriver,WebElement pElement) {
		   String ltext="";
		   if(isElementVisible(pWebDriver, pElement)) {
			   ltext=pElement.getText();
		   }
		   return ltext;
	   }
	   
	   public static boolean clickAndSelect(WebDriver pWebDriver, WebElement pDropdown,WebElement pElementToSelect) {
		   boolean result=false;
		   if(waitForElementToBeVisible(pWebDriver,pDropdown,GLOBAL_WAIT_COUNT)) {
			   Click(pWebDriver, pDropdown);
			   if(waitForElementToBeVisible(pWebDriver, pElementToSelect, 5)) {
				   result= Click(pWebDriver, pElementToSelect);
			   }
		   }
		   return result;
	   }
	    
	   /**
	 * @param pWebDriver
	 * @param pInputField
	 * @param pStringToType
	 * @return boolean
	 * Clear the field and type the string
	 */
	public static boolean ClearAndType(WebDriver pWebDriver,WebElement pInputField, String pStringToType) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			try {
				AutomationLogger.info("Waiting for the visibility of element ->"+pInputField);
				if(wait.until(ExpectedConditions.visibilityOf(pInputField))!=null) {
					pInputField.clear();
					pInputField.sendKeys(pStringToType);
					AutomationLogger.info("String typed ->"+pStringToType);
					isSuccessfull=true;
				}
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to type in input field "+pInputField.getAttribute("xpath"));
				AutomationLogger.error("String to type : "+pStringToType);
				AutomationLogger.error(ex.toString());
			}
			return isSuccessfull;
		}
	
	public static boolean waitForElementToBeClickAble(WebDriver pWebDriver,WebElement pElement) {
		boolean isSuccessful =false;
		wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
		try {
			AutomationLogger.info("Waiting for the element to be clickable"+pElement);
			if(wait.until(ExpectedConditions.elementToBeClickable(pElement))!=null) {
				isSuccessful=true;
			}
			
		}catch(Exception ex) {
			AutomationLogger.error("Element is not clickable -> "+pElement);
			AutomationLogger.error(ex.toString());
		}
		return isSuccessful;
	}
	
	public static List<WebElement> getListOfElementByXpath(WebDriver pWebDriver,String pElementXpath){
		List<WebElement> lList_of_Elements = null;
		try {
			lList_of_Elements = pWebDriver.findElements(By.xpath(pElementXpath));
		}catch(Exception ex) {
			AutomationLogger.error("Element list not found -> "+pElementXpath);
			AutomationLogger.error(ex.toString());
		}
		return lList_of_Elements;
		
	}
	   
	   public static void RefreshPage(WebDriver pWebDriver) {
		   AutomationLogger.info("Refreshing the page");
		   pWebDriver.navigate().to(pWebDriver.getCurrentUrl()); 
	   }
	   
	   public static void waitForTime(WebDriver pWebDriver,long pWaitInSecnds) throws InterruptedException {
		   wait=new WebDriverWait(pWebDriver, pWaitInSecnds);
		   wait.wait(pWaitInSecnds);
		   
	   }
	   
	   public static boolean Type(WebDriver pWebDriver,WebElement pInputField, Keys pStringToType) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			try {
				AutomationLogger.info("Waiting for the visibility of element ->"+pInputField);
				if(wait.until(ExpectedConditions.visibilityOf(pInputField))!=null) {
					pInputField.sendKeys(pStringToType);
					AutomationLogger.info("String typed ->"+pStringToType);
					isSuccessfull=true;
				}
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to type in input field "+pInputField.getAttribute("xpath"));
				AutomationLogger.error("String to type : "+pStringToType);
				AutomationLogger.error(ex.toString());
			}
			return isSuccessfull;
		}
	   public static void ScrollToElement(WebDriver pWebDriver,WebElement pScrollToElement) {
		   try {
			   JavascriptExecutor js = (JavascriptExecutor) pWebDriver;
			   AutomationLogger.info("Scrolling to element ->"+pScrollToElement);		
			   js.executeScript("arguments[0].scrollIntoView();", pScrollToElement);
		   }catch(Exception ex) {
			   AutomationLogger.error("Error in Scrolling to element"+pScrollToElement);
		   }
		   
	   }
	   public static void ScrollPixels(WebDriver pWebDriver) {
		   try {
			   JavascriptExecutor js = (JavascriptExecutor) pWebDriver;
			   AutomationLogger.info("Scrolling down");		
			   js.executeScript("window.scrollBy(0,700)");
		   }catch(Exception ex) {
			   AutomationLogger.error("Error in Scrolling down");
		   }
		   
	   }
	   public static void BackPage(WebDriver pWebDriver) {
		   AutomationLogger.info("Going Back");
		   pWebDriver.navigate().back(); 
	   }
	   public static void MoveToElement(WebDriver pWebDriver, WebElement pElement) {
		   new Actions(pWebDriver).moveToElement(pElement).perform();
	   }
}
