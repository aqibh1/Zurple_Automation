/**
 * 
 */
package com.zurple.admin;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZAProcessEmailQueuesPage extends Page{
	
	@FindBy(xpath="//h1[text()='Process Email Queue']")
	WebElement process_email_queue_heading;
	
	@FindBy(xpath="//button[text()='Immediate Responder Queue']")
	WebElement immediate_responder_queue;
	
	@FindBy(xpath="//button[text()='Autoresponder Queue']")
	WebElement autoresponder_queue_button;
	
	@FindBy(xpath="//button[text()='Next Day Responder Queue']")
	WebElement nextday_responder_queue_button;
	
	@FindBy(xpath="//button[text()='Mass Email Queue']")
	WebElement mass_email_queue_button;
	
	@FindBy(xpath="//button[text()='Create C1 Summary Emails']")
	WebElement create_c1_summary;
	
	@FindBy(xpath="//button[text()='Send C1 Summary Emails']")
	WebElement send_c1_summary;
	
	@FindBy(xpath="//button[text()='Reminder Email Queue']")
	WebElement reminderemail_queue_button;
	
	@FindBy(xpath="//button[text()='Alert Queue']")
	WebElement alert_queue_button;
	
	@FindBy(xpath="//button[text()='Checkin Queue']")
	WebElement checkin_queue_button;
	
	@FindBy(xpath="//button[text()='Digest Queue']")
	WebElement digest_queue_button;
	
	@FindBy(xpath="//button[text()='CMA Email Queue']")
	WebElement CMA_Email_queue_button;
	
	@FindBy(xpath="//button[text()='Checkin Populate Queue']")
	WebElement checkin_populate_queue_button;
	
	@FindBy(xpath="//div[@class='col-md-12 queuedEmailsCont']/button[text()='Process Queue']")
	WebElement processQueue_button;
	
	@FindBy(xpath="//div[@class='blockUI blockMsg blockPage' and text()='Processing...']")
	WebElement processing_alert;
	
	public ZAProcessEmailQueuesPage() {
		
	}
	public ZAProcessEmailQueuesPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isProcessEmailQueuePage() {
		return ActionHelper.waitForElementToBeVisible(driver, process_email_queue_heading, 30);
	}
	public boolean clickOnAutoResponderQueueButton() {
		return ActionHelper.Click(driver, autoresponder_queue_button);
	}
	public boolean clickOnAlertQueueButton() {
		return ActionHelper.Click(driver, alert_queue_button);
	}
	public boolean clickOnImmediateResponderQueueButton() {
		return ActionHelper.Click(driver, immediate_responder_queue);
	}
	public boolean clickOnProcessQueueButton() {
		return ActionHelper.Click(driver, processQueue_button);
	}
	public boolean isPorcessingComplete() {
		return ActionHelper.waitforElementToBeDisappearedByRegularIntervals(driver, processing_alert,30, 10);
	}
	public boolean clickOnMassEmailQueueButton() {
		return ActionHelper.Click(driver, mass_email_queue_button);
	}
	
	public boolean clickOnNextDayQueueButton() {
		return ActionHelper.Click(driver, nextday_responder_queue_button);
	}
	public boolean clickOnCreateC1SummaryEmailsQueueButton() {
		return ActionHelper.Click(driver, create_c1_summary);
	}
	public boolean clickOnSendC1SummaryEmailsQueueButton() {
		return ActionHelper.Click(driver, send_c1_summary);
	}
	
	public void processAutoResponderQueue() {
		assertTrue(isProcessEmailQueuePage(), "Process Email Queue page is not visible...");
		assertTrue(clickOnAutoResponderQueueButton(), "Unable to click on AR Queue button...");
		ActionHelper.staticWait(5);
		assertTrue(clickOnProcessQueueButton(), "Unable to click on process queue button...");
		assertTrue(isPorcessingComplete(), "Processing didn't complete in 5 minutes");
	}
	
	public void processAlertQueue() {
		assertTrue(isProcessEmailQueuePage(), "Process Email Queue page is not visible...");
		assertTrue(clickOnAlertQueueButton(), "Unable to click on Alert Queue button...");
		ActionHelper.staticWait(5);
		assertTrue(clickOnProcessQueueButton(), "Unable to click on process queue button...");
		assertTrue(isPorcessingComplete(), "Processing didn't complete in 5 minutes");
	}
	public void processImmediateResponderQueue() {
		assertTrue(isProcessEmailQueuePage(), "Process Email Queue page is not visible...");
		assertTrue(clickOnImmediateResponderQueueButton(), "Unable to click on Alert Queue button...");
		ActionHelper.staticWait(5);
		assertTrue(clickOnProcessQueueButton(), "Unable to click on process queue button...");
		assertTrue(isPorcessingComplete(), "Processing didn't complete in 5 minutes");
	}
	public void processMassEmailQueue() {
		assertTrue(isProcessEmailQueuePage(), "Process Email Queue page is not visible...");
		assertTrue(clickOnMassEmailQueueButton(), "Unable to click on Mass Email Queue button...");
		ActionHelper.staticWait(5);
		assertTrue(clickOnProcessQueueButton(), "Unable to click on process queue button...");
		assertTrue(isPorcessingComplete(), "Processing didn't complete in 5 minutes");
	}
	
	public void processNextDayResponderQueue() {
		assertTrue(isProcessEmailQueuePage(), "Process Email Queue page is not visible...");
		assertTrue(clickOnNextDayQueueButton(), "Unable to click on Mass Email Queue button...");
		ActionHelper.staticWait(5);
		assertTrue(clickOnProcessQueueButton(), "Unable to click on process queue button...");
		assertTrue(isPorcessingComplete(), "Processing didn't complete in 5 minutes");
	}
	public void processCreateC1SummaryQueue() {
		assertTrue(isProcessEmailQueuePage(), "Process Email Queue page is not visible...");
		assertTrue(clickOnCreateC1SummaryEmailsQueueButton(), "Unable to click on Create C1 Email Queue button...");
		ActionHelper.staticWait(5);
		assertTrue(isPorcessingComplete(), "Processing didn't complete in 5 minutes");
	}
	public void processSendC1SummaryQueue() {
		assertTrue(isProcessEmailQueuePage(), "Process Email Queue page is not visible...");
		assertTrue(clickOnSendC1SummaryEmailsQueueButton(), "Unable to click on Send C1 Email Queue button...");
		ActionHelper.staticWait(5);
		assertTrue(isPorcessingComplete(), "Processing didn't complete in 5 minutes");
	}
	
	public boolean clickOnReminderQueueButton() {
		return ActionHelper.Click(driver, reminderemail_queue_button);
	}
	
	public void processReminderQueue() {
		assertTrue(isProcessEmailQueuePage(), "Process Email Queue page is not visible...");
		assertTrue(clickOnReminderQueueButton(), "Unable to click on Reminder Email Queue button...");
		ActionHelper.staticWait(5);
		assertTrue(clickOnProcessQueueButton(), "Unable to click on process queue button...");
		assertTrue(isPorcessingComplete(), "Processing didn't complete in 5 minutes");
	}
}
