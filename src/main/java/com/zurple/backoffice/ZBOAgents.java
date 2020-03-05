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
	
	@FindBy(xpath="//div[@id='leads_by_status_container']/descendant::a")
	WebElement list_of_agents;
	String agents_count_xpath = "//div[@id='leads_by_status_container']/descendant::a";
	
	@FindBy(id="first_name")
	WebElement agent_first_name;
	
	@FindBy(id="last_name")
	WebElement agent_last_name;
	
	@FindBy(id="email")
	WebElement agent_email;
	
	@FindBy(id="password")
	WebElement agent_password;
	
	@FindBy(id="password_confirm")
	WebElement agent_confirmPassword;
	
	@FindBy(id="alias_email")
	WebElement agent_aliasEmail;
	
	@FindBy(id="add-agent-button")
	WebElement add_agent;
	
	@FindBy(className="ui-button-text")
	// WebElement confirmAdd;
	String confirmAdd = "ui-button-text-only";
	
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
	
	public boolean addAgent() {
		ActionHelper.Type(driver, agent_first_name, "Automated");
		ActionHelper.Type(driver, agent_last_name, "Agent");
		ActionHelper.Type(driver, agent_email, "automated_agent@mailinator.com");
		ActionHelper.Type(driver, agent_password, "12345");
		ActionHelper.Type(driver, agent_confirmPassword, "12345");
		// ActionHelper.Type(driver, agent_aliasEmail, "automated_agent");
		ActionHelper.waitForElementToBeVisible(driver, add_agent , 30);
		ActionHelper.Click(driver, add_agent);
		ActionHelper.ClickByIndex(driver, confirmAdd, 0);
		return true;
	}
}
