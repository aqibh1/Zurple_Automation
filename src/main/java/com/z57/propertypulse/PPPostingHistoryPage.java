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
	
	private boolean isPostSuccessful(String pElement,String pPostTitle, String pPostStatusToVerify) {
		boolean post_found = false;
		boolean status_verified= false;
		List<WebElement> list_of_rows = ActionHelper.getListOfElementByXpath(driver, pElement);
		for(WebElement element: list_of_rows) {
			List<WebElement> list_of_td = element.findElements(By.tagName("td"));
			List<WebElement> list_of_small = element.findElements(By.tagName("small"));
			for(WebElement td: list_of_td) {
				if(td.findElement(By.tagName("small"))!=null && td.findElement(By.tagName("small")).getText().trim().equalsIgnoreCase(pPostTitle)) {
					post_found = true;
				}
				if(td.findElement(By.tagName("span"))!=null && td.findElement(By.tagName("span")).getText().equalsIgnoreCase(pPostStatusToVerify)) {
					status_verified = true;
				}
			}
			if(post_found && status_verified) {
				break;
			}else {
				post_found = false;
				status_verified= false;
			}
		}
		return (post_found && status_verified);

	}

}
