package resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import resources.utility.AutomationLogger;

public class FailedTestsListener implements ISuiteListener{
	FileWriter myWriter = null;

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ISuite suite) {
		List<String> failedTestList = TestRailAndExtentReportListener.getFailedTestList();	
//		List<String> failedTestList = new ArrayList<String>();
//		failedTestList.add("Schedule showing listing");
//		failedTestList.add("Search and Verify property by Price City Features");
//		failedTestList.add("Track Property in Lead Details");
//		failedTestList.add("Verify Sold Homes search");
//		failedTestList.add("Verify Home Value page is populated and validation is working");

		if(failedTestList.size()>0) {
			getNormalizeXMLDocument(failedTestList);
			String failed_xml = convertXMLToPrettyFormat();
			setWriter("failedTestFormatted.xml");
			writeToFile(failed_xml,true);
		}
	}
	
	private void getNormalizeXMLDocument(List<String> failedTestList) {
		List<String> content_list = new ArrayList<String>();
		BufferedReader r = null;
		try{  
			String xml_file = System.getProperty("suite");
			String l_XMLFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\"+xml_file;
			//creating a constructor of file class and parsing an XML file  
			File file = new File(l_XMLFilePath);  
			// In looping through files:
			// Step 2:
			
			boolean lFindTestEndTag = false;
			String testNode = "";
//			setWriter("failedTest.xml");
//			writeToFile("<doc>",false);
			for(String stringToFind: failedTestList) {
				r = new BufferedReader(new FileReader(file)); 
				// Step 3:
				String lineContent = r.readLine();
				testNode = "";
				while (lineContent != null) {
					if (lineContent.contains(stringToFind)) {
						lFindTestEndTag = true;
					}
					if(lFindTestEndTag) {
						testNode=testNode+"\n"+lineContent.trim();
						if(lineContent.contains("</test>")) {
							//write in file
							lFindTestEndTag = false;
							AutomationLogger.info(testNode);
//							writeToFile(testNode,false);
							content_list.add(testNode);
							break;
						}
					}
					lineContent = r.readLine();
				}
			}
			r.close();
		}catch(Exception ex) {
			System.out.print(ex.getLocalizedMessage());
		}
//		writeToFile("</doc>",true);;
		writeAllTheContentInXml(content_list);
	}
	
	private void writeAllTheContentInXml(List<String> content_list) {
		setWriter("failedTest.xml");
		writeToFile("<doc>", false);
		for(String strToWrite: content_list) {
			writeToFile(strToWrite,false);
		}
		writeToFile("</doc>",true);
	}

	private String convertXMLToPrettyFormat() {
		DocumentBuilder db;
		Document doc = null;
		String xmlString = "";
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = db.parse(Paths.get("failedTest.xml").toFile());
			Transformer transformer;
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			// initialize StreamResult with File object to save to file
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
			xmlString = result.getWriter().toString();
		} catch (TransformerFactoryConfigurationError | TransformerException | SAXException | IOException | ParserConfigurationException e) {
			AutomationLogger.error(e.getLocalizedMessage());
			e.printStackTrace();
		} 
		System.out.println(xmlString);
		return xmlString;

	}
	private void setWriter(String pFileName) {
		try {
			myWriter = new FileWriter(pFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void writeToFile(String pStringToWrite, boolean pClose) {
		try {
			myWriter.write(pStringToWrite);
			if(pClose) {
				myWriter.close();
			}
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	private void appendAtStart(File pFile, String pStringToAppend) {
		try {
			FileUtils.writeStringToFile(pFile, pStringToAppend, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
