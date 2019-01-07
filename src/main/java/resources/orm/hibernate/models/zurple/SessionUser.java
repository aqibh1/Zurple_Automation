package resources.orm.hibernate.models.zurple;

import resources.orm.hibernate.models.Abstract;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "session_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "session_user_id")})
public class SessionUser extends Abstract
        implements java.io.Serializable {

    private Integer session_user_id;
    private User user_id;
    private Site site_id;
    private Date session_begin;
    private Date session_end;
    
    private String session_user_agent;
    private Integer session_ip;
    private Float session_latitude;
    private Float session_longitude;
    private String session_referrer;
    private String session_entry;
    
    private Integer session_page_view;

    public SessionUser() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "session_user_id", unique = true, nullable = false)
    public Integer getId() {
        return this.session_user_id;
    }

    public void setId(Integer session_user_id) {
        this.session_user_id = session_user_id;
    }
        
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUserId() {
        return this.user_id;
    }

    public void setUserId(User user_id) {
        this.user_id = user_id;
    }

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "site_id")
    public Site getSiteId() {
        return this.site_id;
    }

    public void setSiteId(Site site_id) {
        this.site_id = site_id;
    }

    @Column(name = "session_user_agent", unique = false, nullable = false, length = 255)
    public String getSessionUserAgent() {
        return this.session_user_agent;
    }

    public void setSessionUserAgent(String session_user_agent) {
        this.session_user_agent = session_user_agent;
    }

    @Column(name = "session_referrer", unique = false, nullable = false, length = 255)
    public String getSessionReferrer() {
        return this.session_user_agent;
    }

    public void setSessionReferrer(String session_referrer) {
        this.session_referrer = session_referrer;
    }

    @Column(name = "session_entry", unique = false, nullable = false, length = 255)
    public String getSessionEntry() {
        return this.session_entry;
    }

    public void setSessionEntry(String session_entry) {
        this.session_entry = session_entry;
    }

    @Column(name = "session_begin", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getSessionBegin() {
        return this.session_begin;
    }

    public void setSessionBegin(Date session_begin) {
        this.session_begin = session_begin;
    }

    @Column(name = "session_end", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getSessionEnd() {
        return this.session_end;
    }

    public void setSessionEnd(Date session_end) {
        this.session_end = session_end;
    }

    @Column(name = "session_page_view")
    public Integer getSessionPageView() {
        return this.session_page_view;
    }

    public void setSessionPageView(Integer session_page_view) {
        this.session_page_view = session_page_view;
    }

    @Column(name = "session_ip")
    public Integer getSessionIp() {
        return this.session_ip;
    }

    public void setSessionIp(Integer session_ip) {
        this.session_ip = session_ip;
    }

    @Column(name = "session_latitude")
    public Float getSessionLatitude() {
        return this.session_latitude;
    }

    public void setSessionLatitude(Float session_latitude) {
        this.session_latitude = session_latitude;
    }

    @Column(name = "session_longitude")
    public Float getSessionLongitude() {
        return this.session_longitude;
    }

    public void setSessionLongitude(Float session_longitude) {
        this.session_longitude = session_longitude;
    }

}
