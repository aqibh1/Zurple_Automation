package com.zurple.my;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import org.testng.TestNG;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

        static String high_level_suites_path = "/src/test/resources/TestSuites/Scenarios/";

        public static void main(String[] args) {

            System.setProperty("environment","dev");

            List<TestNG> testList = getTestsList(System.getProperty("user.dir") + high_level_suites_path + "PreReleaseSmokeTests.xml");

            ExecutorService service = Executors.newFixedThreadPool(3);

            for (TestNG o : testList) {
                service.execute(new MyTask(o));
            }

            service.shutdown();
            try {
                service.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        private static List<TestNG> getTestsList(String scenario){

            List<List<String>> high_level_suites_list = SuiteParser.getSuiteFiles(scenario);

            List<TestNG> testng_list = new ArrayList<TestNG>();
            for (Integer i = 0; i < high_level_suites_list.get(0).size(); i++)
            {
                List<List<String>> second_level_suites_list = SuiteParser.getSuiteFiles(high_level_suites_list.get(0).get(i));
                for (Integer j = 0; j < second_level_suites_list.size(); j++)
                {
                    TestNG testng = new TestNG();
                    testng.setTestSuites(second_level_suites_list.get(j));
                    testng_list.add(testng);
                }
            }

            return testng_list;
        }

        public static class MyTask implements Runnable {
            TestNG target;

            public MyTask(TestNG target) {
                this.target = target;
            }

            @Override
            public void run() {
                target.run();
            }
        }

        public static class SuiteParser {

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
                                children.add(System.getProperty("user.dir") + high_level_suites_path + child.getAttributes().item(0).getNodeValue());
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
