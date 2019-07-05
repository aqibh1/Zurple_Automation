/**
 * 
 */
package resources.orm.hibernate.models.pp;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import resources.orm.hibernate.models.Abstract;

/**
 * @author adar
 *
 */
@Entity
@Table(name = "posts", uniqueConstraints = {
@UniqueConstraint(columnNames = "post_id")})
public class Posts extends Abstract{
	
	private Integer postID;
	private Integer parentPostID;
	private Integer scheduleID;
	private Integer accountID;
	private String postFacebook;
	private String postTwitter;
	private String postLinkedIn;
	private String postYoutube;
	private Integer status;
	private Integer postAttempts;
	private Integer postScheduled;
	private String errors;
	private Timestamp dateAdded;
	private String facebookPageName;
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name = "post_id", unique = true, nullable = false, length = 11)
	public Integer getPostID() {
		return postID;
	}
	
	@Column(name = "parent_post_id", unique = true, nullable = true, length = 10)
	public Integer getParentPostID() {
		return parentPostID;
	}
	
	@Column(name = "schedule_id", unique = true, nullable = true, length = 11)
	public Integer getScheduleID() {
		return scheduleID;
	}
	
	@Column(name = "account_id", unique = true, nullable = false, length = 11)
	public Integer getAccountID() {
		return accountID;
	}
	
	@Column(name = "post_facebook", unique = false, nullable = true, length = 1000)
	public String getPostFacebook() {
		return postFacebook;
	}
	
	@Column(name = "post_twitter", unique = false, nullable = true, length = 500)
	public String getPostTwitter() {
		return postTwitter;
	}
	
	@Column(name = "post_linkedin", unique = false, nullable = true, length = 700)
	public String getPostLinkedIn() {
		return postLinkedIn;
	}
	
	@Column(name = "post_youtube", unique = false, nullable = true, length = 5000)
	public String getPostYoutube() {
		return postYoutube;
	}
	
	@Column(name = "status", unique = false, nullable = true, length = 1)
	public Integer getStatus() {
		return status;
	}
	
	@Column(name = "post_attempts", unique = false, nullable = false, length = 5)
	public Integer getPostAttempts() {
		return postAttempts;
	}
	
	@Column(name = "scheduled", unique = false, nullable = true, length = 1)
	public Integer getPostScheduled() {
		return postScheduled;
	}
	
	@Column(name = "errors", unique = false, nullable = true, length = 200)
	public String getErrors() {
		return errors;
	}
	
	@Column(name = "date_added", unique = false, nullable = true, length = 200)
	public Timestamp getDateAdded() {
		return dateAdded;
	}
	
	@Column(name = "fb_page_name", unique = false, nullable = true, length = 200)
	public String getFacebookPageName() {
		return facebookPageName;
	}
	
	public void setPostID(Integer postID) {
		this.postID = postID;
	}
	public void setParentPostID(Integer parentPostID) {
		this.parentPostID = parentPostID;
	}
	public void setScheduleID(Integer scheduleID) {
		this.scheduleID = scheduleID;
	}
	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}
	public void setPostFacebook(String postFacebook) {
		this.postFacebook = postFacebook;
	}
	public void setPostTwitter(String postTwitter) {
		this.postTwitter = postTwitter;
	}
	public void setPostLinkedIn(String postLinkedIn) {
		this.postLinkedIn = postLinkedIn;
	}
	public void setPostYoutube(String postYoutube) {
		this.postYoutube = postYoutube;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setPostAttempts(Integer postAttempts) {
		this.postAttempts = postAttempts;
	}
	public void setPostScheduled(Integer postScheduled) {
		this.postScheduled = postScheduled;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public void setDateAdded(Timestamp dateAdded) {
		this.dateAdded = dateAdded;
	}
	public void setFacebookPageName(String facebookPageName) {
		this.facebookPageName = facebookPageName;
	}
	
	

}
