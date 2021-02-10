package com.torchx;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

/**
 * 
 * @author ahabib
 *
 */
public class TXBORecentVisitorsPanel extends Page {

	@FindBy(className="customized")
	WebElement panel_header;
	
	@FindBy(xpath="//div[@class='col-md-6 ']/descendant::i[@class='info-icon-white']")
	WebElement tooltip_text;
	
	@FindBy(className="full_name")
	WebElement lead_info;
	
	public List<String> leadInfoList = new ArrayList<String>();
	
	TXBORecentVisitorsPanel(){
	}

	public TXBORecentVisitorsPanel(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPanelHeaderText() {
		return ActionHelper.getText(driver, panel_header);
	}
	
	public String getToolTipText() {
		return ActionHelper.getAttribute(tooltip_text,"title");
	}
	
	public List<String> getLeadInfo() {
		String leadInfo = ActionHelper.getText(driver, lead_info);
		String leadName = leadInfo.split("\\(")[0]; //0210202121639 Autouser
		String leadPhone = leadInfo.split("\\s{4}")[1];
		leadPhone = leadPhone.split("\\[")[0]; //202) 555-0149
		String leadVisitDate = leadInfo.split("\\[")[1]; //02/10/21 at 2:00am]
		leadInfoList.add(leadName);
		leadInfoList.add(leadPhone);
		leadInfoList.add(leadVisitDate);
		return leadInfoList;
	}
	
}
