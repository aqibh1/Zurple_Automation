/**
 * 
 */
package com.torchx;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.blocks.torchx.TXBOFooterMenuBlock;
import resources.blocks.torchx.TXBOSideMenuBlock;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class TXBODashboardPage extends Page{
	@FindBy(id="key-stats-header")
	WebElement keyStats;
	
	@FindBy(xpath="//button[@class='navbar-toggle']")
	WebElement nav_toggle_button;
	
	TXBOSideMenuBlock sideMenuBlock;
	TXBOFooterMenuBlock footerMenuBlock;
	
	public TXBODashboardPage() {
		
	}
	public TXBODashboardPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setSideMenuBlock();
		setFooterMenuBlock();
		PageFactory.initElements(driver, this);
	}
	
	public TXBOSideMenuBlock getSideMenuBlock() {
		return sideMenuBlock;
	}
	public void setSideMenuBlock() {
		this.sideMenuBlock = new TXBOSideMenuBlock(driver);
	}
	
	public TXBOFooterMenuBlock getFooterMenuBlock() {
		return footerMenuBlock;
	}
	public void setFooterMenuBlock() {
		this.footerMenuBlock = new TXBOFooterMenuBlock(driver);
	}
	public boolean isDashboardPage() {
		return ActionHelper.waitForElementToBeVisible(driver, keyStats, 20);
	}
	public boolean clickOnNavToggleButton() {
		return ActionHelper.expandUnexpandDropdown(driver, nav_toggle_button, true);
	}
	public boolean resizeWindowToMobileView() {
		return ActionHelper.resizeWindow(driver, 736, 414);
	}
}
