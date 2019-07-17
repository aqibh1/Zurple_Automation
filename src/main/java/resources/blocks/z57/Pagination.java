package resources.blocks.z57;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.blocks.AbstractBlock;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

public class Pagination extends AbstractBlock{
	WebDriver driver;
	private ActionHelper actionHelper;
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
	
	@FindBy(id="ic_search_results_info")
	WebElement searchResultsInfo;
	
	@FindBy(xpath="//ul[@class='pagination']")
	WebElement pagination_exists;
	
	public Pagination(WebDriver pWebDriver){
		setPageObject(pWebDriver, this);
	}
	
	public boolean clicOnNext() {
		return actionHelper.Click(next_button);
	}
	
	public boolean clickOnPrevious() {
		return actionHelper.Click(previous_button);
	}
	
	public boolean clickOnPageNumber(String pPageNumber) {
		return actionHelper.Click(actionHelper.getDynamicElement(pagination_button, pPageNumber));
	}
	
	public boolean verifyAllPaginationButtonsWorking() {
		Random randomGenerator = new Random();
		int lAttemptCount=0;
		boolean clickOnNum = false;
		boolean clickOnPrev = false;
		boolean clickOnNext = false;
		int currentPageOld =0;
		int currentPageNew=0;
		List<WebElement> list_pagination_buttons = actionHelper.getListOfElementByXpath(pagination_buttons_xpath);
		int lCurrentPage = Integer.parseInt(getCurrentPage(list_pagination_buttons));
		int totalPages = getTotalPages(list_pagination_buttons);
		try {
			if(totalPages>1) {
				do {
					int gotoPage = randomGenerator.nextInt(totalPages) + 1;//(int)(Math.random() * (totalPages));
					System.out.println("Go to page "+gotoPage);
					if(lCurrentPage!=gotoPage) {
						clickOnPageNumber(String.valueOf(gotoPage));
						System.out.println("Clicked on page number was success.. "+gotoPage);
						clickOnNum = Integer.parseInt(getCurrentPage(actionHelper.getListOfElementByXpath(pagination_buttons_xpath)))==gotoPage;
						if(clickOnNum) {
							currentPageOld = Integer.parseInt(getCurrentPage(actionHelper.getListOfElementByXpath(pagination_buttons_xpath)));
							if(clickOnPrevious()) {
								System.out.println("Clicked on previous button was success.. ");
								currentPageNew = Integer.parseInt(getCurrentPage(actionHelper.getListOfElementByXpath(pagination_buttons_xpath)));
								clickOnPrev = currentPageOld!=currentPageNew;
							}
							currentPageOld = Integer.parseInt(getCurrentPage(actionHelper.getListOfElementByXpath(pagination_buttons_xpath)));
							if(clicOnNext()) {
								System.out.println("Clicked on NEXT button was success.. ");
								currentPageNew = Integer.parseInt(getCurrentPage(actionHelper.getListOfElementByXpath(pagination_buttons_xpath)));
								clickOnNext = currentPageOld!=currentPageNew;
							}
						}
					}
					lAttemptCount++;
				}while(!clickOnNum && lAttemptCount<3);

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
		return actionHelper.Click(actionHelper.getDynamicElement(pagination_button_rhs, pPageNumber));
		
	}

	public boolean isPaginationAvailable() {
		return !actionHelper.isElementVisible(next_disabled_button);
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
		List<WebElement> list_pagination_buttons = actionHelper.getListOfElementByXpath(pagination_buttons_xpath_rhs);
		int lCurrentPage = Integer.parseInt(getCurrentPageRHS(list_pagination_buttons));
		int totalPages = getTotalPagesRHS(list_pagination_buttons);
		
		if(totalPages>1) {
			do {
				int gotoPage = (int)(Math.random() * (totalPages));
				if(lCurrentPage!=gotoPage) {
					clickOnPageNumberRHS(String.valueOf(gotoPage));
					clickOnNum = Integer.parseInt(getCurrentPageRHS(actionHelper.getListOfElementByXpath(pagination_buttons_xpath_rhs)))==gotoPage;
					if(clickOnNum) {
						currentPageOld = Integer.parseInt(getCurrentPageRHS(actionHelper.getListOfElementByXpath(pagination_buttons_xpath_rhs)));
						if(clickOnPreviousRHS()) {
							currentPageNew = Integer.parseInt(getCurrentPageRHS(actionHelper.getListOfElementByXpath(pagination_buttons_xpath_rhs)));
							clickOnPrev = currentPageOld!=currentPageNew;
						}
						currentPageOld = Integer.parseInt(getCurrentPageRHS(actionHelper.getListOfElementByXpath(pagination_buttons_xpath_rhs)));
						if(clicOnNextRHS()) {
							currentPageNew = Integer.parseInt(getCurrentPageRHS(actionHelper.getListOfElementByXpath(pagination_buttons_xpath_rhs)));
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
		return actionHelper.Click(next_button_rhs);
	}

	private boolean clickOnPreviousRHS() {
		return actionHelper.Click(previous_button_rhs);
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
	public boolean isPaginationAvailableRHS() {
		boolean isPaginationApplicable = false;
		if(actionHelper.waitForElementToBeVisible(searchResultsInfo, 30)) {
			String enteriesCount=searchResultsInfo.getText().split("of")[1].split("enteries")[0].trim();
			int enteriesCountInt=Integer.parseInt(enteriesCount.split(" ")[0].trim());
			if(enteriesCountInt>10) {
				isPaginationApplicable = true;
			}else {
				AutomationLogger.info("Pagination is not aplicable on this page..");
			}
		}
		return isPaginationApplicable;
	}
	
	public boolean isPaginationExists() {
		return actionHelper.waitForElementToBeVisible(pagination_exists, 30);
	}
	
}
