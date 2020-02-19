package com.zurple.backoffice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOAgents extends Page{

	@FindBy(className="col-sm-4")
	WebElement manage_agents_label;
	
//	@FindBy(className="sorting_1")
//	String count_agents = "sorting_1";
	
	@FindBy(xpath="//div[@id='leads_by_status_container']/descendant::a")
	WebElement list_of_agents;
	String agents_count_xpath = "//div[@id='leads_by_status_container']/descendant::a";
	
	public ZBOAgents() {
		
	}
	
	public ZBOAgents(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public String verifyPageTitle(){
		ActionHelper.waitForElementToBeVisible(driver, manage_agents_label, 30);
		return ActionHelper.getText(driver, manage_agents_label).trim();
	}
	
	public boolean verifyAgentsCount(int pExpectedElements) {
		boolean isSuccessfull = false;
		if(ActionHelper.waitForElementToBeVisible(driver,list_of_agents , 30)) {
			List<WebElement> agentsList = ActionHelper.getListOfElementByXpath(driver, agents_count_xpath);
			System.out.println("This is list size==="+agentsList.size());
			isSuccessfull = pExpectedElements==agentsList.size();
		}
		return isSuccessfull;
	}
}
