package resources.data.z57;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import resources.utility.AutomationLogger;

public class OurCommunityData {
	
	OurCommunityData ourCommunityData;
	
	@JsonProperty("address")
	String address;
	
	@JsonProperty("city")
	String city;
	
	@JsonProperty("state")
	String state;
	
	@JsonProperty("zip")
	String zip;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public OurCommunityData setOurCommunityData(String pFilename){
		ObjectMapper mapper = new ObjectMapper();
		try {
			ourCommunityData = mapper.readValue(new File(System.getProperty("user.dir")+ pFilename), OurCommunityData.class);
		} catch (IOException e) {
			AutomationLogger.error("Class Mapping failed ->"+pFilename);
			e.printStackTrace();
		}
		return ourCommunityData;
	}
	public OurCommunityData getOurCommunityDataObject() {
		return ourCommunityData;
	}

}
