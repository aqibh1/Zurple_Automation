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
}
