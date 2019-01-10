package com.z57.site.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginForm{
	
	WebDriver localWebDriver;
	//Email Login xpath 
	@FindBy(xpath="./descendant::*[@id=\"login_user_topbar\"]")
	WebElement email_input;
	
	//Login Xpath button
	@FindBy(xpath="./descendant::*[@id=\"login_user_topbar_button\"]")
	WebElement login_button;

	
	public LoginForm(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
		localWebDriver=webDriver;
	}

	public void setEmail(String pEmail) {
		try {
			email_input.sendKeys(pEmail);
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
		
	}
	public void clickLoginButton() {
		login_button.click();
	}
	
	public boolean isLoginSuccessful() {
		WebDriverWait wait = new WebDriverWait(localWebDriver, 10);
		return wait.until(ExpectedConditions.invisibilityOf(login_button));
	}
}
