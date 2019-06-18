package resources;

import java.io.FileReader;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import resources.alerts.BootstrapModal;
import resources.classes.Asset;
import resources.utility.AutomationLogger;

public abstract class AbstractPageTest extends AbstractTest
{
    protected AbstractPage page;
    protected String source_in_url="";
    protected Boolean incognito=false;
    private Long threadID;

    public abstract AbstractPage getPage();

    public abstract void clearPage();

    public WebDriver getDriver(){
        Long thread_id = Thread.currentThread().getId();
        WebDriver driver = EnvironmentFactory.getDriver(thread_id);
        setThreadId(thread_id);
        return driver;
    }

    private void setThreadId(Long pThreadId) {
    	threadID = pThreadId;
    }
    public Long getThreadId() {
    	return threadID;
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
    	Date dateObj = new Date();
		long date_to_append=dateObj.getTime()/3600;
		int at = pEmail.indexOf('@');
		String firstPart = pEmail.substring(0, at);
		String lastPart = pEmail.substring(at);
		pEmail=firstPart+"_"+Long.toString(date_to_append)+lastPart;
		return pEmail;
    }
    
    protected String updateName(String pName) {
    	Date dateObj = new Date();
		long date_to_append=dateObj.getTime()/3600;
		pName=pName+" "+Long.toString(date_to_append);
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
}
