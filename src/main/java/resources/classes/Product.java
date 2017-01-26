package resources.classes;

public class Product
{

    private Integer product_id;
    private String recordtype;
    private String displayname;
    private String code_name;

    public Integer getId()
    {
        return product_id;
    }

    public void setId(Integer product_id)
    {
        this.product_id = product_id;
    }

    public String getRecordType()
    {
        return recordtype;
    }

    public void setRecordType(String recordtype)
    {
        this.recordtype = recordtype;
    }

    public String getDisplayName()
    {
        return displayname;
    }

    public void setDisplayName(String displayname)
    {
        this.displayname = displayname;
    }

    public String getCodeName()
    {
        return code_name;
    }

    public void setCodeName(String code_name)
    {
        this.code_name = code_name;
    }
}
