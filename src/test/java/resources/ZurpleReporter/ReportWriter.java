package resources.ZurpleReporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import resources.ConfigReader;

public class ReportWriter {

    private static FileWriter fileWriter;
    private String outputDirectory;
    private Map<String,List<List<HashMap>>> reportsList = new HashMap<>();

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

    public void add(String caseTitle, List<HashMap> results)
    {

        if ( reportsList.containsKey(caseTitle) )
        {
            reportsList.get(caseTitle).add(results);
        }
        else
        {
            List<List<HashMap>> l = new ArrayList<>();
            l.add(results);
            reportsList.put(caseTitle,l);
        }

    }

    public void writeReport()
    {

        FileWriter fw = get_file_writer();

        try {
            fw.write(render());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String render(){

        ConfigReader configReader = ConfigReader.load();
        String result = "";

        try {
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(configReader.getPropertyByName("zurple_reporter_templates_dir")));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            Template temp = cfg.getTemplate("reportTemplate.ftlh");

            Writer out = new StringWriter();
            try {
                HashMap<String, Map> m = new HashMap<>();
                m.put("cases",reportsList);
                temp.process(m, out);
                result = out.toString();
            } catch (TemplateException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
