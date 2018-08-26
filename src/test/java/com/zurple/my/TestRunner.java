package com.zurple.my;

import com.google.common.base.Charsets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;

import org.apache.commons.io.IOUtils;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.log4testng.Logger;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.crypto.dsig.XMLObject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * todo
 *
 * @author Vladimir
 */
public class TestRunner
{

    String high_level_suites_path = "TestSuites/Workflows";

    public static void main(String[] args) {

        System.setProperty("environment","dev");

        List<String> suites = Lists.newArrayList();
        suites.add("src/test/resources/TestSuites/zengtest1/Actions/Register new lead.xml");
        suites.add("src/test/resources/TestSuites/zurple/Actions/SetLeadValidationStatusCorrect.xml");
        suites.add("src/test/resources/TestSuites/zurple/Actions/Login.xml");
        suites.add("src/test/resources/TestSuites/zurple/Actions/SendMassEmailToNewLeads.xml");
        suites.add("src/test/resources/TestSuites/zurple/Actions/ProcessMassEmailQueue.xml");
        suites.add("src/test/resources/TestSuites/Workflows/email_alerts/UserReceivedMassEmail.xml");
        suites.add("src/test/resources/TestSuites/Common/Close Browser.xml");

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        TestNG testng = new TestNG();
        testng.setTestSuites(suites);
        testng.run();
        suites.clear();

    }

    private File[] getHighLevelTestSuitesList(){
        File folder = new File(System.getProperty("user.dir") + high_level_suites_path);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }

    /*private List<XMLObject> getSuiteFilesList(File suite){
        try {
            //we can create JDOM Document from DOM, SAX and STAX Parser Builder classes
            org.jdom2.Document jdomDoc = useDOMParser(suite);
            Element root = jdomDoc.getRootElement();
            List<Element> empListElements = root.getChildren("suite-files");
            List<String> empList = new ArrayList<>();
            for (Element empElement : empListElements) {
                Employee emp = new Employee();
                emp.setId(Integer.parseInt(empElement.getAttributeValue("id")));
                emp.setAge(Integer.parseInt(empElement.getChildText("age")));
                emp.setName(empElement.getChildText("name"));
                emp.setRole(empElement.getChildText("role"));
                emp.setGender(empElement.getChildText("gender"));
                empList.add(emp);
            }
            //lets print Employees list information
            for (Employee emp : empList)
                System.out.println(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    //Get JDOM document from DOM Parser
    private static org.jdom2.Document useDOMParser(File file)
            throws ParserConfigurationException, SAXException, IOException {
        //creating DOM Document
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        DOMBuilder domBuilder = new DOMBuilder();
        return domBuilder.build(doc);

    }

    //private List<String> getlistOfSuites

}
