package resources.orm.hibernate.models.z57;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import resources.orm.hibernate.models.Abstract;

@Entity
@Table(name = "notifications", uniqueConstraints = {
        @UniqueConstraint(columnNames = "notification_id")})
public class Notifications extends Abstract{

	private Integer account_id;
	private String email_from;
	private String email_subject;
	private String email_body;
	private Integer notificationId;
	private Date sentDate;
	
	@Column(name = "date_sent", unique = false, nullable = true)
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	@Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name = "notification_id", unique = false, nullable = false, length = 255)
	public Integer getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	@Column(name = "account_id", unique = false, nullable = false, length = 255)
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	
	 @Column(name = "email_from", unique = false, nullable = false, length = 255)
	public String getEmail_from() {
		return email_from;
	}
	public void setEmail_from(String email_from) {
		this.email_from = email_from;
	}
	
	@Column(name = "email_subject", unique = false, nullable = false, length = 255)
	public String getEmail_subject() {
		return email_subject;
	}
	public void setEmail_subject(String email_subject) {
		this.email_subject = email_subject;
	}
	
	@Column(name = "email_body", unique = false, nullable = false)
	public String getEmail_body() {
		return email_body;
	}
	public void setEmail_body(String email_body) {
		this.email_body = email_body;
	}
	
}
