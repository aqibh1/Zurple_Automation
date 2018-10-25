package resources.ZurpleReporter;

public class ReportWriterContainer {

    public static ReportWriter reportWriter;

    public static void setReportWriter(ReportWriter writer)
    {
        reportWriter = writer;
    }

    public static ReportWriter getReportWriter()
    {
        return reportWriter;
    }

}