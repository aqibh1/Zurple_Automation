package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

public class ZBOVerifyZapierLeadImportPage extends Page{

	private ZBOLeadDetailPage leadDetails;
	
	ZBOVerifyZapierLeadImportPage(){
	}
	
	public ZBOVerifyZapierLeadImportPage(WebDriver pWebdriver) {
		driver = pWebdriver;
		setLeadDetailPage();
		PageFactory.initElements(driver, this);
	}
	
	public ZBOLeadDetailPage getLeadDetailPage() {
		return leadDetails;
	}	

	public void setLeadDetailPage() {
		this.leadDetails = new ZBOLeadDetailPage(driver);
	}
	
	public boolean verifyLeadName(String pName) {
		return leadDetails.isLeadNameExist(pName);
	}
	
}
