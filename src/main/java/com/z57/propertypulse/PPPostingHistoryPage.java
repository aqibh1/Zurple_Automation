/**
 * 
 */
package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
		setPageObject(pWebDriver, this);
	}
	public boolean isPostingHistoryPage() {
		return actionHelper.waitForElementToBeLocated(postHistory_title, 30);
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
		List<WebElement> list_of_rows = actionHelper.getListOfElementByXpath(pElement);
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
			
			List<WebElement> list_of_rows = actionHelper.getListOfElementByXpath(historyTable_rows);

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
				actionHelper.Wait(15);
				actionHelper.RefreshPage();
			}
		}
		return verificationSuccess;
	}
}
