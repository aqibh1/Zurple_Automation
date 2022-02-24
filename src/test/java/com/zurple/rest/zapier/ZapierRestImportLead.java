package com.zurple.rest.zapier;

//Java program to write data in google sheet using java code

/**
 * @author habibaaq
 */

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.restapi.RestAPITest;
import com.restapi.RestResponse;

import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.AutomationLogger;

import com.google.api.client.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONObject;
import org.testng.annotations.Parameters;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ZapierRestImportLead extends RestAPITest{
	
	    private static final String APPLICATION_NAME = "Quickstart";
	    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	    private static final String TOKENS_DIRECTORY_PATH = "tokens";

	    /**
	     * Global instance of the scopes required by this quickstart.
	     * If modifying these scopes, delete your previously saved tokens/ folder.
	     */
	    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
	    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
	    
	    /**
	     * Add a new row in spreadsheet:
	     * https://docs.google.com/spreadsheets/d/1Vc8xkLBO2uJp7HFXIxQoY-HJwY5TEemTBDQ37vvye1E
	     */
	    
	    public void testVerifyGoogleSheet(JSONObject dataObject) throws IOException, GeneralSecurityException{
	    	// Build a new authorized API client service.
	        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport(); //new NetHttpTransport();  
	        final String spreadsheetId = "1Vc8xkLBO2uJp7HFXIxQoY-HJwY5TEemTBDQ37vvye1E";
	        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
	        		.setApplicationName(APPLICATION_NAME)
	        		.build();
	        
	        String range = "A:I";	  
	        List<String> list1 = new ArrayList<>();
	        list1.add(updateName(dataObject.optString("first_name")));
	        list1.add(dataObject.optString("last_name"));
	        list1.add(dataObject.optString("phone"));
	        list1.add(updateEmail(dataObject.optString("email")));
	        list1.add(dataObject.optString("city"));
	        list1.add(dataObject.optString("zip"));
	        list1.add(dataObject.optString("street"));
	        list1.add(dataObject.optString("state"));
	        list1.add(dataObject.optString("note"));
	        List<Object> data1 = new ArrayList<>();
	        data1.addAll(list1);
	        
	        List<List<Object>> data = new ArrayList<>();
	        data.add(data1);
	        
	        getDriver();
	        ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZapierLeadFName, list1.get(0).toString());
	        ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZapierLeadEmail, list1.get(3).toString());
	        
	        ValueRange valueRange=new ValueRange();
	        valueRange.setValues(data);
	        service.spreadsheets().values().
	        append(spreadsheetId, range, valueRange)
	                .setValueInputOption("RAW")
	                .execute();
	    }
	    
	    /**
	     * Creates an authorized Credential object.
	     * @param HTTP_TRANSPORT The network HTTP Transport.
	     * @return An authorized Credential object.
	     * @throws IOException If the credentials.json file cannot be found.
	     */
	    
		private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
	        // Load client secrets.
	        InputStream in = ZapierRestImportLead.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
	        if (in == null) {
	            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
	        }
	        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
	        // Build flow and trigger user authorization request.
	        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
	                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
	                .setAccessType("offline")
	                .build();
	        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
	        
	        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	    }
		
		@Override
		public boolean validateMapResp(RestResponse httpCallResp) throws Exception {
			// TODO Auto-generated method stub
			return false;
		}
	}