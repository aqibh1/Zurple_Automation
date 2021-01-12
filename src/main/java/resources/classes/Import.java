package resources.classes;

import java.util.Date;

public class Import
{

    private String fileName;
    private Integer dataRows;
    private Integer newLeads;
    private Integer ignoredLeads;
    private String importer;
    private Date date;

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public Integer getDataRows()
    {
        return dataRows;
    }

    public void setDataRows(Integer dataRows)
    {
        this.dataRows = dataRows;
    }

    public Integer getNewLeads()
    {
        return newLeads;
    }

    public void setNewLeads(Integer newLeads)
    {
        this.newLeads = newLeads;
    }

    public Integer getIgnoredLeads()
    {
        return ignoredLeads;
    }

    public void setIgnoredLeads(Integer ignoredLeads)
    {
        this.ignoredLeads = ignoredLeads;
    }

    public String getImporter()
    {
        return importer;
    }

    public void setImporter(String importer)
    {
        this.importer = importer;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
}
