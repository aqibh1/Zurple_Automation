package resources.orm.hibernate.models.zurple;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "alert_rules", uniqueConstraints = {
        @UniqueConstraint(columnNames = "arule_id")})
public class AlertRule
        implements java.io.Serializable {

    private Integer alert_rule_id;
    private String alert_name;
    private Site site;
    private String alert_type;
    private Integer int_value;
    private Integer int_value2;
    private String str_value;
    private Date create_datetime;
    private Date update_datetime;
    private Integer alert_rule_priority;
    private Integer immediate_dispatch;
    private Integer nextday_dispatch;

    private Set<UserAlert> user_alerts;

    @Id
    @Column(name = "arule_id", unique = true, nullable = false)
    public Integer getId() {
        return this.alert_rule_id;
    }

    public void setId(Integer alert_rule_id) {
        this.alert_rule_id = alert_rule_id;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "site_id")
    public Site getSite() {
        return this.site;
    }

    public void setSite(Site site_id) {
        this.site = site_id;
    }
    
    @Column(name = "alert_name", unique = false, nullable = false)
    public String getAlertName()
    {
        return alert_name;
    }

    public void setAlertName(String alert_name)
    {
        this.alert_name = alert_name;
    }

    @Column(name = "alert_type", unique = false, nullable = false)
    public String getAlertType()
    {
        return alert_type;
    }

    public void setAlertType(String alert_type)
    {
        this.alert_type = alert_type;
    }

    @Column(name = "int_value", unique = false, nullable = false)
    public Integer getIntValue()
    {
        return int_value;
    }

    public void setIntValue(Integer int_value)
    {
        this.int_value = int_value;
    }

    @Column(name = "int_value2", unique = false, nullable = false)
    public Integer getIntValue2()
    {
        return int_value2;
    }

    public void setIntValue2(Integer int_value2)
    {
        this.int_value2 = int_value2;
    }

    @Column(name = "str_value", unique = false, nullable = false)
    public String getStrValue()
    {
        return str_value;
    }

    public void setStrValue(String str_value)
    {
        this.str_value = str_value;
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

    @Column(name = "alert_rule_priority", unique = false, nullable = false)
    public Integer getAlertRulePriority()
    {
        return alert_rule_priority;
    }

    public void setAlertRulePriority(Integer alert_rule_priority)
    {
        this.alert_rule_priority = alert_rule_priority;
    }

    @Column(name = "immediate_dispatch", unique = false, nullable = false)
    public Integer getImmediateDispatch()
    {
        return immediate_dispatch;
    }

    public void setImmediateDispatch(Integer immediate_dispatch)
    {
        this.immediate_dispatch = immediate_dispatch;
    }

    @Column(name = "nextday_dispatch", unique = false, nullable = false)
    public Integer getNextdayDispatch()
    {
        return nextday_dispatch;
    }

    public void setNextdayDispatch(Integer nextday_dispatch)
    {
        this.nextday_dispatch = nextday_dispatch;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="alertRule")
    public Set<UserAlert> getUserAlerts() {
        return this.user_alerts;
    }

    public void setUserAlerts(Set<UserAlert> user_alerts)
    {
        this.user_alerts = user_alerts;
    }

}
