package com.zurple.backoffice;

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
	
	@FindBy(className="blue_msg_bg")
	WebElement sent_message;
	
	@FindBy(className="timestamp")
	WebElement message_timestamp;
	
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
		return ActionHelper.Click(driver, lead_details);
	}
	
	public boolean clickMessageBox(String pText) {
		return ActionHelper.ClearAndType(driver, text_area, pText);
	}
	
	public boolean sendMessage(String pText) {
		return ActionHelper.Click(driver, send_button);
	}
	
	public boolean getInitials(String pExpected) {
		return ActionHelper.getText(driver, sender_initials).equals(pExpected);
	}
	
	public boolean getMessageTimestamp(String pExpected) {
		return ActionHelper.getText(driver, message_timestamp).equals(pExpected);
	}
	
	public boolean getMessage(String pExpected) {
		return ActionHelper.getText(driver, message_timestamp).equals(pExpected);
	}

}
