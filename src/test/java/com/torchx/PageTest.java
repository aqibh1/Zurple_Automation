/**
 * 
 */
package com.torchx;

import org.openqa.selenium.WebDriver;

import com.zurple.backoffice.ZBOLoginPage;

import resources.AbstractPageTest;
import resources.EnvironmentFactory;

/**
 * @author adar
 *
 */

public abstract class PageTest extends AbstractPageTest{
	
	private String l_userName = EnvironmentFactory.configReader.getPropertyByName("torchx_bo_user");
	private String l_password = EnvironmentFactory.configReader.getPropertyByName("torchx_bo_pass");
	
	private TXBOLoginPage loginPage;
	
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	public String getTXBOUsername() {
		return l_userName;
	}
	public void setL_userName() {
		this.l_userName = EnvironmentFactory.configReader.getPropertyByName("torchx_bo_user");;
	}
	
	public String getTXBOPassword() {
		return l_password;
	}
	public void setL_password() {
		this.l_password = EnvironmentFactory.configReader.getPropertyByName("torchx_bo_pass");
	}
	
	public TXBOLoginPage getLoginPage() {
		return loginPage;
	}
	
	public void setLoginPage(WebDriver pWebDriver) {
		this.loginPage = new TXBOLoginPage(pWebDriver);
	}

}
