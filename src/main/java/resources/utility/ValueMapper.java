package resources.utility;

import java.util.HashMap;

public class ValueMapper {
	private HashMap<String, Integer> listingStatusMapper = new HashMap<String,Integer>();
	private HashMap<String, String> stateMapper = new HashMap<String,String>();
	private HashMap<String, Integer> propertyTypeMapper = new HashMap<String,Integer>();
	private HashMap<String, String> repeatOnDaysMapper = new HashMap<String,String>();
	
	public ValueMapper(){
		setListingStatusMapper();
		setStates();
		setPropertyTypeMapper();
		setRepeatOnDaysMapper();
	}
	private void setListingStatusMapper() {
		listingStatusMapper.put("Active",1);
		listingStatusMapper.put("Pending",2);
		listingStatusMapper.put("Sold",3);
		listingStatusMapper.put("Rented",4);
		listingStatusMapper.put("Leased",5);
		listingStatusMapper.put("Just Listed",6);
		listingStatusMapper.put("Just Reduced",7);
		listingStatusMapper.put("Off the Market",8);
		listingStatusMapper.put("Just Under Contract",9);
		listingStatusMapper.put("Off the Contingent",10);	
	}
	private void setStates() {
		stateMapper.put("California", "CA");
	}
	public Integer getListingStatus(String pListingStatus) {
		return listingStatusMapper.get(pListingStatus);
	}
	public String getState(String pState) {
		return stateMapper.get(pState);
	}
	public Integer getPropertyTypeMapper(String pType) {
		return propertyTypeMapper.get(pType);
	}
	public void setPropertyTypeMapper() {
		propertyTypeMapper.put("New Home", 1);
		propertyTypeMapper.put("Resale", 2);
		propertyTypeMapper.put("Rental", 3);
	}
	
	private void setRepeatOnDaysMapper() {
		repeatOnDaysMapper.put("Sunday", "Sun");
		repeatOnDaysMapper.put("Monday", "Mon");
		repeatOnDaysMapper.put("Tuesday", "Tue");
		repeatOnDaysMapper.put("Wednesday", "Wed");
		repeatOnDaysMapper.put("Thursday", "Thu");
		repeatOnDaysMapper.put("Friday", "Fri");
		repeatOnDaysMapper.put("Saturday", "Sat");
	}
	public String getDays(String pDays) {
		return repeatOnDaysMapper.get(pDays);
	}

}
