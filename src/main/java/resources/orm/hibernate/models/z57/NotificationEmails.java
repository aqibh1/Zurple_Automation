package resources.orm.hibernate.models.z57;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import resources.orm.hibernate.models.Abstract;

@Entity
@Table(name = "notification_emails", uniqueConstraints = {
        @UniqueConstraint(columnNames = "notification_email_id")})
public class NotificationEmails extends Abstract{
	
	private Integer notificationId;
	private String email;
	private String name;
	private Integer notificationEmailId;
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name = "notification_email_id", unique = true, nullable = false, length = 255)
	public Integer getNotificationEmailId() {
		return notificationEmailId;
	}
	public void setNotificationEmailId(Integer notificationEmailId) {
		this.notificationEmailId = notificationEmailId;
	}
	
	@Column(name = "notification_id", unique = true, nullable = false, length = 255)
	public Integer getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	
	@Column(name = "email", unique = true, nullable = false, length = 255)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "name", unique = false, nullable = false, length = 255)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
