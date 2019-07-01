package resources.orm.hibernate.models.zurple;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cities", uniqueConstraints = {
        @UniqueConstraint(columnNames = "city_id")})
public class City
        implements java.io.Serializable {

    private Integer city_id;
    private String state_abr;
    private String city_name;
    private Integer delete_flag;
    private Date create_datetime;
    private Date update_datetime;
    private Float latitude;
    private Float longitude;
    
    @Id
    @Column(name = "city_id", unique = true, nullable = false)
    public Integer getId() {
        return this.city_id;
    }

    public void setId(Integer city_id) {
        this.city_id = city_id;
    }
    
    @Column(name = "state_abr", unique = false, nullable = false)
    public String getStateAbr() {
        return this.state_abr;
    }

    public void setStateAbr(String state_abr) {
        this.state_abr = state_abr;
    }
    
    @Column(name = "city_name", unique = false, nullable = false)
    public String getCityName()
    {
        return city_name;
    }

    public void setCityName(String city_name)
    {
        this.city_name = city_name;
    }
    
    @Column(name = "delete_flag", unique = false, nullable = false)
    public Integer getDeleteFlag()
    {
        return delete_flag;
    }

    public void setDeleteFlag(Integer delete_flag)
    {
        this.delete_flag = delete_flag;
    }
    
    @Column(name = "create_datetime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDatetime()
    {
        return create_datetime;
    }

    public void setCreateDatetime(Date create_datetime)
    {
        this.create_datetime = create_datetime;
    }
    
    @Column(name = "update_datetime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateDatetime()
    {
        return update_datetime;
    }

    public void setUpdateDatetime(Date update_datetime)
    {
        this.update_datetime = update_datetime;
    }
    
    @Column(name = "latitude", unique = false, nullable = false)
    public Float getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Float latitude)
    {
        this.latitude = latitude;
    }

    @Column(name = "longitude", unique = false, nullable = false)
    public Float getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Float longitude)
    {
        this.longitude = longitude;
    }
    
}
