package resources.data.z57;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RegisterUserData {
	
	RegisterUserData registerUserData;
	
	@JsonProperty("user_name")
	String userName;
	
	@JsonProperty("user_email")
	String userEmail;
	
	@JsonProperty("user_phone_number")
	String userPhoneNumber;

	public RegisterUserData getRegisterUserData() {
		return registerUserData;
	}

	public RegisterUserData setRegisterUserData(String pFilename) {
		ObjectMapper mapper = new ObjectMapper();
		 try {
			registerUserData = mapper.readValue(new File(System.getProperty("user.dir")+ pFilename), RegisterUserData.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return registerUserData;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

}
