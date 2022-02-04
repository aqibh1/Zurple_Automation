/**
 * 
 */
package resources.orm.hibernate.models.zurple;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import resources.orm.hibernate.models.Abstract;

/**
 * @author darrraqi
 *
 */
/**
 * @author darrraqi
 *
 */
@Entity
@Table(name = "admin_dashboard_stats", uniqueConstraints = {
        @UniqueConstraint(columnNames = "admin_id")})
public class AdminDashboardStats extends Abstract implements java.io.Serializable{

	private Integer admin_id;
	private Integer new_leads;
	private Integer leads_managed;
	private Integer messages_sent;
	private Integer open_rate;
	private Date create_datetime;
	private Integer lead_replies;
	private Integer alert_triggered;
	private Integer website_visits;
	private Integer auto_leads;

	@Id
	@Column(name = "admin_id", unique = true, nullable = false)
	public Integer getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}

	@Column(name = "new_leads", unique = false, nullable = false)
	public Integer getNew_leads() {
		return new_leads;
	}
	public void setNew_leads(Integer new_leads) {
		this.new_leads = new_leads;
	}

	@Column(name = "leads_managed", unique = false, nullable = false)
	public Integer getLeads_managed() {
		return leads_managed;
	}
	public void setLeads_managed(Integer leads_managed) {
		this.leads_managed = leads_managed;
	}

	@Column(name = "messages_sent", unique = false, nullable = false)
	public Integer getMessages_sent() {
		return messages_sent;
	}
	public void setMessages_sent(Integer messages_sent) {
		this.messages_sent = messages_sent;
	}
	@Column(name = "open_rate", unique = false, nullable = false)
	public Integer getOpen_rate() {
		return open_rate;
	}
	public void setOpen_rate(Integer open_rate) {
		this.open_rate = open_rate;
	}
	@Column(name = "create_datetime", unique = false, nullable = false)
	public Date getCreate_datetime() {
		return create_datetime;
	}
	public void setCreate_datetime(Date create_datetime) {
		this.create_datetime = create_datetime;
	}
	public Integer getLead_replies() {
		return lead_replies;
	}
	@Column(name = "lead_replies", unique = false, nullable = false)
	public void setLead_replies(Integer lead_replies) {
		this.lead_replies = lead_replies;
	}

	public Integer getAlert_triggered() {
		return alert_triggered;
	}
	@Column(name = "alert_triggered", unique = false, nullable = false)
	public void setAlert_triggered(Integer alert_triggered) {
		this.alert_triggered = alert_triggered;
	}

	public Integer getWebsite_visits() {
		return website_visits;
	}
	@Column(name = "website_stats", unique = false, nullable = false)
	public void setWebsite_visits(Integer website_visits) {
		this.website_visits = website_visits;
	}
	public Integer getAuto_leads() {
		return auto_leads;
	}
	@Column(name = "auto_leads", unique = false, nullable = false)
	public void setAuto_leads(Integer auto_leads) {
		this.auto_leads = auto_leads;
	}

}
