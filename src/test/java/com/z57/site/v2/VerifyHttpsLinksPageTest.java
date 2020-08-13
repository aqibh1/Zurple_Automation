/**
 * 
 */
package com.z57.site.v2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.conn.Wire;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.opencsv.CSVWriter;
import com.restapi.HTTPSRequestConnector;

/**
 * @author adar
 *
 */
public class VerifyHttpsLinksPageTest extends PageTest{
	private HomePage page;
	private WebDriver driver;
	File file;
	List<String> url_list;

	// create FileWriter object with file as parameter 
	FileWriter outputfile;

	// create CSVWriter object filewriter object as parameter 
	CSVWriter writer;

	@Override
	public void testTitle() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testHeader() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testBrand() {
		// TODO Auto-generated method stub

	}

	@Override
	public Page getPage(){
		if(page == null){
			page = new HomePage(getDriver());
			page.setUrl("");
			page.setDriver(getDriver());
			driver = getDriver();
			System.out.println(driver.getTitle());
			System.out.println(driver.getCurrentUrl());
			System.out.println(driver.manage().window().getSize());
			url_list = new ArrayList<String>();
		}
		return page;
	}
	private Page getPage(String pUrl) {
		
			driver = getDriver();
			page = new HomePage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
			System.out.println(driver.getTitle());
			System.out.println(driver.getCurrentUrl());
			System.out.println(driver.manage().window().getSize());
			url_list = new ArrayList<String>();
		
		return page;
		
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub

	}
	private void setCsvWriter(String pFilePath) throws IOException {
		try {
			file = new File(pFilePath); 
			outputfile = new FileWriter(file); 
			writer = new CSVWriter(outputfile);
		}
		catch(Exception ex) {
			System.out.println("");
		}
	}

	@Test
	public void testVerifyHttpsLinks() throws IOException, KeyManagementException, NoSuchAlgorithmException {
		System.setProperty("jsse.enableSNIExtension", "false");
//		getPage("");
//		getPage("/listings");
//		getPage("/listings/sold-listings");
//		getPage("/idx");
//		getPage("/recent-home-sales");
//		getPage("/community-reports");
//		getPage("/school-reports");
//		getPage("/points-of-interest");
//		getPage("/category/real-estate-news");
//		getPage("/contact-me ");
		getPage("/about-me");
//		getPage("/buyers");
//		getPage("/sellers");
		
		setCsvWriter(System.getProperty("user.dir")+"/http_link_report/report.csv");
		String[] lHeader = {"Page","HTTPS","Source","URL","Status"};
		writeCSVFile(lHeader);
	
		List<WebElement> a_href_links = driver.findElements(By.tagName("a"));
		verifyTheLinks(a_href_links,"href");
		List<WebElement> img_src_links = driver.findElements(By.tagName("img"));
		verifyTheLinks(img_src_links,"src");
		List<WebElement> link_href_links = driver.findElements(By.tagName("link"));
		verifyTheLinks(link_href_links,"href");
		List<WebElement> script_src_links = driver.findElements(By.tagName("script"));
		verifyTheLinks(script_src_links,"src");
		
		writer.close();

	}
	

	private void verifyTheLinks(List<WebElement> pListOfElements, String pAttributeToFind) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		int counter=0;
		for(WebElement element: pListOfElements) {
			System.out.println("Url Count :: "+counter);
			String lUrl = element.getAttribute(pAttributeToFind);
			if(lUrl!=null && !lUrl.isEmpty()) {
				System.out.println("URL :: "+lUrl);
				verifyValidLinks(lUrl,pAttributeToFind);

			}
			counter++;
		}
	}

	private void verifyValidLinks(String pUrl, String pSource) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		boolean isHttp= false;
		
//		boolean invalidUrl =false;
		if(!isUrlExist(pUrl)) {
			url_list.add(pUrl);
			if(!pUrl.startsWith("https:") && pUrl.startsWith("http:")) {
				isHttp = true;
				execute(pUrl, pSource, isHttp);
			}else if(!pUrl.startsWith("http:") && pUrl.startsWith("https:")) {
				execute(pUrl, pSource, isHttp);
			}
		}
	}

	private void writeCSVFile(String[] pData) {
		writer.writeNext(pData);
	}
	
	private boolean isUrlExist(String pUrl) {
		for(String url: url_list) {
			if(url.equalsIgnoreCase(pUrl)) {
				return true;
			}
		}
		return false;
	}
	private void execute(String pUrl,String pSource, boolean pIsHttp) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		
		URL lUrl = new URL(pUrl);
		HTTPSRequestConnector httpConnector = new HTTPSRequestConnector();
		httpConnector.setUrl(lUrl);
		httpConnector.setRequestType("GET");
		if(httpConnector.setConnection()) {

			if(httpConnector.getStatus()!=200 && httpConnector.getStatus()!=403) {
				String[] dataArray = {"Featured Properties",String.valueOf(!pIsHttp),pSource,pUrl,String.valueOf(httpConnector.getStatus())};
				writeCSVFile(dataArray);
			}
		}else {
			String[] dataArray = {"Featured Properties",String.valueOf(!pIsHttp),pUrl,"0"};
			writeCSVFile(dataArray);
		}
	}
}
