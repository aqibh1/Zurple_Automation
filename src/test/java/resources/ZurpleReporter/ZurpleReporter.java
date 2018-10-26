package resources.ZurpleReporter;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.stream.Collectors.toList;

public class ZurpleReporter implements IReporter {

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        List<HashMap> results = suites
                .stream()
                .flatMap(suiteToResults())
                .collect(Collectors.toList());

        ReportWriterContainer.getReportWriter().add(TestSuiteTitleContainer.getTestSuiteTitle(), results);

    }

    private Function<ISuite, Stream<? extends HashMap>> suiteToResults() {
        return suite -> suite.getResults().entrySet()
                .stream()
                .flatMap(resultsToRows(suite));
    }

    private Function<Map.Entry<String, ISuiteResult>, Stream<? extends HashMap>> resultsToRows(ISuite suite) {
        return e -> {
            ITestContext testContext = e.getValue().getTestContext();

            Set<ITestResult> failedTests = testContext
                    .getFailedTests()
                    .getAllResults();
            Set<ITestResult> passedTests = testContext
                    .getPassedTests()
                    .getAllResults();
            Set<ITestResult> skippedTests = testContext
                    .getSkippedTests()
                    .getAllResults();

            String suiteName = suite.getName();

            return Stream
                    .of(failedTests, passedTests, skippedTests)
                    .flatMap(results -> generateReportRows(e.getKey(), suiteName, results).stream());
        };
    }

    private List<HashMap> generateReportRows(String testName, String suiteName, Set<ITestResult> allTestResults) {
        return allTestResults.stream()
                .map(testResultToResultRow(testName, suiteName))
                .collect(toList());
    }

    private Function<ITestResult, HashMap<String,String>> testResultToResultRow(String testName, String suiteName) {

        HashMap<String,String> m = new HashMap<String,String>();

        return testResult -> {
            switch (testResult.getStatus()) {
                case ITestResult.FAILURE:
                    m.put("suiteName",suiteName);
                    m.put("testName",testName);
                    m.put("methodName",testResult.getName());
                    m.put("testResultCode","FAILED");
                    m.put("time","NA");
                    m.put("screenshot",ScreenshotTaker.getScreenshotName(testResult));
                    return m;

                case ITestResult.SUCCESS:
                    m.put("suiteName",suiteName);
                    m.put("testName",testName);
                    m.put("methodName",testResult.getName());
                    m.put("testResultCode","PASSED");
                    m.put("time",String.valueOf(testResult.getEndMillis() - testResult.getStartMillis()));
                    m.put("screenshot","");
                    return m;

                case ITestResult.SKIP:
                    m.put("suiteName",suiteName);
                    m.put("testName",testName);
                    m.put("methodName",testResult.getName());
                    m.put("testResultCode","SKIPPED");
                    m.put("time","NA");
                    m.put("screenshot","");
                    return m;
                default:
                    return m;
            }
        };
    }

}
