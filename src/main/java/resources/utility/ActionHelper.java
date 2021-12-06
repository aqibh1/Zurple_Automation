package resources.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
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
				AutomationLogger.info("Waiting for the visibility of element ->"+pInputField);
				if(wait.until(ExpectedConditions.visibilityOf(pInputField))!=null) {
					pInputField.sendKeys(pStringToType);
					AutomationLogger.info("String typed ->"+pStringToType);
					isSuccessfull=true;
					staticWait(1);
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
			AutomationLogger.info("Clicking on button -> "+pElementToBeClicked);
			try {
				if(wait.until(ExpectedConditions.visibilityOf(pElementToBeClicked))!=null) {
					pElementToBeClicked.click();
					isSuccessfull=true;
					AutomationLogger.info("Clicked on button successful..");
				}
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to Click on "+pElementToBeClicked);
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessfull;
		}
	   
	   public static boolean ClickByStringElement(WebDriver pWebDriver,String pElementToBeClicked) {
		   boolean isSuccessfull=false;
			wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			AutomationLogger.info("Clicking on button -> "+pElementToBeClicked);
			try {
					pWebDriver.findElement(By.className(pElementToBeClicked)).click();
					isSuccessfull=true;
					AutomationLogger.info("Clicked on button successful..");
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to Click on "+pElementToBeClicked);
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessfull;
		}
	   
	   public static boolean ClickStringXpathElement(WebDriver pWebDriver,String pElementToBeClicked) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			AutomationLogger.info("Clicking on button -> "+pElementToBeClicked);
			try {
				if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pElementToBeClicked)))!=null) {
					pWebDriver.findElement(By.xpath(pElementToBeClicked)).click();
					isSuccessfull=true;
					AutomationLogger.info("Clicked on button successful..");
				}
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to Click on "+pElementToBeClicked);
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessfull;
		}
	   
	   public static boolean ClickByIndex(WebDriver pWebDriver, String pElementToBeClicked, int index) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			AutomationLogger.info("Clicking on button -> "+pElementToBeClicked);
			try {
					pWebDriver.findElements(By.className(pElementToBeClicked)).get(index).click();
					isSuccessfull=true;
					AutomationLogger.info("Clicked on button successful..");
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to Click on "+pElementToBeClicked);
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessfull;
		}
	   
	   public static boolean ClickByXpathIndex(WebDriver pWebDriver, String pElementToBeClicked, int index) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			AutomationLogger.info("Clicking on button -> "+pElementToBeClicked);
			try {
					pWebDriver.findElements(By.xpath(pElementToBeClicked)).get(index).click();
					isSuccessfull=true;
					AutomationLogger.info("Clicked on button successful..");
				
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
	   
	   
	   public static boolean waitForElementToBeDisappeared(WebDriver pWebDriver,WebElement pElementToBeDisappeared, long pWaitForTime) {			
		   wait=new WebDriverWait(pWebDriver, pWaitForTime);
		   boolean isElementVisible = false;
		   try {
			   isElementVisible=wait.until(ExpectedConditions.invisibilityOf(pElementToBeDisappeared));
		   }catch(Exception ex) {
			   AutomationLogger.error("Element did not disappear.  Wait max limit is "+pWaitForTime+" seconds");
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
	   
	   public static boolean waitForStringIDToBeVisible(WebDriver pWebDriver, String pElement, long pWaitInSecnds) {
		   boolean isElementVisible = false;
		   try {
			   WebDriverWait wait = new WebDriverWait(pWebDriver, pWaitInSecnds); // Wait for seconds.
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(pElement)));
		   }catch(Exception ex) {
			   AutomationLogger.error("Element is not visible.  -> "+pElement);
			   AutomationLogger.error("Wait max limit is "+GLOBAL_WAIT_COUNT+" seconds");
			   AutomationLogger.error(ex.getMessage());
		   }
		   return isElementVisible;
	   }
	   
	   public static boolean waitForStringXpathToBeVisible(WebDriver pWebDriver, String pElement, long pWaitInSecnds) {
		   boolean isElementVisible = false;
		   try {
			   WebDriverWait wait = new WebDriverWait(pWebDriver, pWaitInSecnds); // Wait for seconds.
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pElement)));
		   }catch(Exception ex) {
			   AutomationLogger.error("Element is not visible.  -> "+pElement);
			   AutomationLogger.error("Wait max limit is "+GLOBAL_WAIT_COUNT+" seconds");
			   AutomationLogger.error(ex.getMessage());
		   }
		   return isElementVisible;
	   }
	   
	   public static boolean waitForStringClassNameToBeVisible(WebDriver pWebDriver, String pElement, long pWaitInSecnds) {
		   boolean isElementVisible = false;
		   try {
			   WebDriverWait wait = new WebDriverWait(pWebDriver, pWaitInSecnds); // Wait for seconds.
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(pElement)));
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
			   AutomationLogger.info("Element Text :" +ltext);
			   
		   }
		   return ltext;
	   }
	   
	   public static String getTextByStringElement(WebDriver pWebDriver,String pElement) {
			String text = "";
		   	wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			AutomationLogger.info("Clicking on button -> "+pElement);
			try {
					pWebDriver.findElement(By.className(pElement)).getText();
					AutomationLogger.info("Clicked on button successful..");
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to Click on "+pElement);
				AutomationLogger.error(ex.getMessage());
			}
			return text;
		}
	   
	   public static String getTextByXpathIndex(WebDriver pWebDriver,String pElement, int index) {
			String text = "";
		   	wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			AutomationLogger.info("Clicking on button -> "+pElement);
			try {
					pWebDriver.findElements(By.xpath(pElement)).get(index).getText();
					AutomationLogger.info("Clicked on button successful..");
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to Click on "+pElement);
				AutomationLogger.error(ex.getMessage());
			}
			return text;
		}
	   
	   public static String getTextByIndex(WebDriver pWebDriver, String pElement, int index) {
		   String text = "";
		   	wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			AutomationLogger.info("Clicking on button -> "+pElement);
			try {
					text = pWebDriver.findElements(By.className(pElement)).get(index).getText();
					AutomationLogger.info("Clicked on button successful..");
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to Click on "+pElement);
				AutomationLogger.error(ex.getMessage());
			}
			return text;
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
					pInputField.sendKeys(Keys.BACK_SPACE);
					pInputField.clear();
					pInputField.sendKeys(pStringToType);
					AutomationLogger.info("String typed ->"+pStringToType);
					isSuccessfull=true;
					staticWait(1);
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
	
	public static List<WebElement> getListOfElementByClassName(WebDriver pWebDriver,String pElementXpath){
		List<WebElement> lList_of_Elements = null;
		try {
			lList_of_Elements = pWebDriver.findElements(By.className(pElementXpath));
		}catch(Exception ex) {
			AutomationLogger.error("Element list not found -> "+pElementXpath);
			AutomationLogger.error(ex.getMessage());
		}
		return lList_of_Elements;
		
	}
	
	public static List<WebElement> getListOfElementById(WebDriver pWebDriver,String pElementXpath){
		List<WebElement> lList_of_Elements = null;
		try {
			lList_of_Elements = pWebDriver.findElements(By.id(pElementXpath));
		}catch(Exception ex) {
			AutomationLogger.error("Element list not found -> "+pElementXpath);
			AutomationLogger.error(ex.getMessage());
		}
		return lList_of_Elements;
		
	}
	   
	   public static void RefreshPage(WebDriver pWebDriver) {
		   AutomationLogger.info("Refreshing the page");
		   AutomationLogger.info(pWebDriver.getCurrentUrl());
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
					staticWait(1);
				}
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to type in input field "+pInputField.getAttribute("xpath"));
				AutomationLogger.error("String to type : "+pStringToType);
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessfull;
		} 
	   
	   public static boolean TypeByStringElement(WebDriver pWebDriver, String pElementToBeClicked, String pStringToType, int index) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			AutomationLogger.info("Clicking on button -> "+pElementToBeClicked);
			try {
				pWebDriver.findElements(By.className(pElementToBeClicked)).get(index).clear();
				pWebDriver.findElements(By.className(pElementToBeClicked)).get(index).sendKeys(pStringToType);
				isSuccessfull=true;
				AutomationLogger.info("Clicked on button successful..");
			}catch(Exception ex) {
				AutomationLogger.error("Unable to Click on "+pElementToBeClicked);
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
		   try {
			   AutomationLogger.info("Fetching attribute: "+pAttributeName);
			   return pElement.getAttribute(pAttributeName).trim();
		   }catch(Exception ex) {
			   AutomationLogger.error("Unable to fetch attribute ");
			   return "";
		   }
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
	   
	   public static WebElement getDynamicElementByID(WebDriver pWebDriver,String id,String pDynamicVariable) {
	  		try {
	  		return pWebDriver.findElement(By.id(id.replace(FrameworkConstants.DYNAMIC_VARIABLE, pDynamicVariable)));
	  		}catch(Exception ex) {
	  			AutomationLogger.error("Unable to get dynamic webelement for xpath "+id);
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
	   public static boolean selectDropDownOptions2(WebDriver pWebDriver, WebElement pElementToBeClicked,String pDropdownOptionsXpath, String[] pOptionsToSelect) {
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
			   
			   return isSuccessful;
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
//		   pXpathToAppend = "/descendant::span[text()='FAILED']";
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
   public static boolean waitForElementToVisibleAfterRegularIntervals(WebDriver pWebDriver, WebElement pEelement, long pWaitIntervalInSeconds, int pTotalAttempts) {
		   
		   boolean displayed = false;
		   int counter = 0;
		   while (counter<pTotalAttempts && !displayed) {
			   // Element is found so set the boolean as true
			   displayed = isElementVisible(pWebDriver, pEelement);
			   if(!displayed) {
				   RefreshPage(pWebDriver);
				   try {
					Thread.sleep(pWaitIntervalInSeconds*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   counter++;
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
	  
	   public static boolean isElementToBeClickAble(WebDriver pWebDriver,WebElement pElement) {
			boolean isSuccessful =false;
			try {
				AutomationLogger.info("Waiting for the element to be clickable"+pElement);
				if(ExpectedConditions.elementToBeClickable(pElement)!=null) {
					isSuccessful=true;
				}
				
			}catch(Exception ex) {
				AutomationLogger.error("Element is not clickable -> "+pElement);
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessful;
		}
	   
	   public static boolean isElementSelected(WebDriver pWebDriver,WebElement pElement) {
			boolean isSuccessful =false;
			try {
				AutomationLogger.info("Checking if element is selected"+pElement);
				if(pElement.isSelected()) {
					isSuccessful=true;
				}
				
			}catch(Exception ex) {
				AutomationLogger.error("Element is not visible -> "+pElement);
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessful;
		}
	   
	   //True if you want to check the checkbox
	   public static boolean checkUncheckInputBox(WebDriver pWebDriver, WebElement pElement, boolean pSelect) {
		   boolean isSuccess = false;
		   boolean lElementVal = pElement.isSelected();
		   if(lElementVal && !pSelect) {
			   isSuccess =  ActionHelper.Click(pWebDriver, pElement);
		   }else if(!lElementVal && pSelect) {
			   isSuccess = ActionHelper.Click(pWebDriver, pElement);
		   }else if(lElementVal && pSelect) {
			   isSuccess = true;
		   }else if (!lElementVal && !pSelect) {
			   isSuccess = true;
		   }
		   return isSuccess;
	   }
	   
	   public static boolean typeAndSelect(WebDriver pWebDriver, WebElement pDropdown,String pElementToSelect, String pStringToType) {
		   boolean result=false;
		   if(waitForElementToBeVisible(pWebDriver,pDropdown,GLOBAL_WAIT_COUNT)) {
			   ActionHelper.Type(pWebDriver, pDropdown, pStringToType);
			   WebElement lElementToBeClicked = waitAndGetDynamicElement(pWebDriver, pElementToSelect,pStringToType);
			   if(lElementToBeClicked!=null) {
				   result= Click(pWebDriver, lElementToBeClicked);
			   }
		   }
		   return result;
	   }
	   
	   public static WebElement waitAndGetDynamicElement(WebDriver pWebDriver, String pElement,String pDynamicVariable) {
		 WebElement lElement = null;
		   int lTotalAttemps = 0;
		   do {
			   try {
				   lElement = pWebDriver.findElement(By.xpath(pElement.replace(FrameworkConstants.DYNAMIC_VARIABLE, pDynamicVariable)));
			   }catch(Exception ex) {
				   AutomationLogger.info("Unable to get dynamic webelement for xpath "+pElement);
				   AutomationLogger.info(ex.getMessage());
				   lTotalAttemps++;
				   staticWait(10);
			   }
		   }while(lElement==null && lTotalAttemps<5);
		   return lElement;
	   }
	   public static boolean handleDisableAdAlert(WebDriver pWebDriver) {
		   boolean isSuccess = true;
		   try {
			   if(ExpectedConditions.alertIsPresent()!=null) {
				   pWebDriver.switchTo().alert().accept();	   
			   }
		   }catch(Exception ex) {
			   isSuccess = false;
		   }
		   return isSuccess;
	   }
	   
	   public static boolean ClickAndSelect(WebDriver pWebDriver, WebElement pDropdown,String pElementToSelect, String pStringToType) {
		   boolean result=false;
		   if(waitForElementToBeVisible(pWebDriver,pDropdown,GLOBAL_WAIT_COUNT)) {
			   Click(pWebDriver, pDropdown);
			   Type(pWebDriver,pDropdown,pStringToType);
			   staticWait(20);
			   List<WebElement> list_element = getListOfElementByXpath(pWebDriver, pElementToSelect);
			   for(WebElement element: list_element) {
				   if(getText(pWebDriver, element).contains(pStringToType)) {
					   result = Click(pWebDriver,element);
					   break;
				   }
			   }
		   }
		   return result;
	   }
	   public static boolean clickSaveAsButtonWindows(WebDriver pWebDriver) {
		   boolean result = true;
		   try {
			   ActionHelper.staticWait(5);
			   Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\resources\\autoit-scripts"+"\\save-as.exe");
		   }catch(Exception ex) {
			   AutomationLogger.fatal("AutoIT error..Image upload failed..");
			   result = false;
		   }
		   return result;
	   }
	   public static boolean waitForProcessingToEnd(WebDriver pDriver) {
		   return waitForElementToBeDisappeared(pDriver,pDriver.findElement(By.id("DataTables_Table_0_processing")),30);
	   }
	   
	   public static boolean waitforElementToBeDisappearedByRegularIntervals(WebDriver pWebDriver, WebElement pElement, long pTime, int pAttempts) {
		   boolean isDisappeared = false;
		   int count = 0;
		   while(!isDisappeared && count<pAttempts) {
			   isDisappeared = !isElementVisible(pWebDriver, pElement);
			   if(!isDisappeared) {
				   staticWait(pTime);
				   ActionHelper.RefreshPage(pWebDriver);
			   }
		   }
		   return isDisappeared;
	   }
	 
	   public static boolean ClickForAds(WebDriver pWebDriver,WebElement pElementToBeClicked) {
		   boolean isSuccessfull=true;
		   wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
		   AutomationLogger.info("Clicking on button -> "+pElementToBeClicked);
		   try {
			   pElementToBeClicked.click();
			   
			   AutomationLogger.info("Clicked on button successful..");

		   }catch(Exception ex) {
			   isSuccessfull=false;
			   AutomationLogger.error("Unable to Click on "+pElementToBeClicked);
			   AutomationLogger.error(ex.getMessage());
		   }
		   return isSuccessfull;
	   }
	   public static void switchToSecondWindow(WebDriver pWebDriver) {
		   int counter = 0;
		   Set<String> handle= pWebDriver.getWindowHandles();
		   for(String lWindowHandle: handle) {
			   AutomationLogger.info(lWindowHandle);
			   if(counter>0) {
				   pWebDriver.switchTo().window(lWindowHandle);
			   }
			   counter++;
		   }
	   }
	   public static void switchToSecondWindowByIndex(WebDriver pWebDriver, int pIndex) {
		   int counter = 0;
		   Set<String> handle= pWebDriver.getWindowHandles();
		   for(String lWindowHandle: handle) {
			   AutomationLogger.info(lWindowHandle);
			   if(counter==pIndex) {
				   pWebDriver.switchTo().window(lWindowHandle);
			   }
			   counter++;
		   }
	   }
	   public static void switchToOriginalWindow(WebDriver pWebDriver) {
		   Set<String> handle= pWebDriver.getWindowHandles();
		   for(String lWindowHandle: handle) {
			   AutomationLogger.info(lWindowHandle);

			   pWebDriver.switchTo().window(lWindowHandle);
		   }
	   }

	   public static boolean isAlertPresent(WebDriver pWebDriver) {
		   boolean isSuccess = false;
//		   try {
//			   if(ExpectedConditions.alertIsPresent()!=null) {
//				   isSuccess= true;	   
//			   }
//		   }catch(Exception ex) {
//			   isSuccess = false;
//		   }
		   try 
		    { 
			   pWebDriver.switchTo().alert(); 
			   isSuccess = true; 
		    }   // try 
		    catch (NoAlertPresentException Ex) 
		    { 
		    	isSuccess = false; 
		    }   // 
		   return isSuccess;
	   }
	   
	   public static WebElement getElementByXpath(WebDriver pWebDriver,String pElementXpath){
			WebElement element = null;
			try {
				element = pWebDriver.findElement(By.xpath(pElementXpath));
			}catch(Exception ex) {
				AutomationLogger.error("Element list not found -> "+pElementXpath);
				AutomationLogger.error(ex.getMessage());
			}
			return element;	
		}
	   
	   public static WebElement getElementByID(WebDriver pWebDriver,String pElementXpath){
			WebElement element = null;
			try {
				element = pWebDriver.findElement(By.id(pElementXpath));
			}catch(Exception ex) {
				AutomationLogger.error("Element list not found -> "+pElementXpath);
				AutomationLogger.error(ex.getMessage());
			}
			return element;
			
		}
	 
	   public static boolean resizeWindow(WebDriver pWebDriver, int length, int width) {
		   boolean isSuccess = true;
		   try {
			   pWebDriver.manage().window().setSize(new Dimension(length, width));
			   isSuccess = true;
		   } catch(Exception ex) {
			   isSuccess = false;
		   }
		   return isSuccess;
	   }
	   //Pass the pExpand value true or false if you want to expand the dropdown accordingly
	   public static boolean expandUnexpandDropdown(WebDriver pWebDriver,WebElement pElement, boolean pExpand) {
		   boolean isExpanded = Boolean.parseBoolean(pElement.getAttribute("aria-expanded"));
		   if(isExpanded && pExpand) {
			   AutomationLogger.info("Element is already expanded");
			   return true;
		   }else if(isExpanded && !pExpand) {
			   return ActionHelper.Click(pWebDriver, pElement);
		   }else if(!isExpanded && pExpand) {
			   return ActionHelper.Click(pWebDriver, pElement);
		   }else {
			   AutomationLogger.info("Element is already not expanded");
			   return true;
		   }
	   }
	   
	   public static boolean checkUncheckInputBox(WebDriver pWebDriver, WebElement pElementToCheck,WebElement pElementToClick, boolean pSelect) {
		   boolean isSuccess = false;
		   boolean lElementVal = pElementToCheck.isSelected();
		   if(lElementVal && !pSelect) {
			   isSuccess =  ActionHelper.Click(pWebDriver, pElementToClick);
		   }else if(!lElementVal && pSelect) {
			   isSuccess = ActionHelper.Click(pWebDriver, pElementToClick);
		   }else if(lElementVal && pSelect) {
			   isSuccess = true;
		   }else if (!lElementVal && !pSelect) {
			   isSuccess = true;
		   }
		   return isSuccess;
	   }
	   
	   public static boolean getDynamicElementAfterRegularIntervals(WebDriver pWebDriver,String pXpath,String pDynamicVariable, int pTotalAttempts) {
		   WebElement element = null;
		   int counter = 0;
		   while (counter<pTotalAttempts && element==null) {
			   try {
				   element = pWebDriver.findElement(By.xpath(pXpath.replace(FrameworkConstants.DYNAMIC_VARIABLE, pDynamicVariable)));
			   }catch(Exception ex) {
				   AutomationLogger.error("Unable to get dynamic webelement for xpath "+pXpath);
				   AutomationLogger.error(ex.getMessage());
				   element = null;
				   pWebDriver.navigate().refresh();
				   AutomationLogger.info("Waiting for 1 minute before refreshing the page");
				   staticWait(60);
				   counter++;
			   }
		   }
		   return element!=null;
	   }
	   public static boolean verifyTextAfterRegularIntervals(WebDriver pWebDriver,WebElement pXpath,String pTextToVerify, int pTotalAttempts) {
		   boolean isVerified = false;
		   int counter = 0;
		   while (counter<pTotalAttempts && !isVerified) {
			   isVerified = getText(pWebDriver, pXpath).equalsIgnoreCase(pTextToVerify);
			   if(!isVerified) {
				   AutomationLogger.info("Unable to get dynamic webelement for xpath "+pXpath);
				   pWebDriver.navigate().refresh();
				   staticWait(60);
				   counter++;
			   }
		   }
		   return isVerified;
	   }
	   //Give xpath of of elements which return number of elements in list
	   public static boolean findTextInListOfElements(WebDriver pWebDriver, String pListXpaths, String pTextToFind) {
		   boolean isFound = false;
		   List<WebElement> list = getListOfElementByXpath(pWebDriver, pListXpaths);
		   for(WebElement element: list) {
			   isFound = getText(pWebDriver, element).contains(pTextToFind)?true:false;
			   if(isFound) {
				   break;
			   }
		   }
		   return isFound;
	   }
	   public static boolean waitForElementToBeFoundAfterRegularIntervals(WebDriver pWebDriver,String pListXpaths, String pTextToFind, int pTotalAttempts) {
		   boolean isVerified = false;
		   int counter = 0;
		   while (counter<pTotalAttempts && !isVerified) {
			   if(findTextInListOfElements(pWebDriver, pListXpaths,pTextToFind)) {
				   isVerified = true;
			   }else {
				   pWebDriver.navigate().refresh();
				   staticWait(60);
				   counter++;
			   }
		   }
		   return true;
	   }
	   public static boolean waitForElementToBeFoundAfterRegularIntervals(WebDriver pWebDriver,String pListXpaths, String pTextToFind, String pTextToFind2, int pTotalAttempts) {
		   boolean isVerified = false;
		   int counter = 0;
		   while (counter<pTotalAttempts && !isVerified) {
			   if(findTextInListOfElements(pWebDriver, pListXpaths,pTextToFind)) {
				   isVerified = true;
			   }else if(findTextInListOfElements(pWebDriver, pListXpaths,pTextToFind2)) {
				   isVerified = false;
				   break;
			   }else {
				   pWebDriver.navigate().refresh();
				   staticWait(60);
				   counter++;
			   }
		   }
		   return isVerified;
	   }
	   public static boolean TypeForUploadImage(WebDriver pWebDriver,WebElement pInputField, String pStringToType) {
		   boolean isSuccessfull=false;
		   try {
			   pInputField.sendKeys(pStringToType);
			   AutomationLogger.info("String typed ->"+pStringToType);
			   isSuccessfull=true;
			   staticWait(1);
		   }catch(Exception ex) {
			   AutomationLogger.error("Unable to type in input field "+pInputField.getAttribute("xpath"));
			   AutomationLogger.error("String to type : "+pStringToType);
			   AutomationLogger.error(ex.getMessage());
		   }
		   return isSuccessfull;
	   }
	  
	   public static boolean appendAtStart(WebDriver pWebDriver,WebElement pInputField, String pStringToType) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(pWebDriver, GLOBAL_WAIT_COUNT);
			try {
				AutomationLogger.info("Waiting for the visibility of element ->"+pInputField);
				if(wait.until(ExpectedConditions.visibilityOf(pInputField))!=null) {
					pInputField.sendKeys(Keys.CONTROL,Keys.HOME);
					staticWait(5);
					pInputField.sendKeys(pStringToType);
					AutomationLogger.info("String typed ->"+pStringToType);
					isSuccessfull=true;
					staticWait(1);
				}
				
			}catch(Exception ex) {
				AutomationLogger.error("Unable to type in input field "+pInputField.getAttribute("xpath"));
				AutomationLogger.error("String to type : "+pStringToType);
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessfull;
		}
	   public static boolean dragAndDropByPixels(WebDriver pWebDriver, WebElement pFrom, int pXAxis, int pYAxis) {
		   boolean result = true;
		   try {
			   Actions action=new Actions(pWebDriver);
			   action.dragAndDropBy(pFrom, pXAxis, pYAxis).build().perform();
			   staticWait(5);
		   }catch(Exception ex) {
			   AutomationLogger.error("Drag and Drop failed for "+pFrom);
			   result = false;
		   }
		   return result;
	   }
	   public static String getSelectedOption(WebDriver pWebDriver, WebElement pElementToBeClicked,String pDropdownOptionsXpath) {
			String isSuccessful="";
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
					if(element.isSelected()) {
						isSuccessful = ActionHelper.getText(pWebDriver, element);
						break;
					}
				}
			}
			return isSuccessful;
		}
	   public static boolean ClickWithStaticWait(WebDriver pWebDriver,WebElement pElementToBeClicked) {
		   boolean isSuccessfull=false;
		   staticWait(5);
		   AutomationLogger.info("Clicking on button -> "+pElementToBeClicked);
		   try {
			   pElementToBeClicked.click();
			   isSuccessfull=true;
			   AutomationLogger.info("Clicked on button successful..");
		   }catch(Exception ex) {
			   AutomationLogger.error("Unable to Click on "+pElementToBeClicked);
			   AutomationLogger.error(ex.getMessage());
		   }
		   return isSuccessfull;
	   }
	   public static boolean clickAndSelectByIndex(WebDriver pWebDriver,WebElement pElementToBeClicked,String pOptions, int pIndex) {
		   try {
			   boolean isSuccessful=false;
			   List<WebElement> list_of_options = new ArrayList<WebElement>();
			   AutomationLogger.info("Clicking on button "+pElementToBeClicked);
			   if(ActionHelper.Click(pWebDriver, pElementToBeClicked)) {
				   AutomationLogger.info("Selecting a option from Dropdown "+pOptions);
				   list_of_options = getListOfElementByXpath(pWebDriver, pOptions);
				   isSuccessful = ActionHelper.Click(pWebDriver, list_of_options.get(pIndex));
			   }
			   
			   return isSuccessful;
		   }catch(Exception ex) {
				return false;
			}
	   }
	  
	   public static boolean clickAndSelectFromDropdownByValue(WebDriver pWebDriver, WebElement pElementToBeClicked, String pValueToSelect) {
			boolean isSuccessful=false;
			List<WebElement> list_of_options = new ArrayList<WebElement>();
			 AutomationLogger.info("Clicking on button "+pElementToBeClicked);
			 list_of_options = pElementToBeClicked.findElements(By.tagName("option"));
			if(ActionHelper.Click(pWebDriver, pElementToBeClicked)) {
				for(WebElement element: list_of_options) {
					System.out.println(element.getText().trim());
					if(element.getAttribute("value").trim().equalsIgnoreCase(pValueToSelect)) {
						isSuccessful = ActionHelper.Click(pWebDriver, element);
						Click(pWebDriver,pElementToBeClicked);
						break;
					}
				}
			}
			return isSuccessful;
		}
	   public static boolean waitforDropdownToBePopulated(WebDriver pWebDriver, WebElement pElementToBeClicked, int pAttempts) {
		   boolean isPopulated = false;
		   int counter = 0;
		   List<WebElement> list_of_options = new ArrayList<WebElement>();
		   AutomationLogger.info("Clicking on button "+pElementToBeClicked);
		   while(!isPopulated && counter<pAttempts) {
			   ActionHelper.Click(pWebDriver, pElementToBeClicked);
			   list_of_options = pElementToBeClicked.findElements(By.tagName("option"));
			   if(list_of_options.size()>1) {
				   isPopulated = true;
			   }else {
				   staticWait(30);
			   }
		   }
		   return isPopulated;
	   }
	   public static String getTextByJS(WebDriver pWebDriver, String pElementId) {
		   JavascriptExecutor je = (JavascriptExecutor) pWebDriver;
		   String script = "return document.getElementById('"+pElementId+"').getAttribute('value');";
		   String value = (String) je.executeScript(script); 
		   return value;
	   }
	   public static void openUrlInNewTab(WebDriver pWebDriver, String pUrl) {
		   pWebDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"t");
		   switchToSecondWindow(pWebDriver);
		   pWebDriver.navigate().to(pUrl);
	   }
	   
	   public static void openUrlInCurrentTab(WebDriver pWebDriver, String pUrl) {
		   pWebDriver.navigate().to(pUrl);
	   }
	   
	   public static void switchToiFrame(WebDriver pWebDriver, String pIndex) {
		   pWebDriver.switchTo().frame(pIndex);
	   }
	   
	   public static void switchToiFramebyElement(WebDriver pWebDriver, WebElement pElement) {
		   pWebDriver.switchTo().frame(pElement);
	   }
	   
	   public static void switchToDefaultContent(WebDriver pWebDriver) {
		   pWebDriver.switchTo().defaultContent();
	   }

	   public static boolean sendSpecialKeys(WebDriver pWebDriver,Keys pKey) {
		   boolean isSuccessfull=false;
		   try {
			   Actions actions = new Actions(pWebDriver);
			   actions.sendKeys(pKey).build().perform();
			   isSuccessfull=true;
		   }catch(Exception ex) {
			   AutomationLogger.error("Unable to Click on "+pKey);
			   AutomationLogger.error(ex.getMessage());
		   }
		   return isSuccessfull;
	   }
	   public static boolean isElementEnabled(WebDriver pWebDriver,WebElement pElement) {
			boolean isSuccessful =false;
			try {
				AutomationLogger.info("Checking if element is enabled"+pElement);
				if(pElement.isEnabled()) {
					isSuccessful=true;
				}
			}catch(Exception ex) {
				AutomationLogger.error("Element is not visible -> "+pElement);
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessful;
		}
	   public static boolean clickThroughJS(WebDriver pWebDriver,WebElement pElement) {
		   boolean isSuccess = true;
		   try {
		   JavascriptExecutor executor = (JavascriptExecutor)pWebDriver;
		   executor.executeScript("arguments[0].click();", pElement);
		   }catch(Exception ex) {
			   AutomationLogger.error("Click failed through JS Executor "+pElement);
			   isSuccess = false;
		   }
		   return isSuccess;
	   }
	   public static boolean sendSpecialKeys(WebElement pElement,Keys pKey) {
		   boolean isSuccessfull=false;
		   try {
			   pElement.sendKeys(pKey);
			   isSuccessfull=true;
		   }catch(Exception ex) {
			   AutomationLogger.error("Unable to Click on "+pKey);
			   AutomationLogger.error(ex.getMessage());
		   }
		   return isSuccessfull;
	   }
	   //This function return the CSS property value
	   public static String getCssValueOfTheElement(WebDriver pWebDriver, WebElement pElement, String pPropName) {
		   String pAttributeValue = "";
		   if(isElementVisible(pWebDriver, pElement)) {
			   pAttributeValue = pElement.getCssValue(pPropName).trim();
		   }
		   return pAttributeValue;
	   }
	   
	   public static boolean typeInAlertText(WebDriver pWebDriver) {
		   boolean isAlertHandled = true;
		   try {
			   Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			   //Type your message
			   alert.sendKeys("Selenium");
		   }catch(Exception ex) {
			   isAlertHandled = false;
		   }
		   return isAlertHandled;
	   }
	   public static String getValidationMessage(WebDriver pWebDriver, WebElement pElement) {
		   String message = getAttribute(pElement, "validationMessage"); 
		   return message;
	   }
	   /**
	 * @param pWebDriver
	 * @param pElementToBeClicked
	 * @param pDropdownOptionsXpath
	 * @param pOptionToSelect
	 * @return boolean
	 * Select the drop down option if contains the text
	 */
	   public static boolean selectDropDownOptionIfContains(WebDriver pWebDriver, WebElement pElementToBeClicked,String pOptionToSelect) {
		   boolean isSuccessful=false;
		   List<WebElement> list_of_options = new ArrayList<WebElement>();
		   AutomationLogger.info("Clicking on button "+pElementToBeClicked);
		   if(ActionHelper.Click(pWebDriver, pElementToBeClicked)) {
			   list_of_options = pElementToBeClicked.findElements(By.tagName("option"));
			   for(WebElement element: list_of_options) {
				   System.out.println(element.getText().trim());
				   if(element.getText().trim().contains(pOptionToSelect)) {
					   isSuccessful = ActionHelper.Click(pWebDriver, element);
					   break;
				   }
			   }
		   }
		   return isSuccessful;
	   }   
	   
	   public static String getAlertText(WebDriver pWebDriver) {
		   String alert_text = "";
		   try {
			   if(ExpectedConditions.alertIsPresent()!=null) {
				   alert_text=pWebDriver.switchTo().alert().getText();	   
			   }
		   }catch(Exception ex) {
			   alert_text = "";
		   }
		   return alert_text;
	   }
	   public static boolean isElementDisplayed(WebDriver pWebDriver, WebElement pElement) {
		   boolean isSuccessful =false;
			try {
				AutomationLogger.info("Checking if element is enabled"+pElement);
				if(pElement.isDisplayed()) {
					isSuccessful=true;
				}
			}catch(Exception ex) {
				AutomationLogger.error("Element is not visible -> "+pElement);
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessful;
	   }
	   public static long getVerticlePixels(WebDriver pWebDriver) {
		   JavascriptExecutor executor = (JavascriptExecutor) pWebDriver;
		   Long value = (Long) executor.executeScript("return window.pageYOffset;");
		   return value;
	   }
}
