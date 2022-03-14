package com.zurple.backoffice;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOSMSChatPage extends Page{

	@FindBy(className="z-page-title")
	WebElement page_title;
	
	@FindBy(xpath="//div[@class='row chatlog_header ']/h2")
	WebElement chat_header;
	
	@FindBy(xpath="//div[@class='row chatlog_header ']/h3[@class='leadname']")
	WebElement lead_name;
	
	@FindBy(xpath="//div[@class='row chatlog_header ']/span")
	WebElement lead_phone;
	
	@FindBy(xpath="//a[@class='btn btn-primary leaddetail_btn float-right']")
	WebElement lead_details;
	
	@FindBy(xpath="//div[@class='sect_left']/textarea[@name='message']")
	WebElement text_area;
	
	@FindBy(xpath="//button[@id='chat_submit_button']/i[@class='fab fa-telegram sendicon']")
	WebElement send_button;
	
	@FindBy(className="initial")
	WebElement sender_initials;
	
//	@FindBy(className="blue_msg_bg")
//	WebElement sent_message;
	
//	@FindBy(className="timestamp")
//	WebElement message_timestamp;
	
	String message_timestamp = "//section[@id='chat_message_body']/descendant::span[contains(@class,'timestamp')]";
	
	String sent_message = "//section[@id='chat_message_body']/descendant::div[contains(@class,'msg_box')]/span";
	
	List<WebElement> messagesList;
	
	public ZBOSMSChatPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean pageTitle(String pExpected) {
		return ActionHelper.getText(driver, page_title).equals(pExpected);
	}
	
	public boolean chatPageHeader(String pExpected) {
		return ActionHelper.getText(driver, chat_header).equals(pExpected);
	}
	
	public boolean leadName(String pExpected) {
		return ActionHelper.getText(driver, lead_name).equals(pExpected);
	}
	
	public boolean leadPhoneNumber(String pExpected) {
		return ActionHelper.getText(driver, lead_phone).equals(pExpected);
	}
	
	public boolean clickLeadDetails() {
		return !ActionHelper.getAttribute(lead_details,"href").isEmpty();
	}
	
	public boolean typeMessage(String pText) {
		return ActionHelper.ClearAndType(driver, text_area, pText);
	}
	
	public boolean sendMessage() {
		return ActionHelper.Click(driver, send_button);
	}
	
	public boolean getInitials(String pExpected) {
		return ActionHelper.getText(driver, sender_initials).equals(pExpected);
	}
	
	public boolean getMessage(String pExpected) {
		messagesList = ActionHelper.getListOfElementByXpath(driver, sent_message);
		return ActionHelper.getTextByXpathIndex(driver, sent_message, messagesList.size()-1).trim().equals(pExpected);
	}
	
	public boolean getMessageTimestamp(String pExpected) {
		//List<WebElement> timestampList = ActionHelper.getListOfElementByClassName(driver, message_timestamp);
		return ActionHelper.getTextByXpathIndex(driver, message_timestamp, messagesList.size()-1).trim().contains(pExpected);
	}
}
