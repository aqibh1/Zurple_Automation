/**
 * 
 */
package resources.blocks.torchx;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.blocks.AbstractBlock;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class TXBOSideMenuBlock extends AbstractBlock{
	
	@FindBy(xpath="//a[@class='navbar-brand']/img[@src='/img/torchx/torchx_allwhite.png']")
	WebElement torchXLogo_sidebar;
	
	@FindBy(id="profile-dropdown-toggle")
	WebElement automationTx_dropdownmenu;
	
	@FindBy(linkText="My Profile")
	WebElement myProfile_link;
	
	@FindBy(linkText="Billing")
	WebElement billing_link;
	
	@FindBy(linkText="Support")
	WebElement support_link;
	
	@FindBy(linkText="Logout")
	WebElement logout_link;
	
	@FindBy(linkText="Dashboard")
	WebElement dashboard_link;
	
	@FindBy(id="lead-dropdown-toggle")
	WebElement leads_dropdownmenu;
	
	@FindBy(linkText="Leads List")
	WebElement leadList_link;
	
	@FindBy(linkText="Add Lead")
	WebElement addLead_link;
	
	@FindBy(linkText="Properties")
	WebElement properties_link;
	
	@FindBy(id="marketing-dropdown-toggle")
	WebElement marketing_dropdownmenu;
	
	@FindBy(linkText="Message Log")
	WebElement messageLog_link;
	
	@FindBy(linkText="Email Message")
	WebElement emailMessage_link;
	
	@FindBy(linkText="Text Message")
	WebElement textMessage_link;
	
	@FindBy(linkText="Campaign Manager")
	WebElement campaignManager_link;
	
	@FindBy(linkText="Template Manager")
	WebElement templateManager_link;
	
	@FindBy(linkText="Craigslist")
	WebElement craigList_link;
	
	@FindBy(id="social-dropdown-toggle")
	WebElement social_dropdownmenu;
	
	@FindBy(linkText="Create Post")
	WebElement createPost_link;
	
	@FindBy(linkText="Scheduled Posts")
	WebElement scheduledPosts_link;
	
	@FindBy(linkText="Post History")
	WebElement postHistory_link;
	
	@FindBy(linkText="Integrations & Settings")
	WebElement integrationsAndSettings_link;
	
	@FindBy(linkText="Statistics")
	WebElement statistics_link;
	
	@FindBy(xpath="//nav[@role='navigation']")
	WebElement navigation_bar;
	
	public TXBOSideMenuBlock() {
		// TODO Auto-generated constructor stub
	}
	public TXBOSideMenuBlock(WebDriver pWebDriver ) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isTorchXLogoVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, torchXLogo_sidebar, 20);
	}
	public boolean expandAutomationMenu() {
		return ActionHelper.expandUnexpandDropdown(driver, automationTx_dropdownmenu, true);
	}
	public boolean gotoMyProfile() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, myProfile_link)) {
			isSuccessful = driver.getCurrentUrl().contains("agent/edit/admin_id");
		}
		return isSuccessful;
	}
	public boolean goBackToDashboard() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, dashboard_link)){
			isSuccessful = driver.getCurrentUrl().contains("dashboard");
		}
		return isSuccessful;
	}
	public boolean gotoBilling() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, billing_link)) {
			isSuccessful = driver.getCurrentUrl().contains("billing");
		}
		return isSuccessful;
	}
	public boolean gotoSupport() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, support_link)) {
			isSuccessful = driver.getCurrentUrl().contains("support");
		}
		return isSuccessful;
	}
	public boolean expandLeadMenu() {
		return ActionHelper.expandUnexpandDropdown(driver, leads_dropdownmenu, true);
	}
	public boolean gotoLeadList() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, leadList_link)) {
			isSuccessful = driver.getCurrentUrl().contains("leads/crm");
		}
		return isSuccessful;
	}
	public boolean gotoAddList() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, addLead_link)) {
			isSuccessful = driver.getCurrentUrl().contains("lead/create");
		}
		return isSuccessful;
	}
	public boolean gotoProperties() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, properties_link)) {
			isSuccessful = driver.getCurrentUrl().contains("properties");
		}
		return isSuccessful;
	}
	public boolean expandMarketingMenu() {
		return ActionHelper.expandUnexpandDropdown(driver, marketing_dropdownmenu, true);
	}
	public boolean gotoMessageLog() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, messageLog_link)) {
			isSuccessful = driver.getCurrentUrl().contains("messagelog");
		}
		return isSuccessful;
	}
	public boolean gotoEmailMessage() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, emailMessage_link)) {
			isSuccessful = driver.getCurrentUrl().contains("marketing/massemail");
		}
		return isSuccessful;
	}
	public boolean gotoTextMessage() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, textMessage_link)) {
			isSuccessful = driver.getCurrentUrl().contains("marketing/textmessage");
		}
		return isSuccessful;
	}
	public boolean gotoCampaigns() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, campaignManager_link)) {
			isSuccessful = driver.getCurrentUrl().contains("campaigns");
		}
		return isSuccessful;
	}
	public boolean gotoTemplateManager() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, templateManager_link)) {
			isSuccessful = driver.getCurrentUrl().contains("marketing/templates");
		}
		return isSuccessful;
	}
	public boolean gotoCraiglist() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, craigList_link)) {
			isSuccessful = driver.getCurrentUrl().contains("properties/craigslist");
		}
		return isSuccessful;
	}
	public boolean expandSocialMenu() {
		return ActionHelper.expandUnexpandDropdown(driver, social_dropdownmenu, true);
	}
	public boolean gotoCreatePost() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, createPost_link)) {
			isSuccessful = driver.getCurrentUrl().contains("social/createpost");
		}
		return isSuccessful;
	}
	public boolean gotoSchedulePost() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, scheduledPosts_link)) {
			isSuccessful = driver.getCurrentUrl().contains("social/scheduledposts");
		}
		return isSuccessful;
	}
	public boolean gotoPostHistory() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, postHistory_link)) {
			isSuccessful = driver.getCurrentUrl().contains("social/history");
		}
		return isSuccessful;
	}
	public boolean gotoIntegrationAndSettings() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, integrationsAndSettings_link)) {
			isSuccessful = driver.getCurrentUrl().contains("social/integrations-and-settings");
		}
		return isSuccessful;
	}
	public boolean gotoStatistics() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, statistics_link)) {
			isSuccessful = driver.getCurrentUrl().contains("statistics");
		}
		return isSuccessful;
	}
	public boolean verifyNavigationbarColor() {
		return navigation_bar.getCssValue("background-color").contains("32, 75, 134");
	}
}
