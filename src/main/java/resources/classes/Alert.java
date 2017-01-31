package resources.classes;

import java.util.ArrayList;
import java.util.List;

public class Alert
{

    private String leadLink;
    private String propertyLink;
    private List<String> flagsList=new ArrayList<String>();

    public String getPropertyLink()
    {
        return propertyLink;
    }

    public void setPropertyLink(String propertyLink)
    {
        this.propertyLink = propertyLink;
    }

    public String getLeadLink()
    {
        return leadLink;
    }

    public void setLeadLink(String leadLink)
    {
        this.leadLink = leadLink;
    }

    public List<String> getFlagsList()
    {
        return flagsList;
    }

    public void addFlag(String flag)
    {
        this.flagsList.add(flag);
    }
}
