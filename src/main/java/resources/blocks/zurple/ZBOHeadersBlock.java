package resources.blocks.zurple;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.blocks.AbstractBlock;
import resources.utility.ActionHelper;

public class ZBOHeadersBlock extends AbstractBlock{
	
	@FindBy(id="social-dropdown-toggle")
	WebElement adsManager_Dropdown;
	
	@FindBy(xpath="//li[@id='social-dropdown']/descendant::span[text()='Create Ad']")
	WebElement createAd_dropdown;
	
	@FindBy(xpath="//li[@id='social-dropdown']/descendant::span[text()='Ads Overview']")
	WebElement adsOverview_dropdown;
	
	public ZBOHeadersBlock(WebDriver pDriver) {
		driver = pDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isAdsManagerHeadingVisible() {
		boolean isAdsManagerVisible = false;
		if(ActionHelper.waitForElementToBeVisible(driver, adsManager_Dropdown, 30)) {
			isAdsManagerVisible = ActionHelper.getText(driver, adsManager_Dropdown).equalsIgnoreCase("Ads Manager");
		}
		return isAdsManagerVisible;
	}
	public boolean verifyAdsManagerDropdownOptions() {
		boolean isVerified = false;
		if(ActionHelper.MouseHoverOnElement(driver, adsManager_Dropdown)) {
			if(ActionHelper.isElementVisible(driver, createAd_dropdown) && ActionHelper.isElementVisible(driver, adsOverview_dropdown)) {
				isVerified = true;
			}
		}
		return isVerified;
	}
	public boolean clickOnAdsOverviewButton() {
		boolean isVerified = false;
		if(ActionHelper.MouseHoverOnElement(driver, adsManager_Dropdown)) {
			if(ActionHelper.Click(driver, adsOverview_dropdown)) {
				isVerified = true;
			}
		}
		return isVerified;
	}
}
