package resources.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alert
{

    private String leadLink;
    private String propertyLink;
    private HotAlertsFlags flagsList=new HotAlertsFlags();

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

    public HotAlertsFlags getFlagsList()
    {
        return flagsList;
    }

    public void setFlagsList(HotAlertsFlags flagsList)
    {
        this.flagsList=flagsList;
    }

    /**
     *  Function parses lead id from leadurl
     * @return Integer  lead id
     * @throws Exception if lead id can't be parced
     */
    public Integer getLeadId()
            throws Exception
    {
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(leadLink);
        while (m.find()) {
            return Integer.parseInt(m.group());
        }
        throw new Exception("Can't parce lead id");
    }
}
