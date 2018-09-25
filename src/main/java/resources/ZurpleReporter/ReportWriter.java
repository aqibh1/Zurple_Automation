package resources.ZurpleReporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportWriter {

    private static FileWriter fileWriter;
    private String outputDirectory;

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

    public void add(String customReportTemplateStr)
    {
        FileWriter fw = get_file_writer();

        try {
            fw.write(customReportTemplateStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void close_file()
    {
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
