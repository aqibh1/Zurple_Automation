package resources.utility;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ActionHelper {
	protected  WebDriverWait wait;
	private  long GLOBAL_WAIT_COUNT=30;
	private WebDriver driver;
	
	public ActionHelper(WebDriver pWebDriver) {
		driver = pWebDriver;
	}
	
	public boolean Type(WebElement pInputField, String pStringToType) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(driver, GLOBAL_WAIT_COUNT);
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
		
	   public boolean Click(WebElement pElementToBeClicked) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(driver, GLOBAL_WAIT_COUNT);
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
	   
	   public boolean MouseHoverOnElement(WebElement pElementToBeHoveredOn) {
		   boolean status = true;
		   if(isElementVisible(pElementToBeHoveredOn)) {
			   Actions action = new Actions(driver);
			   action.moveToElement(pElementToBeHoveredOn).build().perform();
			   AutomationLogger.info("Mouse hover is successfull");
		   }else {
			   AutomationLogger.error("Mouse hover is unsuccessful over element ->"+pElementToBeHoveredOn);
			   status = false;
		   }
		   return status;
		}
	   
	   public boolean waitForElementToBeDisappeared(WebElement pElementToBeDisappeared) {			
		   wait=new WebDriverWait(driver, GLOBAL_WAIT_COUNT);
		   boolean isElementVisible = false;
		   try {
			   isElementVisible=wait.until(ExpectedConditions.invisibilityOf(pElementToBeDisappeared));
		   }catch(Exception ex) {
			   AutomationLogger.error("Element did not disappear.  Wait max limit is "+GLOBAL_WAIT_COUNT+" seconds");
			   AutomationLogger.error(ex.getMessage());
		   }
		   return isElementVisible;

	   }
	   
	   public  boolean isElementVisible(WebElement pElement) {
		   wait=new WebDriverWait(driver, GLOBAL_WAIT_COUNT);
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
	   
	   public  boolean waitForElementToBeVisible(WebElement pElement,long pWaitInSecnds) {
		   wait=new WebDriverWait(driver, pWaitInSecnds);
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
	   
	   public String getText(WebElement pElement) {
		   String ltext="";
		   if(isElementVisible(pElement)) {
			   ltext=pElement.getText().trim();
		   }
		   return ltext;
	   }

	   public String getValue(WebElement pElement) {
		   String ltext="";
		   if(isElementVisible(pElement)) {
			   ltext=pElement.getAttribute("value");
		   }
		   return ltext;
	   }
	   public String getTextByValue(WebElement pElement) {
		   String ltext="";
		   if(isElementVisible(pElement)) {
			   ltext=pElement.getAttribute("value").trim();
		   }
		   return ltext;
	   }
	   
	   public boolean clickAndSelect(WebElement pDropdown,WebElement pElementToSelect) {
		   boolean result=false;
		   if(waitForElementToBeVisible(pDropdown,GLOBAL_WAIT_COUNT)) {
			   Click(pDropdown);
			   if(waitForElementToBeVisible(pElementToSelect, 5)) {
				   result= Click(pElementToSelect);
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
	public boolean ClearAndType(WebElement pInputField, String pStringToType) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(driver, GLOBAL_WAIT_COUNT);
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
	
	public boolean waitForElementToBeClickAble(WebElement pElement) {
		boolean isSuccessful =false;
		wait=new WebDriverWait(driver, GLOBAL_WAIT_COUNT);
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
	
	public  List<WebElement> getListOfElementByXpath(String pElementXpath){
		List<WebElement> lList_of_Elements = null;
		try {
			lList_of_Elements = driver.findElements(By.xpath(pElementXpath));
		}catch(Exception ex) {
			AutomationLogger.error("Element list not found -> "+pElementXpath);
			AutomationLogger.error(ex.getMessage());
		}
		return lList_of_Elements;
		
	}
	   
	   public  void RefreshPage() {
		   AutomationLogger.info("Refreshing the page");
		   driver.navigate().to(driver.getCurrentUrl()); 
	   }
	   
	   public  void waitForTime(long pWaitInSecnds) throws InterruptedException {
		   wait=new WebDriverWait(driver, pWaitInSecnds);
		   wait.wait(pWaitInSecnds);
		   
	   }
	   
	   public  boolean Type(WebElement pInputField, Keys pStringToType) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(driver, GLOBAL_WAIT_COUNT);
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
	   public  void ScrollToElement(WebElement pScrollToElement) {
		   try {
			   JavascriptExecutor js = (JavascriptExecutor) driver;
			   AutomationLogger.info("Scrolling to element ->"+pScrollToElement);		
			   js.executeScript("arguments[0].scrollIntoView();", pScrollToElement);
		   }catch(Exception ex) {
			   AutomationLogger.error("Error in Scrolling to element"+pScrollToElement);
		   }
		   
	   }
	   public void ScrollPixels() {
		   try {
			   JavascriptExecutor js = (JavascriptExecutor) driver;
			   AutomationLogger.info("Scrolling down");		
			   js.executeScript("window.scrollBy(0,700)");
		   }catch(Exception ex) {
			   AutomationLogger.error("Error in Scrolling down");
		   }
		   
	   }
	   public  void BackPage(WebDriver pWebDriver) {
		   AutomationLogger.info("Going Back");
		   pWebDriver.navigate().back(); 
	   }
	   public  void MoveToElement(WebElement pElement) {
		   new Actions(driver).moveToElement(pElement).perform();
	   }
	   public  String getAttribute(WebElement pElement,String pAttributeName) {
		   AutomationLogger.info("Fetching attribute: "+pAttributeName);
		   return pElement.getAttribute(pAttributeName);
		   
	   }
	   
	   public WebElement getDynamicElement(String pXpath,String pDynamicVariable) {
	  		try {
	  		return driver.findElement(By.xpath(pXpath.replace(FrameworkConstants.DYNAMIC_VARIABLE, pDynamicVariable)));
	  		}catch(Exception ex) {
	  			AutomationLogger.error("Unable to get dynamic webelement for xpath "+pXpath);
	  			AutomationLogger.error(ex.getMessage());
	  			return null;
	  		}
	  	}
	   
	   public  boolean waitForElementToBeLocated(String pElement,long pWaitInSecnds) {
		   wait=new WebDriverWait(driver, pWaitInSecnds);
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
	   
	   public  String getDynamicElementXpath(String pXpath,String pDynamicVariable) {
	  		try {
	  		return pXpath.replace(FrameworkConstants.DYNAMIC_VARIABLE, pDynamicVariable);
	  		}catch(Exception ex) {
	  			AutomationLogger.error("Unable to get dynamic webelement for xpath "+pXpath);
	  			AutomationLogger.error(ex.getMessage());
	  			return null;
	  		}
	  	}
	   public boolean clickAndSelectFromMenuButton(WebElement pElementToBeClicked,WebElement pElementToBeSelected) {
			boolean isSuccessful=false;
			 AutomationLogger.info("Clicking on button "+pElementToBeClicked);
			if(Click(pElementToBeClicked)) {
				AutomationLogger.info("Selecting a option from Dropdown "+pElementToBeSelected);
				isSuccessful = Click(pElementToBeSelected);
			}
			return isSuccessful;
		}
	   public boolean selectDropDownOption(WebElement pElementToBeClicked,String pDropdownOptionsXpath, String pOptionToSelect) {
			boolean isSuccessful=false;
			List<WebElement> list_of_options = new ArrayList<WebElement>();
			 AutomationLogger.info("Clicking on button "+pElementToBeClicked);
			if(Click(pElementToBeClicked)) {
				if(pDropdownOptionsXpath.isEmpty()) {
					 list_of_options = pElementToBeClicked.findElements(By.tagName("option"));
				}else {
					 list_of_options = driver.findElements(By.xpath(pDropdownOptionsXpath));
				}	
				AutomationLogger.info("Selecting a option from Dropdown "+pDropdownOptionsXpath);
				for(WebElement element: list_of_options) {
					System.out.println(element.getText().trim());
					if(element.getText().trim().equalsIgnoreCase(pOptionToSelect)) {
						isSuccessful = Click(element);
						Click(pElementToBeClicked);
						break;
					}
				}
			}
			return isSuccessful;
		}
	   public  boolean selectDropDownOptions( WebElement pElementToBeClicked,String pDropdownOptionsXpath, String[] pOptionsToSelect) {
		   try {
			   boolean isSuccessful=false;
			   List<WebElement> list_of_options = new ArrayList<WebElement>();
			   AutomationLogger.info("Clicking on button "+pElementToBeClicked);
			   if(Click(pElementToBeClicked)) {
				   AutomationLogger.info("Selecting a option from Dropdown "+pDropdownOptionsXpath);
				   for(String pOptionToSelect: pOptionsToSelect) {
					   if(pDropdownOptionsXpath.isEmpty()) {
						   list_of_options = pElementToBeClicked.findElements(By.tagName("option"));
					   }else {
						   list_of_options = driver.findElements(By.xpath(pDropdownOptionsXpath));
					   }	
					   for(WebElement element: list_of_options) {
						   if(element.getText().equalsIgnoreCase(pOptionToSelect.trim())) {
							   isSuccessful = Click(element);
							   break;
						   }
					   }
				   }
			   }
			   
			   return Type(pElementToBeClicked.findElement(By.tagName("input")),Keys.ESCAPE) && isSuccessful;
		   }catch(Exception ex) {
				return false;
			}
		}
	   
	   public boolean waitForElementsToBeFound( String pXpath) {
		   boolean isFound = false;
		   int counter = 0;
		   while(counter<100) {
			   System.out.println(counter);
			   if(driver.findElements(By.xpath(pXpath))!=null) {
				   isFound = true;
				   break;
			   }
			   counter++;
		   }
		   return isFound;
	   }
	   public boolean waitForAjaxToBeCompleted(WebDriver pWebDriver) {
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
	   
	   public boolean waitForElementToBeDisappeared(String pElementToBeDisappeared) {			
		   wait=new WebDriverWait(driver, GLOBAL_WAIT_COUNT);
		   boolean isElementVisible = false;
		   try {
			   isElementVisible=wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(pElementToBeDisappeared)));
		   }catch(Exception ex) {
			   AutomationLogger.error("Element did not disappear.  Wait max limit is "+GLOBAL_WAIT_COUNT+" seconds");
			   AutomationLogger.error(ex.getMessage());
		   }
		   return isElementVisible;

	   }
	   public  String getHeading(String pHeading) {
		   return driver.findElement(By.tagName(pHeading)).getText();
		   
	   }
	   
	   public boolean waitForElementToVisibleAfterRegularIntervals( WebElement pEelement, String pXpathToAppend, long pWaitIntervalInSeconds, int pTotalAttempts) {
		   
		   boolean displayed = false;
		   int counter = 0;
		   pXpathToAppend = "/descendant::span[text()='FAILED']";
		    while (counter<pTotalAttempts) {
		    	try {
		    	WebElement elementFund = pEelement.findElement(By.xpath(pXpathToAppend));
		        if (elementFund!=null) {
		            // Element is found so set the boolean as true
		            displayed = isElementVisible(elementFund);
		        } 
		    	}catch(Exception ex) {
		    		try {
		            	RefreshPage();
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
	   public void Wait(long pTimeInSeconds) {
		   try {
			   AutomationLogger.info("Static wait of "+pTimeInSeconds+" seconds");
			   Thread.sleep(pTimeInSeconds*1000);
		   }catch(Exception ex) {
			   AutomationLogger.error("Static wait failed");
		   }
	   }
	   //Drag and Drop Method
	   public boolean dragAndDrop(WebElement pFrom, WebElement pTo) {
		   boolean result = true;
		   try {
			   Actions action=new Actions(driver);
			   action.dragAndDrop(pFrom, pTo).build().perform();
			   Wait(5);
		   }catch(Exception ex) {
			   AutomationLogger.error("Drag and Drop failed for "+pFrom);
			   result = false;
		   }
		   return result;
	   }
	   
	   public void ScrollDownByPixels(String pPixels) {
		   try {
			   JavascriptExecutor js = (JavascriptExecutor) driver;
			   AutomationLogger.info("Scrolling down");		
			   js.executeScript("window.scrollBy(0,"+pPixels+")");
		   }catch(Exception ex) {
			   AutomationLogger.error("Error in Scrolling down");
		   }
		   
	   }
	   public boolean Clear(WebElement pInputField) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(driver, GLOBAL_WAIT_COUNT);
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
	   public boolean isOptionSelected( WebElement pDropdown, String pOption) {
		   boolean lOptionValue = false;
		   try {
			   if(isElementVisible(pDropdown)) {
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
	   
	   public boolean doesElementContainsAttribute(String pElementXpath, String pAttribute, String pValue) {
			boolean isSuccessfull=false;
			wait=new WebDriverWait(driver, GLOBAL_WAIT_COUNT);
			try {
				AutomationLogger.info("Waiting for the visibility of element ->"+pElementXpath);
				if(wait.until(ExpectedConditions.attributeContains(By.xpath(pElementXpath), pAttribute, pValue))) {
					AutomationLogger.info("Atrribute contains the value..");
					isSuccessfull=true;
				}
				
			}catch(Exception ex) {
				AutomationLogger.error("Element is not visible.. ");
				AutomationLogger.error(ex.getMessage());
			}
			return isSuccessfull;
		}
}
