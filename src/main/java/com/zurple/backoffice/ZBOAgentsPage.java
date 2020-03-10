package com.zurple.backoffice;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOAgentsPage extends Page{

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
	
	@FindBy(xpath="//div[contains(@class,'ui-dialog-buttonset')]//span[@class='ui-button-text']")
	WebElement confirmAdd;
	
	Calendar calendar = Calendar.getInstance();
	
	public ZBOAgentsPage() {
		
	}
	
	public ZBOAgentsPage(WebDriver pWebDriver) {
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
	
	public boolean typeAgentFirstName(String pAgentName) {
		return ActionHelper.Type(driver, agent_first_name, pAgentName);
	}
	public boolean typeAgentLastName(String pAgentName) {
		return ActionHelper.Type(driver, agent_last_name, pAgentName);
	}
	public boolean typeAgentEmail(String pAgentEmail) {
		return ActionHelper.Type(driver, agent_email, pAgentEmail);
	}
	public boolean typeAgentPassword(String pAgentPassword) {
		return ActionHelper.Type(driver, agent_password, pAgentPassword);
	}
	public boolean typeAgentConfirmPassword(String pAgentConfirmPassword) {
		return ActionHelper.Type(driver, agent_first_name, pAgentConfirmPassword);
	}
	public boolean addAgentButton(String pAddAgent) {
		return ActionHelper.Click(driver, add_agent);
	}
	public boolean confirmAgent(String pConfirmAgent) {
		return ActionHelper.Type(driver, agent_aliasEmail, pConfirmAgent);
	}
}
