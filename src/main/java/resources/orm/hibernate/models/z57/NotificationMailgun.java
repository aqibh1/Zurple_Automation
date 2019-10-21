package resources.orm.hibernate.models.z57;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import resources.orm.hibernate.models.Abstract;

@Entity
@Table(name = "notification_mailgun", uniqueConstraints = {
@UniqueConstraint(columnNames = "notitication_mailgun_id")})
public class NotificationMailgun extends Abstract{
	
	private Integer accountId;
	private Integer status;
	private Integer notficationEmailId;
	private Integer notficationId;
	private Integer notificationMailgunId;
	private String externalMailgunId;
	private Timestamp dateAdded;
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name = "notitication_mailgun_id", unique = true, nullable = false, length = 255)
	public Integer getNotificationMailgunId() {
		return notificationMailgunId;
	}
	public void setNotificationMailgunId(Integer notificationMailgunId) {
		this.notificationMailgunId = notificationMailgunId;
	}
	
	@Column(name = "notification_id", unique = true, nullable = false, length = 255)
	public Integer getNotficationId() {
		return notficationId;
	}
	public void setNotficationId(Integer notficationId) {
		this.notficationId = notficationId;
	}
	
	@Column(name = "notification_email_id", unique = true, nullable = false, length = 255)
	public Integer getNotficationEmailId() {
		return notficationEmailId;
	}
	
	@Column(name = "external_mailgun_id", unique = false, nullable = false, length = 200)
	public String getExternalMailgunId() {
		return externalMailgunId;
	}
	
	@Column(name = "date_added", unique = true, nullable = true, length = 11)
	public Timestamp getDateAdded() {
		return dateAdded;
	}
	
	public void setNotficationEmailId(Integer notficationEmailId) {
		this.notficationEmailId = notficationEmailId;
	}
	
	@Column(name = "account_id", unique = true, nullable = false, length = 255)
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	@Column(name = "status", unique = true, nullable = false, length = 255)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public void setDateAdded(Timestamp dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public void setExternalMailgunId(String externalMailgunId) {
		this.externalMailgunId = externalMailgunId;
	}
}
