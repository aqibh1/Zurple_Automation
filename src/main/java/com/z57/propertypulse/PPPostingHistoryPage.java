/**
 * 
 */
package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class PPPostingHistoryPage extends Page{
	
	String postHistory_title="//table[@id='history_table']/descendant::h2[text()='Post History ']";
	String historyTable_rows = "//table[@id='history_table']/descendant::tbody/tr";
	
	public PPPostingHistoryPage() {
		// TODO Auto-generated constructor stub
	}
	public PPPostingHistoryPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isPostingHistoryPage() {
		return ActionHelper.waitForElementToBeLocated(driver, postHistory_title, 30);
	}
	
	public boolean isPostCompleted(String pStatus) {
		return isPostSuccessful(historyTable_rows, pStatus, "Completed");
	}
	
	public boolean isYoutubePostCompleted(String pStatus, String pPlatform) {
//		return isYoutubePostSuccessful(historyTable_rows, pStatus, "Completed",pPlatform);
		return verifyVideoPosted(10, 20, pStatus, pPlatform, "Completed");//(historyTable_rows, pStatus, "Completed",pPlatform);
	}
	private boolean isPostSuccessful(String pElement,String pPostTitle, String pPostStatusToVerify) {
		boolean post_found = false;
		boolean status_verified= false;
		List<WebElement> list_of_rows = ActionHelper.getListOfElementByXpath(driver, pElement);
		for(WebElement element: list_of_rows) {
			List<WebElement> list_of_small = element.findElements(By.tagName("small"));
			List<WebElement> list_of_span = element.findElements(By.tagName("span"));
			
			//Small tag will look for post title
			for(WebElement smallElement: list_of_small) {
				if(smallElement.getText().trim().equalsIgnoreCase(pPostTitle.trim())) {
					post_found = true;
					break;
				}	
			}
			//Span tag will look for post status: Completed
			if(post_found) {

				for(WebElement spanElement: list_of_span) {
					if(spanElement.getText().equalsIgnoreCase(pPostStatusToVerify)) {
						status_verified = true;
						break;
					}
				}


			}
			if(post_found && status_verified) {//Success case
				break;
			}else if(post_found && !status_verified) {//if post is not completed
				break;
			}
			else {
				post_found = false;
				status_verified= false;
			}
		}
		return (post_found && status_verified);

	}
	
	private boolean isYoutubePostSuccessful(String pElement,String pPostTitle, String pPostStatusToVerify, String pPlatform) {
		boolean post_found = false;
		boolean status_verified= false;
		boolean image_verified = false;
		int lArrayIndexCounter = 0;

		List<WebElement> list_of_rows = ActionHelper.getListOfElementByXpath(driver, pElement);
		for(WebElement element: list_of_rows) {
			List<WebElement> list_of_small = element.findElements(By.tagName("small"));

			//Small tag will look for post title
			for(WebElement smallElement: list_of_small) {
				if(smallElement.getText().trim().contains(pPostTitle.trim())) {
					post_found = true;
					break;
				}	
			}
			//Span tag will look for post status: Completed
			if(post_found) {
				status_verified = verifyPostsStatus(lArrayIndexCounter, "Completed", 20, 20,pPostTitle,pPlatform);
			}
			if(status_verified) {
				image_verified = verifyImageIcon(lArrayIndexCounter);	
			}
			lArrayIndexCounter++;
			if(!post_found) {
				AutomationLogger.error("Post is not found "+pPostTitle);
			}else if(!status_verified) {
				AutomationLogger.error("Status of video is not completed ");
			}else if(!image_verified) {
				AutomationLogger.error("No image icon found on Posting Hiistory page.");
			}else if(post_found || status_verified || image_verified) {
				break;
			}
			
		}

		return (post_found && status_verified && image_verified);

	}
	
	private boolean verifyPostsStatus(int pArrayIndex,String pStatus, int pTotalAttempts, int pInterval, String pTitle,String pPlatform) {
		int counter = 0;
		boolean status_verified = false;
		
		while(counter<pTotalAttempts && !status_verified) {
			List<WebElement> list_of_rows = ActionHelper.getListOfElementByXpath(driver, historyTable_rows);
			List<WebElement> list_of_span = list_of_rows.get(pArrayIndex).findElements(By.tagName("span"));	
				
			for(WebElement spanElement: list_of_span) {
				if(spanElement.getText().equalsIgnoreCase(pStatus)) {
					status_verified = true;
					break;
				}
			}
			if(status_verified) {
				break;
			}else {
				ActionHelper.RefreshPage(driver);
				ActionHelper.staticWait(15);
				counter++;
				list_of_rows = ActionHelper.getListOfElementByXpath(driver, historyTable_rows);
				int lCount = 0;
				boolean post_found=false;
				boolean isPlatformYoutube = false;
				for(WebElement element: list_of_rows) {
					post_found=false;
					isPlatformYoutube = false;
					List<WebElement> list_of_small = element.findElements(By.tagName("small"));

					//Small tag will look for post title
					for(WebElement smallElement: list_of_small) {
						
						if(smallElement.getText().trim().contains(pTitle.trim())) {
							post_found = true;
						}else if(smallElement.getText().trim().contains(pPlatform)) {
							isPlatformYoutube = true;
						}
					}
					if(post_found && isPlatformYoutube) {
						pArrayIndex = lCount;
						break;
					}
					lCount++;
				}
			}
		}
		return status_verified;
	}
	
	private boolean verifyVideoPosted(int pTotalAttempts, int pInterval, String pTitle,String pPlatform,String pStatus) {
		int counter = 0;
		boolean status_verified = false;
		boolean post_found=false;
		boolean isPlatformYoutube = false;
		boolean verificationSuccess = false;
		boolean isPending = false;

		while(counter<pTotalAttempts && !verificationSuccess) {
			
			isPending = false;
			status_verified = false;
			post_found=false;
			isPlatformYoutube = false;
			
			List<WebElement> list_of_rows = ActionHelper.getListOfElementByXpath(driver, historyTable_rows);

			for(WebElement element:list_of_rows) {
				List<WebElement> list_of_span = element.findElements(By.tagName("span"));
				List<WebElement> list_of_small = element.findElements(By.tagName("small"));

				//Verification of Post Title and Platform
				for(WebElement smallElement: list_of_small) {
					if(smallElement.getText().trim().contains(pTitle.trim())) {
						post_found = true;
					}else if(smallElement.getText().trim().contains(pPlatform)) {
						isPlatformYoutube = true;
					}else if(post_found && isPlatformYoutube) {
						break;
					}
					
				}

				if(post_found && isPlatformYoutube) {
					//Verification of status i.e Complete
					for(WebElement spanElement: list_of_span) {
						if(spanElement.getText().equalsIgnoreCase(pStatus)) {
							status_verified = true;
						}else if(spanElement.getText().equalsIgnoreCase("Pending")) {
							isPending = true;
							break;
						}
					}
				}
				if(!isPending) {
					//Verification of Image
					if(status_verified) {
						List<WebElement> list_of_img = element.findElements(By.tagName("img"));
						for(WebElement imgElement: list_of_img) {
							if(!imgElement.getAttribute("src").contains("hqdefault.jpg")) {
								verificationSuccess = true;
								break;
							}
						}
					}
				}else {
					break;
				}
				if(verificationSuccess) {
					break;
				}
			}
			if(!verificationSuccess && isPending) {
				ActionHelper.staticWait(15);
				ActionHelper.RefreshPage(driver);
			}
		}
		return verificationSuccess;
	}
	
	private boolean verifyImageIcon(int pArrayIndex) {
		boolean status_verified = false;
		List<WebElement> list_of_rows = ActionHelper.getListOfElementByXpath(driver, historyTable_rows);
		List<WebElement> list_of_img = list_of_rows.get(pArrayIndex).findElements(By.tagName("img"));	
		for(WebElement imgElement: list_of_img) {
			if(!imgElement.getAttribute("src").contains("hqdefault.jpg")) {
				status_verified = true;
				break;
			}
		}
		return status_verified;
	}
}
