package resources.ZurpleReporter;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.stream.Collectors.toList;

public class ZurpleReporter implements IReporter {

    private static ReportWriter reportWriter;

    private static final Logger LOGGER = LoggerFactory.getLogger(ZurpleReporter.class);

    private static final String ROW_TEMPLATE = "<tr class=\"%s\"><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>";
    private static final String OUTPUT_DIRECTORY = "zurple-test-reports";

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        String reportTemplate = initReportTemplate();

        final String body = suites
                .stream()
                .flatMap(suiteToResults())
                .collect(Collectors.joining());

        try {
            getReportWriter().add(reportTemplate.replaceFirst("</tbody>", String.format("%s</tbody>", body)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static ReportWriter getReportWriter() throws FileNotFoundException, IOException{

        if(reportWriter == null){
            reportWriter = new ReportWriter(OUTPUT_DIRECTORY);
        }

        return reportWriter;
    }

    private Function<ISuite, Stream<? extends String>> suiteToResults() {
        return suite -> suite.getResults().entrySet()
                .stream()
                .flatMap(resultsToRows(suite));
    }

    private Function<Map.Entry<String, ISuiteResult>, Stream<? extends String>> resultsToRows(ISuite suite) {
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

    private List<String> generateReportRows(String testName, String suiteName, Set<ITestResult> allTestResults) {
        return allTestResults.stream()
                .map(testResultToResultRow(testName, suiteName))
                .collect(toList());
    }

    private Function<ITestResult, String> testResultToResultRow(String testName, String suiteName) {
        return testResult -> {
            switch (testResult.getStatus()) {
                case ITestResult.FAILURE:
                    return String.format(ROW_TEMPLATE, "danger", suiteName, testName, testResult.getName(), "FAILED", "NA");

                case ITestResult.SUCCESS:
                    return String.format(ROW_TEMPLATE, "success", suiteName, testName, testResult.getName(), "PASSED", String.valueOf(testResult.getEndMillis() - testResult.getStartMillis()));

                case ITestResult.SKIP:
                    return String.format(ROW_TEMPLATE, "warning", suiteName, testName, testResult.getName(), "SKIPPED", "NA");

                default:
                    return "";
            }
        };
    }

    private String initReportTemplate() {
        String template = null;
        byte[] reportTemplate;
        try {
            reportTemplate = Files.readAllBytes(Paths.get("src/main/java/resources/ZurpleReporter/template/customize-emailable-report-template.html"));
            template = new String(reportTemplate, "UTF-8");
        } catch (IOException e) {
            LOGGER.error("Problem initializing template", e);
        }
        return template;
    }

    private void saveReportTemplate(String outputDirectory, String reportTemplate) {
        new File(outputDirectory).mkdirs();
        try {
            PrintWriter reportWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File(outputDirectory, "my-report.html"))));
            reportWriter.println(reportTemplate);
            reportWriter.flush();
            reportWriter.close();
        } catch (IOException e) {
            LOGGER.error("Problem saving template", e);
        }
    }



}
