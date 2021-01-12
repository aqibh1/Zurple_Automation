package resources.classes;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lead
{

    private String leadLink;
    private String firstName;
    private String lastName;
    private String userName;
    private String phone;
    private String cellPhone;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String priceLow;
    private String priceHigh;
    private String bedrooms;
    private Integer adminId;
    private Integer siteId;
    private String notes;
    private Boolean isBuyer;
    private Boolean isSeller;
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
        return firstName+" "+lastName;
    }

    public void setName(String name)
    {
        this.firstName = name;
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

    public String[] toCSVLine(){

        String buyer = "";
        if(isBuyer!=null){
            buyer="x";
        }

        String seller = "";
        if(isSeller!=null){
            seller="x";
        }

        String[] line = {
            getFirstName(),
            getLastName(),
            getPhone(),
            getCellPhone(),
            getEmail(),
            getUserName(),
            getStreet(),
            getCity(),
            getState(),
            getZip(),
            getPriceLow(),
            getPriceHigh(),
            getBedrooms(),
            getDateCreated().toString(),
            getAdminId().toString(),
            getSiteId().toString(),
            getNotes(),
            buyer,
            seller
        };

        return line;
    }

    public static Lead generateLead(
        Integer adminId,
        Integer siteId,
        String prefix,
        Boolean valid
    ){

        String email = prefix+"@test.con";
        if(valid){
            email=prefix+"@test.com";
        }

        Lead lead = new Lead();
        lead.setFirstName(prefix+"_first_name");
        lead.setLastName(prefix+"_last_name");
        lead.setPhone("(123) 456-7891");
        lead.setCellPhone("(789) 654-1211");
        lead.setEmail(email);
        lead.setUserName(prefix+"_user_name");
        lead.setStreet("1st street");
        lead.setCity("San Diego");
        lead.setCity("CA");
        lead.setZip("430000");
        lead.setPriceLow("1000");
        lead.setPriceHigh("10000");
        lead.setBedrooms("2");
        lead.setDateCreated(new Date());
        lead.setAdminId(adminId);
        lead.setSiteId(siteId);

        return lead;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getCellPhone()
    {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone)
    {
        this.cellPhone = cellPhone;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getPriceLow()
    {
        return priceLow;
    }

    public void setPriceLow(String priceLow)
    {
        this.priceLow = priceLow;
    }

    public String getPriceHigh()
    {
        return priceHigh;
    }

    public void setPriceHigh(String priceHigh)
    {
        this.priceHigh = priceHigh;
    }

    public String getBedrooms()
    {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms)
    {
        this.bedrooms = bedrooms;
    }

    public Integer getAdminId()
    {
        return adminId;
    }

    public void setAdminId(Integer adminId)
    {
        this.adminId = adminId;
    }

    public Integer getSiteId()
    {
        return siteId;
    }

    public void setSiteId(Integer siteId)
    {
        this.siteId = siteId;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public Boolean getBuyer()
    {
        return isBuyer;
    }

    public void setBuyer(Boolean buyer)
    {
        isBuyer = buyer;
    }

    public Boolean getSeller()
    {
        return isSeller;
    }

    public void setSeller(Boolean seller)
    {
        isSeller = seller;
    }
}
