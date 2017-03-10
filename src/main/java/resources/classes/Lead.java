package resources.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lead
{

    private String leadLink;
    private String name;
    private String phone;
    private String email;
    private String searchLocation;
    private Date lastVisit;
    private Date dateCreated;
    private String agent;
    private String priorityRank;
    private HotAlertsFlags hotAlertsFlags;

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
        return hotAlertsFlags;
    }

    public void setFlagsList(HotAlertsFlags hotAlertsFlags)
    {
        this.hotAlertsFlags=hotAlertsFlags;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSearchLocation()
    {
        return searchLocation;
    }

    public void setSearchLocation(String searchLocation)
    {
        this.searchLocation = searchLocation;
    }

    public Date getLastVisit()
    {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit)
    {
        this.lastVisit = lastVisit;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public String getAgent()
    {
        return agent;
    }

    public void setAgent(String agent)
    {
        this.agent = agent;
    }

    public String getPriorityRank()
    {
        return priorityRank;
    }

    public void setPriorityRank(String priorityRank)
    {
        this.priorityRank = priorityRank;
    }
}
