/**
 * 
 */
package resources.blocks.zurple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import resources.blocks.AbstractBlock;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOLeadDetailsSearchBlock extends AbstractBlock{
	
	String buyersearch_location_list = "//div[@id='z-activity-details-searches-grid']/descendant::td[contains(@class,'yui-dt-col-location')]/div";
	String buyersearch_date = "//div[@id='z-activity-details-searches-grid']/descendant::td[contains(@class,'yui-dt-col-date')]/div";
	String buyersearch_pricerange = "//div[@id='z-activity-details-searches-grid']/descendant::td[contains(@class,'yui-dt-col-price_range')]/div";
	String buyersearch_bed = "//div[@id='z-activity-details-searches-grid']/descendant::td[contains(@class,'yui-dt-col-bedrooms')]/div";
	String buyersearch_bath = "//div[@id='z-activity-details-searches-grid']/descendant::td[contains(@class,'yui-dt-col-bathrooms')]/div";
	String buyersearch_sqfeet = "//div[@id='z-activity-details-searches-grid']/descendant::td[contains(@class,'yui-dt-col-sqft')]/div";
	String buyersearch_lotsize = "//div[@id='z-activity-details-searches-grid']/descendant::td[contains(@class,'yui-dt-col-lot_sqft')]/div";
	
	String soldhomes_location_list = "//div[@id='z-activity-details-searches-grid']/descendant::td[contains(@class,'yui-dt-col-location')]/div";
	String soldhomes_date = "//div[@id='z-activity-details-searches-grid']/descendant::td[contains(@class,'yui-dt-col-date')]/div";
	String soldhomes_pricerange = "//div[@id='z-activity-details-searches-grid']/descendant::td[contains(@class,'yui-dt-col-price_range')]/div";
	String soldhomes_bed = "//div[@id='z-activity-details-searches-grid']/descendant::td[contains(@class,'yui-dt-col-bedrooms')]/div";
	String soldhomes_bath = "//div[@id='z-activity-details-searches-grid']/descendant::td[contains(@class,'yui-dt-col-bathrooms')]/div";
	String soldhomes_sqfeet = "//div[@id='z-activity-details-searches-grid']/descendant::td[contains(@class,'yui-dt-col-sqft')]/div";
	
	String localinfo_location_list = "//div[@id='z-activity-details-local-information-searches-grid']/descendant::td[contains(@class,'yui-dt-col-location')]/div";
	String localinfo_date = "//div[@id='z-activity-details-local-information-searches-grid']/descendant::td[contains(@class,'yui-dt-col-date')]/div";
	String localinfo_type = "//div[@id='z-activity-details-local-information-searches-grid']/descendant::td[contains(@class,'yui-dt-col-type')]/div";
	
	public ZBOLeadDetailsSearchBlock() {
		
	}
	public ZBOLeadDetailsSearchBlock(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);	
	}
	
	public boolean verifyBuyerSearchLocation(String pLocation) {
		List<WebElement> list_of_locs = ActionHelper.getListOfElementByXpath(driver, buyersearch_location_list);
		return pLocation.contains(ActionHelper.getText(driver, list_of_locs.get(0)));
	}
	public boolean verifyBuyerSearchDate() {
		List<WebElement> list_of_locs = ActionHelper.getListOfElementByXpath(driver, buyersearch_date);
		return ActionHelper.getText(driver, list_of_locs.get(0)).contains(getCurrentPSTDate());
	}
	public boolean verifyBuyerSearchPriceRange(String pPriceRange) {
		List<WebElement> list_of_locs = ActionHelper.getListOfElementByXpath(driver, buyersearch_pricerange);
		return ActionHelper.getText(driver, list_of_locs.get(0)).contains(pPriceRange);
	}
	public boolean verifyBuyerSearchBedCount(String pBed) {
		pBed = pBed.replace("+", "");
		List<WebElement> list_of_locs = ActionHelper.getListOfElementByXpath(driver, buyersearch_bed);
		return ActionHelper.getText(driver, list_of_locs.get(0)).contains(pBed);
	}
	public boolean verifyBuyerSearchBathCount(String pBath) {
		pBath = pBath.replace("+", "");
		List<WebElement> list_of_locs = ActionHelper.getListOfElementByXpath(driver, buyersearch_bath);
		return ActionHelper.getText(driver, list_of_locs.get(0)).contains(pBath);
	}
	public boolean verifyBuyerSearchSqFeet(String pSqFeet) {
		List<WebElement> list_of_locs = ActionHelper.getListOfElementByXpath(driver, buyersearch_sqfeet);
		return ActionHelper.getText(driver, list_of_locs.get(0)).contains(pSqFeet);
	}
	public boolean verifyBuyerSearchLotSize(String pLotSize) {
		List<WebElement> list_of_locs = ActionHelper.getListOfElementByXpath(driver, buyersearch_lotsize);
		return ActionHelper.getText(driver, list_of_locs.get(0)).contains(pLotSize);
	}
	
	public boolean verifySoldHomesLocation(String pLocation) {
		List<WebElement> list_of_locs = ActionHelper.getListOfElementByXpath(driver, soldhomes_location_list);
		return ActionHelper.getText(driver, list_of_locs.get(0)).contains(pLocation);
	}
	public boolean verifySoldHomesDate() {
		List<WebElement> list_of_locs = ActionHelper.getListOfElementByXpath(driver, soldhomes_date);
		return ActionHelper.getText(driver, list_of_locs.get(0)).contains(getCurrentPSTDate());
	}
	public boolean verifySoldHomesPriceRange(String pPriceRange) {
		List<WebElement> list_of_locs = ActionHelper.getListOfElementByXpath(driver, soldhomes_pricerange);
		return ActionHelper.getText(driver, list_of_locs.get(0)).contains(pPriceRange);
	}
	public boolean verifySoldHomesBedCount(String pBed) {
		List<WebElement> list_of_locs = ActionHelper.getListOfElementByXpath(driver, soldhomes_bed);
		return ActionHelper.getText(driver, list_of_locs.get(0)).contains(pBed);
	}
	public boolean verifySoldHomesLotSize(String pLotSize) {
		List<WebElement> list_of_locs = ActionHelper.getListOfElementByXpath(driver, soldhomes_sqfeet);
		return ActionHelper.getText(driver, list_of_locs.get(0)).contains(pLotSize);
	}
	public boolean verifyLocalInformationSearches(String pLocalInfoSearchType, String pLocation) {
		int indexCount = 0;
		boolean lLocalInfoTypeVerified = false;
		boolean lLocalInfoDateVerified = false;
		boolean lLocalInfoLocationVerified = false;
		List<WebElement> list_of_locs = ActionHelper.getListOfElementByXpath(driver, localinfo_type);
		for(int i=0;i<list_of_locs.size();i++) {
			if(ActionHelper.getText(driver, list_of_locs.get(i)).equalsIgnoreCase(pLocalInfoSearchType)) {
				lLocalInfoTypeVerified = true;
				indexCount = i;
				break;
			}
		}
		if(lLocalInfoTypeVerified) {
			lLocalInfoDateVerified = ActionHelper.getText(driver,ActionHelper.getListOfElementByXpath(driver, localinfo_date).get(indexCount)).contains(getCurrentPSTDate());
			lLocalInfoLocationVerified = ActionHelper.getText(driver,ActionHelper.getListOfElementByXpath(driver, localinfo_location_list).get(indexCount)).contains(pLocation);
		}
		
		return (lLocalInfoDateVerified && lLocalInfoLocationVerified);
	}
	private String getCurrentPSTDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
		String formattedDate = dateFormat.format(new Date(System.currentTimeMillis())).toString().toLowerCase();
		return formattedDate;

	}
}
