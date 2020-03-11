package com.zurple.backoffice;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
	
	@FindBy(id="delete-agent-button")
	WebElement del_agent;
	
//	@FindBy(xpath="//div[contains(@class,'ui-dialog-buttonset')]//span[@class='ui-button-text']")
//	WebElement confirmDel;
	
	String confirmDel = "//div[contains(@class,'ui-dialog-buttonset')]//span[@class='ui-button-text']";
	
	public List<WebElement> agentsList;
	
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
			ActionHelper.waitForStringXpathToBeVisible(driver, agents_count_xpath, 30);
			agentsList = ActionHelper.getListOfElementByXpath(driver, agents_count_xpath);
			isSuccessfull = pExpectedElements==agentsList.size();
		return isSuccessfull;
	}
	
	public String getURL() {
		String yourText = driver.getCurrentUrl();
		String cleartext = yourText.replaceAll("https://my.stage01.zurple.com", " ");
		return cleartext.trim();
	}
	
	public boolean typeAgentFirstName(String pAgentName) {
		ActionHelper.waitForElementToBeVisible(driver, agent_first_name, 30);
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
		return ActionHelper.Type(driver, agent_confirmPassword, pAgentConfirmPassword);
	}
	public boolean addAgentButton() {
		return ActionHelper.Click(driver, add_agent);
	}
	public boolean confirmAgent() {
		ActionHelper.waitForElementToBeVisible(driver, confirmAdd , 30);
		return ActionHelper.Click(driver, confirmAdd);
	}
	public boolean delAgent() {
		ActionHelper.waitForElementToBeVisible(driver, del_agent, 30);
		return ActionHelper.Click(driver, del_agent);
	}
	public boolean confirmDelAgent() {
		ActionHelper.waitForStringXpathToBeVisible(driver, confirmDel, 30);
		return ActionHelper.ClickStringXpathElement(driver, confirmDel);
	}
}
