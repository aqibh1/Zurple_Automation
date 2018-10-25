package resources.ZurpleReporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportWriter {

    private static FileWriter fileWriter;
    private String outputDirectory;
    private Map<String,List<String>> reportsList = new HashMap<>();
    private static final String HEADER_TEMPLATE = "<h3>%s</h3>";

    private static final String REPORT_FILE_NAME = "custom-emailable-report";

    private FileWriter get_file_writer()
    {
        if (fileWriter == null)
        {
            File targetFile = new File(outputDirectory + "/" + REPORT_FILE_NAME + "" + ".html");
            try {
                fileWriter = new FileWriter(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return fileWriter;
    }

    public ReportWriter(String outputDirectory){
        this.outputDirectory = outputDirectory;
    }

    public void add(String caseTitle, String customReportTemplateStr)
    {

        if ( reportsList.containsKey(caseTitle) )
        {
            reportsList.get(caseTitle).add(customReportTemplateStr);
        }
        else
        {
            List<String> new_list = new ArrayList();
            new_list.add(customReportTemplateStr);
            reportsList.put(caseTitle,new_list);
        }

    }

    public void writeReport()
    {

        FileWriter fw = get_file_writer();

        for(Map.Entry<String, List<String>> entry : reportsList.entrySet()) {
            String title = entry.getKey();

            String caseHeader = String.format(HEADER_TEMPLATE, title);
            try {
                fw.write(caseHeader);
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<String> lst = entry.getValue();

            for (String report: lst)
            {

                try {
                    fw.write(report);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        try {
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
