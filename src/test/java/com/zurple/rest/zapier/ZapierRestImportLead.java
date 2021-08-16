package com.zurple.rest.zapier;

//Java program to write data in excel sheet using java code

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

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	     * Prints the names and majors of students in a sample spreadsheet:
	     * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
	     */
	    @Test
	    public void testVerifyGoogleSheet() throws IOException, GeneralSecurityException{
	        // Build a new authorized API client service.
	        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport(); //new NetHttpTransport();  
	        final String spreadsheetId = "1Vc8xkLBO2uJp7HFXIxQoY-HJwY5TEemTBDQ37vvye1E";
	        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
	        		.setApplicationName(APPLICATION_NAME)
	        		.build();
	        
	        String range = "A:H";	  
	        List<String> list1 = new ArrayList<>();
	        list1.add("Aaqib");
	        list1.add("Habib");
	        list1.add("14844493826");
	        list1.add("zapimport_zurpleqa@mailinator.com");
	        list1.add("San Diego");
	        list1.add("92130");
	        list1.add("7");
	        list1.add("CA");
	        List<Object> data1 = new ArrayList<>();
	        data1.addAll(list1);
	        List<List<Object>> data = new ArrayList<>();
	        data.add(data1);
	        ValueRange valueRange=new ValueRange();
	        valueRange.setValues(data);
	        service.spreadsheets().values().
	        append(spreadsheetId, range, valueRange)
	                .setValueInputOption("RAW")
	                .execute();
//	        ValueRange response = service.spreadsheets().values()
//            .get(spreadsheetId, range)
//            .execute();
//    List<List<Object>> values = response.getValues();
//    if (values == null || values.isEmpty()) {
//        System.out.println("No data found.");
//    } else {
//        for (List row : values) {
//            // Print columns A and E, which correspond to indices 0 and 4.
//            System.out.printf("%s, %s\n", row.get(0), row.get(4));
//        }
//    }

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
	
	
//	// any exceptions need to be caught
//	@Test
//	public void testVerifyZap() throws Exception
//	{
//		// workbook object
//		try {
////			XSSFWorkbook workbook = new XSSFWorkbook();
////		
////			// spreadsheet object
////			XSSFSheet spreadsheet = workbook.createSheet(" Student Data ");
////	
////			// creating a row object
//			XSSFRow row;
//	
//			String excelFilePath = "https://1drv.ms/x/s!AgAEpogDAeQ6qFAUufdP9haAvs-m?e=iC7qUC";
//			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
//			XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(inputStream);
//			
//			XSSFSheet spreadsheet = workbook.getSheetAt(0);
//
//			// This data needs to be written (Object[])
//			Map<String, Object[]> studentData = new TreeMap<String, Object[]>();
//			studentData.put("1", new Object[] { "First Name", "Last Name", "Phone","Email","City","Zip","Street" });
//	
//			studentData.put("2", new Object[] { "Aaqib", "ZapImport", "1234567890","testleadimport01_zurpleqa@mailinator.com","San Diego","92130","11" });
//	
////			studentData.put(
////				"3",
////				new Object[] { "129", "Narayana", "2nd year" });
////	
////			studentData.put("4", new Object[] { "130", "Mohan",
////												"2nd year" });
////	
////			studentData.put("5", new Object[] { "131", "Radha",
////												"2nd year" });
////	
////			studentData.put("6", new Object[] { "132", "Gopal",
////												"2nd year" });
//	
//			Set<String> keyid = studentData.keySet();
//	
//			//int rowid = 0;
//	
//			// writing the data into the sheets...
//			int rowid = spreadsheet.getPhysicalNumberOfRows();
//			for (String key : keyid) {				
//				row = spreadsheet.createRow(rowid++);
//				Object[] objectArr = studentData.get(key);
//				int cellid = 0;
//	
//				for (Object obj : objectArr) {
//					Cell cell = row.createCell(cellid++);
//					cell.setCellValue((String)obj);
//				}
//		}
//	
//			// .xlsx is the format for Excel Sheets...
//			// writing the workbook into the file...
//			FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"/Zapier.xlsx"));
//	
//			workbook.write(out);
//			out.close();
//		} catch(Exception e) {
//			e.getMessage();
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public boolean validateMapResp(RestResponse httpCallResp) throws Exception {
//		// TODO Auto-generated method stub
//		return false;
//	}
//}