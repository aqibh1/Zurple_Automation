package com.z57.site.v2;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

public class WhatsNearbyPage extends Page{


	@FindBy(xpath="//h1[@class='entry-title title_prop']")
	WebElement pageTitle;
	
	@FindBy(xpath="//div[@id='community_search_results_box']/descendant::h2")
	WebElement no_search_result_header;
	
	@FindBy(xpath="//div[@id='z57_poi_table_info']")
	WebElement table_entries_info;
	
	@FindBy(xpath="//a[@class='paginate_button next' and @id='z57_poi_table_next']")
	WebElement next_button;
	
	@FindBy(xpath="//a[@class='paginate_button previous' and @id='z57_poi_table_previous']")
	WebElement previous_button;
	
	@FindBy(xpath="//div[@id='z57_poi_table_paginate']/descendant::a[@data-dt-idx='1']")
	WebElement pageNumberOne;
	
	@FindBy(xpath="//div[@id='z57_poi_table_paginate']/descendant::a[@class='paginate_button current']")
	WebElement currentPageNumber;
	
	String list_of_point_of_intrest="//table[@id='z57_poi_table']/descendant::tbody/tr";
	String pin_popup_info_table ="//table[@class='table table-condensed table-striped']/descendant::tbody/tr";
	String list_of_pages_pagination="//div[@id='z57_poi_table_paginate']/span/a[@data-dt-idx='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";

	private ActionHelper actionHelper;

	public WhatsNearbyPage(WebDriver pWebDriver) {
		driver=pWebDriver;
		actionHelper = new ActionHelper(driver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean isPointOfIntrestsPage() {
		boolean result = actionHelper.getText(pageTitle).equalsIgnoreCase(FrameworkConstants.PointOfIntrestsPageTitle)?true:false;
		return result;
	}

	public boolean isSearchHasSomeResults() {
		return actionHelper.getText(no_search_result_header).equalsIgnoreCase(FrameworkConstants.NoSearchResult);
	}
	
	public boolean isResultsCorrect(String pState) {
		boolean result = true;

		List<WebElement> list_of_elements = driver.findElements(By.xpath(list_of_point_of_intrest));
		for(WebElement element: list_of_elements) {
			String lPlaceName = element.findElement(By.tagName("a")).getText();
			actionHelper.Click(element.findElement(By.tagName("a")));
			List<WebElement> table_contents_list = driver.findElements(By.xpath(pin_popup_info_table));
			for(WebElement table_element: table_contents_list) {
				actionHelper.waitForElementToBeVisible(table_element, 10);
				String element_text = table_element.getText();
				if(element_text.contains("Address:")) {
					String pin_state = element_text.split(" ")[element_text.split(" ").length-1];

					System.out.println(pin_state);
					if(!pState.equalsIgnoreCase(pin_state)) {
						AutomationLogger.error("STATE Mismatched");
						AutomationLogger.error("POI :: "+lPlaceName);
						AutomationLogger.error("State Expected :: "+pState);
						AutomationLogger.error("Actual State :: "+pin_state);
						return false;

					}
				}
			}
		}
		return result;
	}
	
	public boolean verifyPagination() {
		boolean flag = false;
		boolean forwardPagination=false,backwardsPagination=false;
		String lCurrent_text = table_entries_info.getText();
		String lPrevious_Text = table_entries_info.getText();
		String total_count = lCurrent_text.split("of ")[1].split(" ")[0];
		int total_page_count = (Integer.parseInt(total_count))/10;
		AutomationLogger.info("Checking if pages exists");
		if(actionHelper.waitForElementToBeVisible(pageNumberOne, 30)) {
			if(total_page_count!=1) {
				for(int counter=1;counter<=total_page_count;counter++) {
//					if(counter%2==0) {
//						if(counter==Integer.parseInt(actionHelper.getText(currentPageNumber))) {
//							counter++;
//							clickOnPageNumber(list_of_pages_pagination, String.valueOf(counter));
//						}
//					}else {
//						clickOnNextButtonPage();
//						counter++;
//					}
					if(!flag) {
						Random randomGenerator = new Random();
						int randomPage = randomGenerator.nextInt(total_page_count) + 1;
						if(randomPage>5) {
							randomPage = randomPage/5;
						}
						if(randomPage==1) {
							randomPage++;
						}
						if(randomPage==total_page_count) {
							randomPage--;
						}
						clickOnPageNumber(list_of_pages_pagination, String.valueOf(randomPage));
						counter = randomPage;
						flag = true;
					}else {
						clickOnNextButtonPage();
					}
					lPrevious_Text =lCurrent_text;
					lCurrent_text = table_entries_info.getText();

					forwardPagination = !lPrevious_Text.equalsIgnoreCase(lCurrent_text);
					if(!forwardPagination) {
						break;
					}
				}

				while(actionHelper.isElementVisible(previous_button)) {
					actionHelper.Click(previous_button);
					lPrevious_Text =lCurrent_text;
					lCurrent_text = table_entries_info.getText();
					backwardsPagination=lCurrent_text.equalsIgnoreCase(lPrevious_Text)?false:true;
					if(!backwardsPagination) {
						break;
					}
				}
			}else {
				AutomationLogger.info("Only one page exists");
				forwardPagination=true;
				backwardsPagination=true;
			}
		}else {
			AutomationLogger.error("Pagination is not visible");
		}

		return (forwardPagination && backwardsPagination);
	}
	
		private boolean clickOnNextButtonPage() {
			return actionHelper.Click(next_button);
		}
		
		private boolean clickOnPageNumber(String pElementXpath, String pPageNumber) {
			return actionHelper.Click(actionHelper.getDynamicElement(pElementXpath, pPageNumber));
		}
	@Override
	public WebElement getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement getBrand() {
		// TODO Auto-generated method stub
		return null;
	}



}
