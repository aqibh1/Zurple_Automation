package resources.classes;

import java.util.Date;

public class Template
{

    private Integer id;
    private String description;
    private String variationOf;
    private Date dateCreated;
    private String forPackages;
    private Integer sequence;
    private Boolean override;
    private String active;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getVariationOf()
    {
        return variationOf;
    }

    public void setVariationOf(String variationOf)
    {
        this.variationOf = variationOf;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public String getForPackages()
    {
        return forPackages;
    }

    public void setForPackages(String forPackages)
    {
        this.forPackages = forPackages;
    }

    public Integer getSequence()
    {
        return sequence;
    }

    public void setSequence(Integer sequence)
    {
        this.sequence = sequence;
    }

    public Boolean getOverride()
    {
        return override;
    }

    public void setOverride(Boolean override)
    {
        this.override = override;
    }

    public String getActive()
    {
        return active;
    }

    public void setActive(String active)
    {
        this.active = active;
    }
}
