package resources;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import resources.alerts.BootstrapModal;
import resources.classes.Asset;
import resources.utility.AutomationLogger;

public abstract class AbstractPageTest extends AbstractTest
{

    protected TestEnvironment environment;
    protected AbstractPage page;
    protected String source_in_url="";
    protected Boolean incognito=false;
    private Long threadID;
    private boolean isProd = false;

    public abstract AbstractPage getPage();

    public abstract void clearPage();

    public WebDriver getDriver(){
        Long thread_id = Thread.currentThread().getId();
        WebDriver driver = EnvironmentFactory.getDriver(thread_id);
        setThreadId(thread_id);
        setIsProd();
        return driver;
    }
    private void setIsProd() {
    	if(System.getProperty("environment").equalsIgnoreCase("prod") || System.getProperty("environment").equalsIgnoreCase("autoconvoprod")){
    		isProd = true; 
    	}
    }
    public boolean getIsProd() {
    	return isProd;
    }
    private void setThreadId(Long pThreadId) {
    	threadID = pThreadId;
    }
    public String getThreadId() {
    	return threadID.toString();
    }
    @Parameters({"source_in_url","incognito"})
    @BeforeTest
    public void globalSetUp(@Optional("") String source_in_url, @Optional("") String incognito){
        this.source_in_url = source_in_url;
        this.incognito = Boolean.parseBoolean(incognito);
    }

    @BeforeClass
    public void clearingPageObject(){
        clearPage();
    }

    @BeforeTest
    public void settingUpDriver(){
        getDriver();
    }

    protected boolean checkAssetsVersion(List<Asset> assets){
        for (Asset asset: getPage().getAssets()) {

            //We should check only self-hosted assets
            Boolean checkFlag = false;
            Pattern pattern_domain = Pattern.compile("^(?:https?:\\/\\/)?(?:[^@\\n]+@)?(?:www\\.)?([^:\\/\\n]+)");
            Matcher matcher_domain = pattern_domain.matcher(asset.getUrl());
            if (matcher_domain.find()) {
                String domain = matcher_domain.group();
                //We should check only self-hosted assets
                if(domain.contains("zurple.com") || domain.contains("zengtest")){
                    checkFlag = true;
                }

            }


            //We shouln't check ckeditor dynamically loaded assets
            Pattern pattern_ckeditor = Pattern.compile("\\/ckeditor\\/");
            Matcher matcher_ckeditor = pattern_ckeditor.matcher(asset.getUrl());


            Pattern pattern_version = Pattern.compile("\\?v=\\d+$");
            Matcher matcher_version = pattern_version.matcher(asset.getUrl());

            if(
                    checkFlag &&
                    !matcher_ckeditor.find() &&
                    !matcher_version.find()
            ){
                return false;
            }
        }
        return true;
    }
    
    
    protected String updateEmail(String pEmail) {
//    	Date dateObj = new Date();
//		long date_to_append=dateObj.getTime()/3600;
		String date_to_append = getCurrentPSTTime().replace("-", "");
		int at = pEmail.indexOf('@');
		String firstPart = pEmail.substring(0, at);
		String lastPart = pEmail.substring(at);
//		pEmail=firstPart+"_"+Long.toString(date_to_append)+lastPart;
		pEmail=date_to_append+generateRandomInt(1000)+"-"+firstPart+lastPart;
		return pEmail;
    }
    
    protected String updateName(String pName) {
//    	Date dateObj = new Date();
//		long date_to_append=dateObj.getTime()/3600;
    	String date_to_append = getCurrentPSTTime().replace("-", "");
//		pName=pName+" "+Long.toString(date_to_append);
		pName=date_to_append+generateRandomInt(1000)+" "+pName;
		return pName;
    }
    public void closeBootStrapModal() {
    	BootstrapModal bootstrapModalObj = new BootstrapModal(getPage().getWebDriver());

    	if(bootstrapModalObj.checkBootsrapModalIsShown()){
        	bootstrapModalObj.getBootstrapModal().close();
        	bootstrapModalObj.clearBootstrapModal();
        }
    }
    protected JSONObject getDataFile(String pDataFile) {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonDatObject = new JSONObject();
		AutomationLogger.info("Reading Data File: "+pDataFile);
		try (FileReader reader = new FileReader(System.getProperty("user.dir")+pDataFile))
		{
			//Read JSON file
			Object obj = jsonParser.parse(reader);
			jsonDatObject = new JSONObject(obj.toString());
		}catch(Exception ex) {
			AutomationLogger.fatal("Unable to read data file "+pDataFile);
		}
		return jsonDatObject;
	}
    
    protected void writeJsonToFile(String pFileToWrite, JSONObject pObjectToWrite) {
    	File lFilePath = new File(System.getProperty("user.dir")+pFileToWrite);
    	boolean isEmptyFile = lFilePath.length()==0?true:false;
    	try (FileWriter file = new FileWriter(lFilePath,true)) {
    		AutomationLogger.info("Writing json to file "+pFileToWrite);
    		if(!isEmptyFile) {
    			file.write(",");
    		}
    		file.write(toPrettyFormat(pObjectToWrite.toString()));
    		file.flush();

    	} catch (IOException e) {
    		AutomationLogger.fatal("Unable to write file "+pFileToWrite);
    	}
    }
    protected void writeStringToFile(String pFileToWrite, String pObjectToWrite) {
    	File lFilePath = new File(System.getProperty("user.dir")+pFileToWrite);
    	try (FileWriter file = new FileWriter(lFilePath,true)) {
    		AutomationLogger.info("Writing json to file "+pFileToWrite);
    		file.write(pObjectToWrite);
    		file.flush();

    	} catch (IOException e) {
    		AutomationLogger.fatal("Unable to write file "+pFileToWrite);
    	}
    }
    protected void emptyFile(String pFileToWrite, String pObjectToWrite) {
    	File lFilePath = new File(System.getProperty("user.dir")+pFileToWrite);
    	try {
    		PrintWriter writer = new PrintWriter(lFilePath);
    		writer.print("");
    		writer.close();

    	} catch (IOException e) {
    		AutomationLogger.fatal("Unable to write file "+pFileToWrite);
    	}
    }
    protected void writeJsonArrayToFile(String pFileToWrite, JSONArray pObjectToWrite) {
    	File lFilePath = new File(System.getProperty("user.dir")+pFileToWrite);
    	boolean isEmptyFile = lFilePath.length()==0?true:false;
    	try (FileWriter file = new FileWriter(lFilePath,true)) {
    		AutomationLogger.info("Writing json to file "+pFileToWrite);
    		if(!isEmptyFile) {
    			file.write(",");
    		}
    		file.write(arrayToPrettyFormat(pObjectToWrite.toString()));
    		file.flush();

    	} catch (IOException e) {
    		AutomationLogger.fatal("Unable to write file "+pFileToWrite);
    	}
    }
    private String toPrettyFormat(String pJsonString) 
    {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(pJsonString).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;
    }
    private String arrayToPrettyFormat(String pJsonArray) 
    {
        JsonParser parser = new JsonParser();
        JsonArray jArray = parser.parse(pJsonArray).getAsJsonArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(jArray);

        return prettyJson;
    }
    protected void writePojoToJsonFile(Object pPojoObject, String pFileToWrite) {
    	ObjectMapper mapper = new ObjectMapper(); 
        /**
         * Write object to file
         */
        try {
        	 final String json1 = mapper.writeValueAsString(pPojoObject);
//            mapper.writeValue(new File(pFileToWrite), pPojoObject);//Plain JSON
        	 writeJsonToFile(pFileToWrite, new JSONObject(json1));
//            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(System.getProperty("user.dir")+pFileToWrite), pPojoObject);//Prettified JSON
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean closeBootStrapModal(WebDriver pWebDriver) {
    	return new BootstrapModal(pWebDriver).closeBootstrapModal();
    }
    //Month/Day/Year
    //12/24/2019
    protected String getTodaysDate() {
    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
    	String formattedDate = dateFormat.format(new Date(System.currentTimeMillis() + 7200000)).toString().toLowerCase();
    	
//    	Instant timeStamp= Instant.now();
//        System.out.println("Machine Time Now:" + timeStamp);
//     
//        //timeStamp in zone - "America/Los_Angeles"
//        ZonedDateTime LAZone= timeStamp.atZone(ZoneId.of("America/Los_Angeles"));
//        System.out.println("In Los Angeles(America) Time Zone:"+ LAZone);
//        
//    	String lDate = "";
//  
//    	String tempDate[] = LAZone.toString().split("T")[0].split("-");
//    	lDate = tempDate[1]+"/"+tempDate[2]+"/"+tempDate[0];
//    	
//    	return lDate;
    	return formattedDate;
    }
  //Month/Day/Year
    //12/24/2019
    protected String getTomorrowsDate() {
//    	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//    	dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
//    	String formattedDate = dateFormat.format(new Date(System.currentTimeMillis() + 86400000)).toString().toLowerCase();
    	Instant timeStamp= Instant.now();
        System.out.println("Machine Time Now:" + timeStamp);
     
        //timeStamp in zone - "America/Los_Angeles"
        ZonedDateTime LAZone= timeStamp.atZone(ZoneId.of("America/Los_Angeles")).plusDays(2);
        System.out.println("In Los Angeles(America) Time Zone:"+ LAZone);
        
    	String lDate = "";
  
    	String tempDate[] = LAZone.toString().split("T")[0].split("-");
    	lDate = tempDate[1]+"/"+tempDate[2]+"/"+tempDate[0];
 
    	return lDate;
    }
  //Add two hours to current PST time
    //05:00 pm
    protected String getPSTTime() {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("hh aa");
    	dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
    	String formattedDate = dateFormat.format(new Date(System.currentTimeMillis() + 7200000)).toString().toLowerCase();
    	return formattedDate.replace(" ", ":00 ");
    	
    }
    
    protected String getCurrentAndNextDayOfTheWeek() {
    	Instant timeStamp= Instant.now();
    	ZonedDateTime LAZone= timeStamp.atZone(ZoneId.of("America/Los_Angeles"));
    	//LocalDate date = LocalDate.now();
    	DayOfWeek dow = LAZone.getDayOfWeek();
    	System.out.println("Enum = " + dow);
    	String dayName = dow.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    	System.out.println("FULL = " + dayName);
    	LAZone = LAZone.plus(1, ChronoUnit.DAYS);
    	dow = LAZone.getDayOfWeek();
    	String dayName2 = dow.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    	
    	return dayName+","+dayName2;
    	
    }
    
    protected String getCurrentPSTTime() {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-ss");
    	dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
    	String formattedDate = dateFormat.format(new Date(System.currentTimeMillis())).toString().toLowerCase();
    	return formattedDate;
    	
    }
    //Give 0 if you dont want to add days in todays date
    protected String getTodaysDate(int pDays) {
    	Date date = new Date();
    	SimpleDateFormat df  = new SimpleDateFormat("MM/dd/YYYY");
    	Calendar c1 = Calendar.getInstance();
    	String currentDate = df.format(date);// get current date here
    	
    	if(pDays>0) {
        	c1.add(Calendar.DAY_OF_YEAR, pDays);
        	df = new SimpleDateFormat("MM/dd/YYYY");
        	Date resultDate = c1.getTime();
        	currentDate = df.format(resultDate);
    	}
    	return currentDate;
    	
    }
    protected String getTodaysDate(int pDays, String pFormat) {
    	Date date = new Date();
    	SimpleDateFormat df  = new SimpleDateFormat(pFormat);
    	Calendar c1 = Calendar.getInstance();
    	String currentDate = df.format(date);// get current date here
    	
    	if(pDays>0) {
        	c1.add(Calendar.DAY_OF_YEAR, pDays);
        	df = new SimpleDateFormat("MM/dd/YYYY");
        	Date resultDate = c1.getTime();
        	currentDate = df.format(resultDate);
    	}
    	return currentDate;
    	
    }
    
    protected String getTodaysDateInPST(int pDays, String pFormat) {
    	Date date = new Date();
    	SimpleDateFormat df  = new SimpleDateFormat(pFormat);
    	df.setTimeZone(TimeZone.getTimeZone("PST"));
    	Calendar c1 = Calendar.getInstance();
    	String currentDate = df.format(date);// get current date here
    	
    	if(pDays>0) {
        	c1.add(Calendar.DAY_OF_YEAR, pDays);
        	df = new SimpleDateFormat("MM/dd/YYYY");
        	Date resultDate = c1.getTime();
        	currentDate = df.format(resultDate);
    	}
    	return currentDate;	
    }
    
    protected static int generateRandomInt(int pUpperRange){
    	Random random = new Random();
    	int lNum = random.nextInt(pUpperRange);
    	if(lNum==pUpperRange) {
    		lNum = lNum - 1;
    	}
    	return lNum;
    }
    protected String getCuurentTime() {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
    	String formattedDate = dateFormat.format(new Date(System.currentTimeMillis())).toString().toLowerCase();
    	return formattedDate;
    	
    }
    
    // Month/Day/Year
    public String getYesterdaysDate() {
		String lDate = "";
		LocalDate today = LocalDate.now().minusDays(1);
		String tempDate[] = today.toString().split("-");
		lDate = tempDate[1]+"/"+tempDate[2]+"/"+tempDate[0];
		return lDate; //08/18/2020
	}
    
    public String setScheduledPostDate() {
		String lDate = "";
		LocalDate today = LocalDate.now();
		String tempDate[] = today.toString().split("-");
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 3);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		lDate = tempDate[0]+"-"+tempDate[1]+"-"+tempDate[2]+" "+df.format(now.getTime());
		return lDate; //2020-08-19 11:50
	}
    
    public void closeCurrentBrowser() {
    	 Long thread_id = Thread.currentThread().getId();
         WebDriver driver = EnvironmentFactory.getDriver(thread_id);
         driver.quit();
         EnvironmentFactory.webDrivers.remove(thread_id);
    }
    
    public String getDataFileContentJsonArray(String pDataFile) throws IOException {
        String data = ""; 
        data = new String(Files.readAllBytes(Paths.get(pDataFile))); 
        return "["+data+"]"; 
    }
    
    public String convertToJSONArray(String pDataFile) throws IOException {
        String data = ""; 
        data = new String(Files.readAllBytes(Paths.get(pDataFile))); 
        String output = "{"+"\"data\":"+"["+data+"]"+"}";
        return output; 
    }

}
