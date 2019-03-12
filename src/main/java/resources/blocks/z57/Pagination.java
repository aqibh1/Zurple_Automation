package resources.blocks.z57;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

public class Pagination {
	WebDriver driver;
	
	@FindBy(xpath="//ul[@class='pagination']/descendant::li/a[text()='Prev']")
	WebElement previous_button;
	
	@FindBy(xpath="//ul[@class='pagination']/descendant::li/a[text()='Next']")
	WebElement next_button;
	
	String pagination_button = "//ul[@class='pagination']/descendant::li/a[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//ul[@class='pagination']/descendant::li/a")
	WebElement list_of_all_buttons;
	String pagination_buttons_xpath="//ul[@class='pagination']/descendant::li/a";
	
	public Pagination(WebDriver pWebDriver){
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean clicOnNext() {
		return ActionHelper.Click(driver, next_button);
	}
	
	public boolean clickOnPrevious() {
		return ActionHelper.Click(driver, previous_button);
	}
	
	public boolean clickOnPageNumber(String pPageNumber) {
		return ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, pagination_button, pPageNumber));
	}
	
	public boolean verifyAllPaginationButtonsWorking() {
		boolean clickOnNum = false;
		boolean clickOnPrev = false;
		boolean clickOnNext = false;
		List<WebElement> list_pagination_buttons = ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath);
		int lCurrentPage = Integer.parseInt(getCurrentPage(list_pagination_buttons));
		int totalPages = getTotalPages(list_pagination_buttons);
		
		if(totalPages>1) {
			do {
				int gotoPage = (int)(Math.random() * (totalPages));
				if(lCurrentPage!=gotoPage) {
					clickOnPageNumber(String.valueOf(gotoPage));
					clickOnNum = Integer.parseInt(getCurrentPage(ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath)))==gotoPage;
					if(clickOnNum) {
						clickOnPrev = clickOnPrevious();
						clickOnNext =clicOnNext();
					}
				}
			}while(!clickOnNum);

		}else {
			AutomationLogger.info("Only 1 page exists for pagination.");
			clickOnNum = true;
			clickOnPrev = true;
			clickOnNext = true;
		}

		return (clickOnNum && clickOnPrev && clickOnNext);
	}
	
	private String getCurrentPage(List<WebElement> pElementList) {
		String lCurrentPage="";
		for(WebElement element: pElementList) {
			if(element.getAttribute("class").contains("pagination-link disabled") &&
					!element.getText().equalsIgnoreCase("Prev") && !element.getText().equalsIgnoreCase("Next")) {
				lCurrentPage=element.getText();
				break;
			}
		}
		return lCurrentPage;	
	}
	private int getTotalPages(List<WebElement> pElementList) {
		int lTotalPages = 0;
		for(WebElement element: pElementList) {
			if(!element.getText().equalsIgnoreCase("Prev") && !element.getText().equalsIgnoreCase("Next")) {
				lTotalPages++;
			}
		}
		return lTotalPages;	
		
	}

}
