package resources.orm;

import java.io.Serializable;

public class Lead
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long leadId;
    private String leadCode;
    private String leadName;

    public Long getLeadId()
    {
        return leadId;
    }

    public void setLeadId(Long leadId)
    {
        this.leadId = leadId;
    }

    public String getLeadCode()
    {
        return leadCode;
    }

    public void setLeadCode(String leadCode)
    {
        this.leadCode = leadCode;
    }

    public String getLeadName()
    {
        return leadName;
    }

    public void setLeadName(String leadName)
    {
        this.leadName = leadName;
    }

}
