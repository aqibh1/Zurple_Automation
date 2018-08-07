package resources.orm.hibernate.models;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "imports", uniqueConstraints = {})
public class Import
        implements java.io.Serializable {

    @Id
    @Column(name="import_id")
    private Integer import_id;

    @ManyToOne(fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne(fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    @JoinColumn(name = "site_id")
    private Site site;

    @ManyToOne(fetch=FetchType.LAZY,cascade= CascadeType.ALL)
    @JoinColumn(name = "importer_admin_id")
    private Admin importer_admin;

    @Column(name = "row_count")
    private Integer row_count;

    @Column(name = "added_leads")
    private Integer added_leads;

    @Column(name = "file_name")
    private String file_name;

    @Column(name = "create_datetime", unique = false, nullable = true)
    private Date create_datetime;

    @Column(name = "update_datetime", unique = false, nullable = true)
    private Date update_datetime;

    public Integer getImportId()
    {
        return import_id;
    }

    public void setImportId(Integer import_id)
    {
        this.import_id = import_id;
    }

    public Admin getAdmin()
    {
        return admin;
    }

    public void setAdmin(Admin admin)
    {
        this.admin = admin;
    }

    public Site getSite()
    {
        return site;
    }

    public void setSite(Site site)
    {
        this.site = site;
    }

    public Admin getImporterAdmin()
    {
        return importer_admin;
    }

    public void setImporterAdmin(Admin importer_admin)
    {
        this.importer_admin = importer_admin;
    }

    public Integer getRowCount()
    {
        if(row_count==null){
            return 0;
        }
        return row_count;
    }

    public void setRowCount(Integer row_count)
    {
        this.row_count = row_count;
    }

    public Integer getAddedLeads()
    {
        if(added_leads==null){
            return 0;
        }
        return added_leads;
    }

    public void setAddedLeads(Integer added_leads)
    {
        this.added_leads = added_leads;
    }

    public String getFileName()
    {
        return file_name;
    }

    public void setFileName(String file_name)
    {
        this.file_name = file_name;
    }

    public Date getCreateDatetime()
    {
        return create_datetime;
    }

    public void setCreateDatetime(Date create_datetime)
    {
        this.create_datetime = create_datetime;
    }

    public Date getUpdateDatetime()
    {
        return update_datetime;
    }

    public void setUpdateDatetime(Date update_datetime)
    {
        this.update_datetime = update_datetime;
    }
}
