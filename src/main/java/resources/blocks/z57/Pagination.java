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
	
	@FindBy(xpath="//div[@id='ic_search_results_paginate']/descendant::a[text()='Previous']")
	WebElement previous_button_rhs;
	
	@FindBy(xpath="//ul[@class='pagination']/descendant::li/a[text()='Next']")
	WebElement next_button;
	
	@FindBy(xpath="//div[@id='ic_search_results_paginate']/descendant::a[text()='Next']")
	WebElement next_button_rhs;
	
	String pagination_button = "//ul[@class='pagination']/descendant::li/a[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	String pagination_button_rhs = "//div[@id='ic_search_results_paginate']/descendant::a[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//ul[@class='pagination']/descendant::li/a")
	WebElement list_of_all_buttons;
	String pagination_buttons_xpath="//ul[@class='pagination']/descendant::li/a";
	String pagination_buttons_xpath_rhs ="//div[@id='ic_search_results_paginate']/descendant::a";
	
	@FindBy(xpath="//ul[@class='pagination']/descendant::li[@class='disabled']/a[text()='Next']")
	WebElement next_disabled_button;
	
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
		int currentPageOld =0;
		int currentPageNew=0;
		List<WebElement> list_pagination_buttons = ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath);
		int lCurrentPage = Integer.parseInt(getCurrentPage(list_pagination_buttons));
		int totalPages = getTotalPages(list_pagination_buttons);
		try {
			if(totalPages>1) {
				do {
					int gotoPage = (int)(Math.random() * (totalPages));
					if(lCurrentPage!=gotoPage) {
						clickOnPageNumberRHS(String.valueOf(gotoPage));
						clickOnNum = Integer.parseInt(getCurrentPage(ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath)))==gotoPage;
						if(clickOnNum) {
							currentPageOld = Integer.parseInt(getCurrentPage(ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath)));
							if(clickOnPrevious()) {
								currentPageNew = Integer.parseInt(getCurrentPage(ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath)));
								clickOnPrev = currentPageOld!=currentPageNew;
							}
							currentPageOld = Integer.parseInt(getCurrentPage(ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath)));
							if(clicOnNext()) {
								currentPageNew = Integer.parseInt(getCurrentPage(ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath)));
								clickOnNext = currentPageOld!=currentPageNew;
							}
						}
					}
				}while(!clickOnNum);

			}else {
				AutomationLogger.info("Only 1 page exists for pagination.");
				clickOnNum = true;
				clickOnPrev = true;
				clickOnNext = true;
			}
		}catch(Exception ex) {
			clickOnNum = false;
			clickOnPrev = false;
			clickOnNext = false;
			AutomationLogger.error(ex.getMessage());

		}
		return (clickOnNum && clickOnPrev && clickOnNext);
	}
	private boolean clickOnPageNumberRHS(String pPageNumber) {
		return ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, pagination_button_rhs, pPageNumber));
		
	}

	public boolean isPaginationAvailable() {
		return !ActionHelper.isElementVisible(driver, next_disabled_button);
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
	
	//FOR RECENT HOME SALES
	public boolean verifyAllPaginationButtonsWorkingRHS() {
		int lAttemptCount=0;
		boolean clickOnNum = false;
		boolean clickOnPrev = false;
		boolean clickOnNext = false;
		int currentPageOld =0;
		int currentPageNew=0;
		List<WebElement> list_pagination_buttons = ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath_rhs);
		int lCurrentPage = Integer.parseInt(getCurrentPageRHS(list_pagination_buttons));
		int totalPages = getTotalPagesRHS(list_pagination_buttons);
		
		if(totalPages>1) {
			do {
				int gotoPage = (int)(Math.random() * (totalPages));
				if(lCurrentPage!=gotoPage) {
					clickOnPageNumberRHS(String.valueOf(gotoPage));
					clickOnNum = Integer.parseInt(getCurrentPageRHS(ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath_rhs)))==gotoPage;
					if(clickOnNum) {
						currentPageOld = Integer.parseInt(getCurrentPageRHS(ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath_rhs)));
						if(clickOnPreviousRHS()) {
							currentPageNew = Integer.parseInt(getCurrentPageRHS(ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath_rhs)));
							clickOnPrev = currentPageOld!=currentPageNew;
						}
						currentPageOld = Integer.parseInt(getCurrentPageRHS(ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath_rhs)));
						if(clicOnNextRHS()) {
							currentPageNew = Integer.parseInt(getCurrentPageRHS(ActionHelper.getListOfElementByXpath(driver, pagination_buttons_xpath_rhs)));
							clickOnNext = currentPageOld!=currentPageNew;
						}
					}
				}
				lAttemptCount++;
			}while(!clickOnNum & lAttemptCount<3);

		}else {
			AutomationLogger.info("Only 1 page exists for pagination.");
			clickOnNum = true;
			clickOnPrev = true;
			clickOnNext = true;
		}

		return (clickOnNum && clickOnPrev && clickOnNext);
	}

	private boolean clicOnNextRHS() {
		return ActionHelper.Click(driver, next_button_rhs);
	}

	private boolean clickOnPreviousRHS() {
		return ActionHelper.Click(driver, previous_button_rhs);
	}
	
	private String getCurrentPageRHS(List<WebElement> pElementList) {
		String lCurrentPage="";
		for(WebElement element: pElementList) {
			if(element.getAttribute("class").contains("paginate_button current") &&
					!element.getText().equalsIgnoreCase("Previous") && !element.getText().equalsIgnoreCase("Next")) {
				lCurrentPage=element.getText();
				break;
			}
		}
		return lCurrentPage;	
	}
	private int getTotalPagesRHS(List<WebElement> pElementList) {
		int lTotalPages = 0;
		for(WebElement element: pElementList) {
			if(!element.getText().equalsIgnoreCase("Previous") && !element.getText().equalsIgnoreCase("Next")) {
				lTotalPages++;
			}
		}
		return lTotalPages;	
		
	}
	
}
