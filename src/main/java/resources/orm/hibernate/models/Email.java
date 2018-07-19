package resources.orm.hibernate.models;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "emails", catalog = "zurple_platform", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email_id")})
public class Email
        implements java.io.Serializable {

    private Integer email_id;
    private String email_type;
    private String subject;
    private String title;
    private String body;
    private User user_id;
    private Integer order_number;
    private String dlnk;
    
    private Date send_datetime;
    private Date open_datetime;

    public Email() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "email_id", unique = true, nullable = false)
    public Integer getId() {
        return this.email_id;
    }

    public void setId(Integer email_id) {
        this.email_id = email_id;
    }

    @Column(name = "email_type", unique = false, nullable = false, length = 255)
    public String getEmailType() {
        return this.email_type;
    }

    public void setEmailType(String email_type) {
        this.email_type = email_type;
    }
    
    @Column(name = "subject", unique = false, nullable = false, length = 255)
    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    @Column(name = "title", unique = false, nullable = false, length = 255)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name = "body", unique = false, nullable = false, length = 255)
    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    @Column(name = "dlnk", unique = false, nullable = false, length = 255)
    public String getDlnk() {
        return this.dlnk;
    }

    public void setDlnk(String dlnk) {
        this.dlnk = dlnk;
    }
    
    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUserId() {
        return this.user_id;
    }

    public void setUserId(User user_id) {
        this.user_id = user_id;
    }


    @Column(name = "send_datetime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getSendDatetime() {
        return this.send_datetime;
    }

    public void setSendDatetime(Date send_datetime) {
        this.send_datetime = send_datetime;
    }

    @Column(name = "open_datetime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getOpenDatetime() {
        return this.open_datetime;
    }

    public void setOpenDatetime(Date open_datetime) {
        this.open_datetime = open_datetime;
    }

    @Column(name = "order_number")
    public Integer getOrderNumber() {
        return this.order_number;
    }

    public void setOrderNumber(Integer order_number) {
        this.order_number = order_number;
    }

}
