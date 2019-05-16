package resources.orm.hibernate.models.z57;

import resources.orm.hibernate.models.Abstract;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sites", uniqueConstraints = {
        @UniqueConstraint(columnNames = "site_id")})
public class Sites extends Abstract{
	
	private Integer siteID;
	private Integer httpsEnabled;
	private String webSiteURL;
	
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name = "site_id", unique = true, nullable = false, length = 255)
	public Integer getsiteID() {
		return siteID;
	}
	public void setsiteID(Integer siteID) {
		this.siteID = siteID;
	}
	
	@Column(name = "https_enabled", unique = false, nullable = false, length = 255)
	public Integer getHttpsEnabled() {
		return httpsEnabled;
	}
	public void setHttpsEnabled(Integer httpsEnabled) {
		this.httpsEnabled = httpsEnabled;
	}
	@Column(name = "wp_site_url", unique = false, nullable = false, length = 500)
	public String getWebSiteURL() {
		return webSiteURL;
	}
	public void setWebSiteURL(String webSiteURL) {
		this.webSiteURL = webSiteURL;
	}
	
	


}
