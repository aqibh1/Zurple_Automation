/**
 * 
 */
package com.z57.site.v1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import resources.forms.z57.v1.RegisterUserForm;

/**
 * @author adar
 *
 */
public class HomePage extends Page{

	private RegisterUserForm registerUserForm;

	public HomePage() {

	}
	public HomePage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setRegisterUserForm();
		PageFactory.initElements(driver, this);
	}

	public RegisterUserForm getRegisterUserForm() {
		return registerUserForm;
	}

	public void setRegisterUserForm() {
		this.registerUserForm = new RegisterUserForm(driver);
	}



}
