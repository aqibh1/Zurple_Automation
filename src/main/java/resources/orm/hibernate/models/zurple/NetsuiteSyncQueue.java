package resources.orm.hibernate.models.zurple;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="ns_sync_queue", uniqueConstraints = { @UniqueConstraint(columnNames = "job_id")})
public class NetsuiteSyncQueue implements java.io.Serializable {
	@Id
	@Column(name="job_id")
	private Integer job_id;
	
	@Column(name="action")
	private String action;
	
	@Column(name="payload")
	private String payload;
	
	@Column(name="error")
	private String error;
	
	@Column(name="retry_count")
	private String retry_count;
	
	@Column(name="success")
	private Date success;
	
	@Column(name="create_datetime")
	private Date create_datetime;
	
	@Column(name="update_datetime")
	private Integer update_datetime;
	
	@Column(name="processed")
	private Integer processed;
	
	@Column(name="netsuite_id")
	private Integer netsuite_id;
	
	public Integer getJob_id() {
		return job_id;
	}

	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getRetry_count() {
		return retry_count;
	}

	public void setRetry_count(String retry_count) {
		this.retry_count = retry_count;
	}

	public Date getSuccess() {
		return success;
	}

	public void setSuccess(Date success) {
		this.success = success;
	}

	public Date getCreate_datetime() {
		return create_datetime;
	}

	public void setCreate_datetime(Date create_datetime) {
		this.create_datetime = create_datetime;
	}

	public Integer getUpdate_datetime() {
		return update_datetime;
	}

	public void setUpdate_datetime(Integer update_datetime) {
		this.update_datetime = update_datetime;
	}

	public Integer getProcessed() {
		return processed;
	}

	public void setProcessed(Integer processed) {
		this.processed = processed;
	}

	public Integer getNetsuite_id() {
		return netsuite_id;
	}

	public void setNetsuite_id(Integer netsuite_id) {
		this.netsuite_id = netsuite_id;
	}

}
