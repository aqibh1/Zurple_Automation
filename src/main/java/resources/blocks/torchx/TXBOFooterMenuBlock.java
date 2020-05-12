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
public class TXBOFooterMenuBlock extends AbstractBlock {
	
	@FindBy(xpath="//div[@id='footer']/descendant::a[text()='Dashboard']")
	WebElement dashboard_footer_link;

	@FindBy(xpath="//div[@id='footer']/descendant::a[text()='Leads']")
	WebElement leads_footer_link;
	
	@FindBy(xpath="//div[@id='footer']/descendant::a[text()='Properties']")
	WebElement prop_footer_link;
	
	@FindBy(xpath="//div[@id='footer']/descendant::a[text()='Statistics']")
	WebElement statistics_footer_link;
	
	@FindBy(xpath="//div[@id='footer']/descendant::a[text()='Support']")
	WebElement support_footer_link;
	
	public TXBOFooterMenuBlock() {
		
	}
	public TXBOFooterMenuBlock(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean gotoStatistics() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, statistics_footer_link)) {
			isSuccessful = driver.getCurrentUrl().contains("statistics");
		}
		return isSuccessful;
	}
	
	public boolean goBackToDashboard() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, dashboard_footer_link)){
			isSuccessful = driver.getCurrentUrl().contains("dashboard");
		}
		return isSuccessful;
	}
	
	public boolean gotoSupport() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, support_footer_link)) {
			isSuccessful = driver.getCurrentUrl().contains("support");
		}
		return isSuccessful;
	}
	
	public boolean gotoProperties() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, prop_footer_link)) {
			isSuccessful = driver.getCurrentUrl().contains("properties");
		}
		return isSuccessful;
	}
	
	public boolean gotoLeadList() {
		boolean isSuccessful = false;
		if(ActionHelper.Click(driver, leads_footer_link)) {
			isSuccessful = driver.getCurrentUrl().contains("leads/crm");
		}
		return isSuccessful;
	}
}
