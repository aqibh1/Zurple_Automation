package resources.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class ActionHelper {
	protected static WebDriverWait wait;
	private static long GLOBAL_WAIT_COUNT=30;
	
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
				AutomationLogger.error(ex.getMessage());
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
				AutomationLogger.error("Unable to Click on "+pElementToBeClicked);
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessfull;
		}
	   
	   public static boolean MouseHoverOnElement(WebDriver pWebDriver, WebElement pElementToBeHoveredOn) {
		   boolean status = true;
		   if(isElementVisible(pWebDriver, pElementToBeHoveredOn)) {
			   Actions action = new Actions(pWebDriver);
			   action.moveToElement(pElementToBeHoveredOn).build().perform();
			   AutomationLogger.info("Mouse hover is successfull");
		   }else {
			   AutomationLogger.error("Mouse hover is unsuccessful over element ->"+pElementToBeHoveredOn);
			   status = false;
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
			   AutomationLogger.error(ex.getMessage());
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
			   AutomationLogger.error(ex.getMessage());
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
			   AutomationLogger.error(ex.getMessage());
		   }
		   return isElementVisible;
	   }
	   
	   public static String getText(WebDriver pWebDriver,WebElement pElement) {
		   String ltext="";
		   if(isElementVisible(pWebDriver, pElement)) {
			   ltext=pElement.getText().trim();
		   }
		   return ltext;
	   }

	   public static String getValue(WebDriver pWebDriver,WebElement pElement) {
		   String ltext="";
		   if(isElementVisible(pWebDriver, pElement)) {
			   ltext=pElement.getAttribute("value");
		   }
		   return ltext;
	   }
	   public static String getTextByValue(WebDriver pWebDriver,WebElement pElement) {
		   String ltext="";
		   if(isElementVisible(pWebDriver, pElement)) {
			   ltext=pElement.getAttribute("value").trim();
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
				AutomationLogger.error(ex.getMessage());
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
			AutomationLogger.error(ex.getMessage());
		}
		return isSuccessful;
	}
	
	public static List<WebElement> getListOfElementByXpath(WebDriver pWebDriver,String pElementXpath){
		List<WebElement> lList_of_Elements = null;
		try {
			lList_of_Elements = pWebDriver.findElements(By.xpath(pElementXpath));
		}catch(Exception ex) {
			AutomationLogger.error("Element list not found -> "+pElementXpath);
			AutomationLogger.error(ex.getMessage());
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
				AutomationLogger.error(ex.getMessage());
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
	   public static String getAttribute(WebElement pElement,String pAttributeName) {
		   AutomationLogger.info("Fetching attribute: "+pAttributeName);
		   return pElement.getAttribute(pAttributeName);
		   
	   }
	   
	   public static WebElement getDynamicElement(WebDriver pWebDriver,String pXpath,String pDynamicVariable) {
	  		try {
	  		return pWebDriver.findElement(By.xpath(pXpath.replace(FrameworkConstants.DYNAMIC_VARIABLE, pDynamicVariable)));
	  		}catch(Exception ex) {
	  			AutomationLogger.error("Unable to get dynamic webelement for xpath "+pXpath);
	  			AutomationLogger.error(ex.getMessage());
	  			return null;
	  		}
	  	}
	   
	   
	   public static boolean waitForElementToBeLocated(WebDriver pWebDriver,String pElement,long pWaitInSecnds) {
		   wait=new WebDriverWait(pWebDriver, pWaitInSecnds);
		   boolean isElementVisible = false;
		   AutomationLogger.info("Waiting for element to be Located ->"+pElement);
		   try {
			   isElementVisible=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pElement)))!=null;
		   }catch(Exception ex) {
			   AutomationLogger.error("Element is not visible.  -> "+pElement);
			   AutomationLogger.error("Wait max limit is "+GLOBAL_WAIT_COUNT+" seconds");
			   AutomationLogger.error(ex.getMessage());
		   }
		   AutomationLogger.info("Element Found Successfully");
		   return isElementVisible;
	   }
	   
	   public static String getDynamicElementXpath(WebDriver pWebDriver,String pXpath,String pDynamicVariable) {
	  		try {
	  		return pXpath.replace(FrameworkConstants.DYNAMIC_VARIABLE, pDynamicVariable);
	  		}catch(Exception ex) {
	  			AutomationLogger.error("Unable to get dynamic webelement for xpath "+pXpath);
	  			AutomationLogger.error(ex.getMessage());
	  			return null;
	  		}
	  	}
	   public static boolean clickAndSelectFromMenuButton(WebDriver pWebDriver, WebElement pElementToBeClicked,WebElement pElementToBeSelected) {
			boolean isSuccessful=false;
			 AutomationLogger.info("Clicking on button "+pElementToBeClicked);
			if(ActionHelper.Click(pWebDriver, pElementToBeClicked)) {
				AutomationLogger.info("Selecting a option from Dropdown "+pElementToBeSelected);
				isSuccessful = ActionHelper.Click(pWebDriver, pElementToBeSelected);
			}
			return isSuccessful;
		}
	   public static boolean selectDropDownOption(WebDriver pWebDriver, WebElement pElementToBeClicked,String pDropdownOptionsXpath, String pOptionToSelect) {
			boolean isSuccessful=false;
			List<WebElement> list_of_options = new ArrayList<WebElement>();
			 AutomationLogger.info("Clicking on button "+pElementToBeClicked);
			if(ActionHelper.Click(pWebDriver, pElementToBeClicked)) {
				if(pDropdownOptionsXpath.isEmpty()) {
					 list_of_options = pElementToBeClicked.findElements(By.tagName("option"));
				}else {
					 list_of_options = pWebDriver.findElements(By.xpath(pDropdownOptionsXpath));
				}	
				AutomationLogger.info("Selecting a option from Dropdown "+pDropdownOptionsXpath);
				for(WebElement element: list_of_options) {
					System.out.println(element.getText().trim());
					if(element.getText().trim().equalsIgnoreCase(pOptionToSelect)) {
						isSuccessful = ActionHelper.Click(pWebDriver, element);
						Click(pWebDriver,pElementToBeClicked);
						break;
					}
				}
			}
			return isSuccessful;
		}
	   public static boolean selectDropDownOptions(WebDriver pWebDriver, WebElement pElementToBeClicked,String pDropdownOptionsXpath, String[] pOptionsToSelect) {
		   try {
			   boolean isSuccessful=false;
			   List<WebElement> list_of_options = new ArrayList<WebElement>();
			   AutomationLogger.info("Clicking on button "+pElementToBeClicked);
			   if(ActionHelper.Click(pWebDriver, pElementToBeClicked)) {
				   AutomationLogger.info("Selecting a option from Dropdown "+pDropdownOptionsXpath);
				   for(String pOptionToSelect: pOptionsToSelect) {
					   if(pDropdownOptionsXpath.isEmpty()) {
						   list_of_options = pElementToBeClicked.findElements(By.tagName("option"));
					   }else {
						   list_of_options = pWebDriver.findElements(By.xpath(pDropdownOptionsXpath));
					   }	
					   for(WebElement element: list_of_options) {
						   if(element.getText().equalsIgnoreCase(pOptionToSelect.trim())) {
							   isSuccessful = ActionHelper.Click(pWebDriver, element);
							   break;
						   }
					   }
				   }
			   }
			   
			   return Type(pWebDriver,pElementToBeClicked.findElement(By.tagName("input")),Keys.ESCAPE) && isSuccessful;
		   }catch(Exception ex) {
				return false;
			}
		}
	   
	   public static boolean waitForElementsToBeFound(WebDriver pWebDriver, String pXpath) {
		   boolean isFound = false;
		   int counter = 0;
		   while(counter<100) {
			   System.out.println(counter);
			   if(pWebDriver.findElements(By.xpath(pXpath))!=null) {
				   isFound = true;
				   break;
			   }
			   counter++;
		   }
		   return isFound;
	   }
	   public static boolean waitForAjaxToBeCompleted(WebDriver pWebDriver) {
		   boolean isFound = false;
		   try {
			   int counter = 0;
			   AutomationLogger.info("Waiting for Ajax to be disappear ");
			   while(counter<100) {
				   if(!pWebDriver.findElement(By.id("ajax_working")).getAttribute("style").contains("block")) {
					   isFound = true;
					   break;
				   }
				   counter++;
				   Thread.sleep(1000);
			   }
		   }catch(Exception ex) {
			   isFound = false;
		   }
		   return isFound;
	   }
	   
	   public static boolean waitForElementToBeDisappeared(WebDriver pWebDriver,String pElementToBeDisappeared) {			
		   wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
		   boolean isElementVisible = false;
		   try {
			   isElementVisible=wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(pElementToBeDisappeared)));
		   }catch(Exception ex) {
			   AutomationLogger.error("Element did not disappear.  Wait max limit is "+GLOBAL_WAIT_COUNT+" seconds");
			   AutomationLogger.error(ex.getMessage());
		   }
		   return isElementVisible;

	   }
	   public static String getHeading(WebDriver pWebDriver,String pHeading) {
		   return pWebDriver.findElement(By.tagName(pHeading)).getText();
		   
	   }
	   
	   public static boolean waitForElementToVisibleAfterRegularIntervals(WebDriver pWebDriver, WebElement pEelement, String pXpathToAppend, long pWaitIntervalInSeconds, int pTotalAttempts) {
		   
		   boolean displayed = false;
		   int counter = 0;
		   pXpathToAppend = "/descendant::span[text()='FAILED']";
		    while (counter<pTotalAttempts) {
		    	try {
		    	WebElement elementFund = pEelement.findElement(By.xpath(pXpathToAppend));
		        if (elementFund!=null) {
		            // Element is found so set the boolean as true
		            displayed = isElementVisible(pWebDriver, elementFund);
		        } 
		    	}catch(Exception ex) {
		    		try {
		            	RefreshPage(pWebDriver);
						Thread.sleep(pWaitIntervalInSeconds*1000);
						counter++;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    }
			return displayed;
	       
	   }
	   public static void staticWait(long pTimeInSeconds) {
		   try {
			   Thread.sleep(pTimeInSeconds*1000);
		   }catch(Exception ex) {
			   
		   }
	   }
	   //Drag and Drop Method
	   public static boolean dragAndDrop (WebDriver pWebDriver, WebElement pFrom, WebElement pTo) {
		   boolean result = true;
		   try {
			   Actions action=new Actions(pWebDriver);
			   action.dragAndDrop(pFrom, pTo).build().perform();
			   staticWait(5);
		   }catch(Exception ex) {
			   AutomationLogger.error("Drag and Drop failed for "+pFrom);
			   result = false;
		   }
		   return result;
	   }
	   
	   public static void ScrollDownByPixels(WebDriver pWebDriver,String pPixels) {
		   try {
			   JavascriptExecutor js = (JavascriptExecutor) pWebDriver;
			   AutomationLogger.info("Scrolling down");		
			   js.executeScript("window.scrollBy(0,"+pPixels+")");
		   }catch(Exception ex) {
			   AutomationLogger.error("Error in Scrolling down");
		   }
		   
	   }
	   public static boolean Clear(WebDriver pWebDriver,WebElement pInputField) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			try {
				AutomationLogger.info("Waiting for the visibility of element ->"+pInputField);
				if(wait.until(ExpectedConditions.visibilityOf(pInputField))!=null) {
					pInputField.clear();
					AutomationLogger.info("Input field cleared..");
					isSuccessfull=true;
				}
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to clear input field ");
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessfull;
		}
	   public static boolean isOptionSelected(WebDriver pWebDriver, WebElement pDropdown, String pOption) {
		   boolean lOptionValue = false;
		   try {
			   if(isElementVisible(pWebDriver, pDropdown)) {
				   List<WebElement> list_of_options = pDropdown.findElements(By.tagName("option"));
				   for(WebElement element: list_of_options) {
					   if(element.getText().equalsIgnoreCase(pOption)) {
						   lOptionValue = element.isSelected();
						   break;
					   }
				   }
			   }
		   }catch(Exception ex) {
			   AutomationLogger.error("Element did not disappear.  Wait max limit is "+GLOBAL_WAIT_COUNT+" seconds");
			   AutomationLogger.error(ex.getMessage());
		   }
		   return lOptionValue;
	   }
	   
	   public static boolean doubleClick(WebDriver pWebDriver,WebElement pElementToBeClicked) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			try {
				if(wait.until(ExpectedConditions.visibilityOf(pElementToBeClicked))!=null) {
					Actions actions = new Actions(pWebDriver);
					actions.doubleClick(pElementToBeClicked).perform();
					isSuccessfull=true;
				}
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to Click on "+pElementToBeClicked);
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessfull;
		}
}
