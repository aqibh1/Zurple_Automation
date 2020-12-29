package com.zurple.backoffice;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

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
	
	@FindBy(className="btn-danger")
	WebElement del_agent;
	
	String agents_list = "//table[@id='leads_by_status']/descendant::tr/td/a";
	String agents_lead_count_list = "//table[@id='leads_by_status']/descendant::tr/td[2]";
	String agent_lead_count = "//table[@id='leads_by_status']/descendant::tr/td/a[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/td[2]";
	
	@FindBy(xpath="//table[@id='leads_by_status']/descendant::tr/td/a[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/td[2]")
	WebElement agent_count;
	
	public List<WebElement> agentsList;
	
	public ZBOAgentsPage() {
		
	}
	
	public ZBOAgentsPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyPageTitle(){
		boolean isSuccessfull = false;
		if(ActionHelper.waitForElementToBeVisible(driver, manage_agents_label, 30)) {
			String pageTitle = ActionHelper.getText(driver, manage_agents_label).trim();
			if(pageTitle.equals("Manage Agents")) {
				isSuccessfull = true;
			}
		}
		return isSuccessfull;
	}
	
	public boolean verifyAgentsCount(int pExpectedElements) {
		boolean isSuccessfull = false;
			ActionHelper.waitForStringXpathToBeVisible(driver, agents_count_xpath, 30);
			agentsList = ActionHelper.getListOfElementByXpath(driver, agents_count_xpath);
			ActionHelper.staticWait(5);
			isSuccessfull = pExpectedElements==agentsList.size();
		return isSuccessfull;
	}
	
	public int getAgentsCount() {
		return ActionHelper.getListOfElementByXpath(driver, agents_count_xpath).size();
	}
	
	public String getURL() {
		String yourText = driver.getCurrentUrl();
		String cleartext = yourText.replaceAll("https://my.stage01.zurple.com", " ");
		ActionHelper.staticWait(5);
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
		return ActionHelper.doubleClick(driver, del_agent);
	}
	public boolean confirmDelAgent() {
		ActionHelper.waitForElementToBeVisible(driver, confirmAdd , 30);
		return ActionHelper.Click(driver, confirmAdd);
	}
	public HashMap<String,String> getAgentNameAndLeadCount(){
		HashMap<String,String> agent_info_map = new HashMap<String,String>();
		String lAgentName ="Aqib Site Owner";
		while(lAgentName.equalsIgnoreCase("Aqib Site Owner") || lAgentName.equalsIgnoreCase("Aqib Production Testing")) {
			if(ActionHelper.getDynamicElementAfterRegularIntervals(driver, agents_list, "", 2)) {
				List<WebElement> list_of_elements = ActionHelper.getListOfElementByXpath(driver, agents_list);
				List<WebElement> list_of_elements_2 = ActionHelper.getListOfElementByXpath(driver, agents_lead_count_list);
				int element_index = generateRandomInt(list_of_elements.size());
				WebElement agent_web_element = list_of_elements.get(element_index);
				WebElement agent_leads_count = list_of_elements_2.get(element_index);

				lAgentName =ActionHelper.getText(driver, agent_web_element);
				String lAgentUrl = ActionHelper.getAttribute(agent_web_element, "href");
				String lAgentLeadsCount = ActionHelper.getText(driver, agent_leads_count);

				agent_info_map.put("agent_name", lAgentName);
				agent_info_map.put("agent_url", lAgentUrl);
				agent_info_map.put("agent_lead_count", lAgentLeadsCount);
			}
		}
		return agent_info_map;
	}
	public String verifyAgentName(String pAgentName) {
		String lAgentLeadsCount = "";
		//ActionHelper.waitForElementToBeClickAble(driver, agent_count);
		ActionHelper.staticWait(10);
		WebElement agent_count =  ActionHelper.getDynamicElement(driver, agent_lead_count, pAgentName.trim());
		lAgentLeadsCount = ActionHelper.getText(driver,agent_count);
		return lAgentLeadsCount;
	}
}
