package com.zurple.my;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import org.testng.ITestNGListener;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import resources.ZurpleReporter.ReportWriter;
import resources.ZurpleReporter.ReportWriterContainer;
import resources.ZurpleReporter.TestSuiteTitleContainer;
import resources.ZurpleReporter.ZurpleReporter;

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

        public static void main(String[] args) {

            System.setProperty("environment","dev");
            String suitePath = System.getProperty("suite");
            Map<String,List<ZurpleTestNG>> testTree = getTestsList(System.getProperty("user.dir")+ "/" + suitePath);

            ExecutorService service = Executors.newFixedThreadPool(Integer.parseInt(System.getProperty("threads")));

            ReportWriter reportWriter = new ReportWriter("zurple-test-reports");

            for(Map.Entry<String, List<ZurpleTestNG>> entry : testTree.entrySet()) {
                List<ZurpleTestNG> testList = entry.getValue();

                for (ZurpleTestNG o : testList) {
                    service.execute(new TestTask(o, reportWriter));
                }

            }

            service.shutdown();

            try {
                service.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            reportWriter.writeReport();

        }

        private static Map<String,List<ZurpleTestNG>> getTestsList(String scenario){

            List<List<String>> high_level_suites_list = SuiteParser.getSuiteFiles(scenario);

            HashMap<String,List<ZurpleTestNG>> testng_list = new HashMap<>();
            List<Class<? extends ITestNGListener>> listeners = new ArrayList<Class<? extends ITestNGListener>>();
            listeners.add(ZurpleReporter.class);

            for (Integer i = 0; i < high_level_suites_list.get(0).size(); i++)
            {

                //Getting second level suite title
                String second_level_suite_title = SuiteParser.getSuiteTitle(high_level_suites_list.get(0).get(i));

                List<List<String>> second_level_suites_list = SuiteParser.getSuiteFiles(high_level_suites_list.get(0).get(i));

                List<ZurpleTestNG> lst = new ArrayList<>();

                for (Integer j = 0; j < second_level_suites_list.size(); j++)
                {
                    ZurpleTestNG testng = new ZurpleTestNG();
                    testng.setTestSuites(second_level_suites_list.get(j));
                    testng.setListenerClasses(listeners);
                    ((ZurpleTestNG) testng).setSecondLevelTestTitle(second_level_suite_title);
                    lst.add(testng);
                }

                testng_list.put(second_level_suite_title,lst);

            }

            return testng_list;
        }

        public static class TestTask implements Runnable {

            ZurpleTestNG target;

            public TestTask(ZurpleTestNG target, ReportWriter reportWriter) {
                this.target = target;
                //Per-thread test suite title handling
                ReportWriterContainer.setReportWriter(reportWriter);
            }

            @Override
            public void run() {
                TestSuiteTitleContainer.setTestSuiteTitle(target.getSecondLevelTestTitle());
                target.run();
            }
        }

        public static class SuiteParser {

            public static String getSuiteTitle(String file_path)
            {

                String test_case_title = "";

                try {

                    File file = new File(file_path);

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(file);

                    doc.getDocumentElement().normalize();

                    test_case_title = doc.getElementsByTagName("suite").item(0).getAttributes().getNamedItem("name").getNodeValue();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }

                return test_case_title;

            }

            public static List<List<String>> getSuiteFiles(String file_path){

                List<List<String>> res = new ArrayList<List<String>>();

                try {

                    File file = new File(file_path);

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(file);

                    doc.getDocumentElement().normalize();

                    NodeList nList = doc.getElementsByTagName("suite-files");

                    for (Integer i=0; i<nList.getLength(); i++)
                    {

                        List<String> children = new ArrayList<String>();

                        for (Integer j = 0; j < nList.item(i).getChildNodes().getLength(); j++)
                        {
                            Node child = nList.item(i).getChildNodes().item(j);
                            if (child.getNodeType() == Node.ELEMENT_NODE)
                            {
                                children.add(file.getParentFile().getAbsolutePath() + "/" + child.getAttributes().item(0).getNodeValue());
                            }
                        }
                        res.add(children);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }

                return res;

            }
        }

}
