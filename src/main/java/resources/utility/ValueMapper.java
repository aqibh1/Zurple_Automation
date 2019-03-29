package resources.utility;

import java.util.HashMap;

public class ValueMapper {
	private HashMap<String, Integer> listingStatusMapper = new HashMap<String,Integer>();
	private HashMap<String, String> stateMapper = new HashMap<String,String>();
	
	public ValueMapper(){
		setListingStatusMapper();
		setStates();
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

}
