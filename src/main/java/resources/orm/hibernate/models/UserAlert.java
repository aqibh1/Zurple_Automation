package resources.orm.hibernate.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import resources.orm.hibernate.HibernateUtil;

@Entity
@Table(name = "user_alert",uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_alert_id")})
public class UserAlert extends Abstract
        implements java.io.Serializable {

    private Integer user_alert_id;
    private UserActivity user_activity;
    private AlertRule alert_rule;
    private User user;
    private Email email;
    private Integer user_alert_target;
    private Integer user_alert_active;
    private Date user_alert_triggered;
    private Date user_alert_dispatched;
    private Date user_alert_stopped;
    private String user_alert_data;
       
    
    public UserAlert() {
    }

    @Id
    @Column(name = "user_alert_id", unique = true, nullable = false)
    public Integer getId()
    {
        return user_alert_id;
    }

    public void setId(Integer user_alert_id)
    {
        this.user_alert_id = user_alert_id;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_activity_id")
    public UserActivity getUserActivity()
    {
        return user_activity;
    }

    public void setUserActivity(UserActivity user_activity_id)
    {
        this.user_activity = user_activity_id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser()
    {
        return user;
    }

    public void setUser(User user_id)
    {
        this.user = user_id;
    }
    
    
    
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "email_id")
    public Email getEmailId() {
        return this.email;
    }

    public void setEmailId(Email email_id) {
        this.email = email_id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "alert_rule_id")
    public AlertRule getAlertRule()
    {
        return alert_rule;
    }

    public void setAlertRule(AlertRule alert_rule)
    {
        this.alert_rule = alert_rule;
    }

    @Column(name = "user_alert_target", unique = false, nullable = false)
    public Integer getUserAlertTarget()
    {
        return user_alert_target;
    }

    public void setUserAlertTarget(Integer user_alert_target)
    {
        this.user_alert_target = user_alert_target;
    }
    
    @Column(name = "user_alert_active", unique = false, nullable = false)
    public Integer getUserAlertActive()
    {
        return user_alert_active;
    }

    public void setUserAlertActive(Integer user_alert_active)
    {
        this.user_alert_active = user_alert_active;
    }

    @Column(name = "user_alert_triggered", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUserAlertTriggered()
    {
        return user_alert_triggered;
    }

    public void setUserAlertTriggered(Date user_alert_triggered)
    {
        this.user_alert_triggered = user_alert_triggered;
    }

    @Column(name = "user_alert_dispatched", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUserAlertDispatched()
    {
        return user_alert_dispatched;
    }

    public void setUserAlertDispatched(Date user_alert_dispatched)
    {
        this.user_alert_dispatched = user_alert_dispatched;
    }

    @Column(name = "user_alert_stopped", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUserAlertStopped()
    {
        return user_alert_stopped;
    }

    public void setUserAlertStopped(Date user_alert_stopped)
    {
        this.user_alert_stopped = user_alert_stopped;
    }

    @Column(name = "user_alert_data", unique = false, nullable = false)
    public String getUserAlertData()
    {
        return user_alert_data;
    }
    
    public void setUserAlertData(String user_alert_data)
    {
        this.user_alert_data = user_alert_data;
    }
}
