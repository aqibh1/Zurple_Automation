/**
 * 
 */
package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

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
		PageFactory.initElements(driver,this);
	}
	public boolean isPostingHistoryPage() {
		return actionHelper.waitForElementToBeLocated(postHistory_title, 30);
	}
	
	public boolean isPostCompleted(String pStatus) {
		return isPostSuccessful(historyTable_rows, pStatus, "Completed");
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

}
