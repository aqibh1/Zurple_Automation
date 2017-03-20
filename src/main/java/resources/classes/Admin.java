package resources.classes;

import java.math.BigDecimal;
import java.util.Date;

public class Admin
{

    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private Date created_date;
    private Boolean active;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return first_name;
    }

    public void setFirstName(String first_name)
    {
        this.first_name = first_name;
    }

    public String getLastName()
    {
        return last_name;
    }

    public void setLastName(String last_name)
    {
        this.last_name = last_name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Date getCreatedDate()
    {
        return created_date;
    }

    public void setCreatedDate(Date created_date)
    {
        this.created_date = created_date;
    }

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }
}
