package resources.orm.hibernate.models.z57;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import resources.orm.hibernate.models.Abstract;

@Entity
@Table(name = "listing_images", uniqueConstraints = {
@UniqueConstraint(columnNames = "listing_image_id")})
public class ListingImages extends Abstract{
	
	private Integer listingImageId;
	private Integer listingId;
	private String location;
	private String thumbnail;
	private String label;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "listing_image_id", unique = true, nullable = false, length = 255)
	public Integer getListingImageId() {
		return listingImageId;
	}
	public void setListingImageId(Integer listingImageId) {
		this.listingImageId = listingImageId;
	}
	
	@Column(name = "listing_id", unique = false, nullable = false, length = 255)
	public Integer getListingId() {
		return listingId;
	}
	public void setListingId(Integer listingId) {
		this.listingId = listingId;
	}
	
	@Column(name = "location", unique = false, nullable = true, length = 255)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name = "thumbnail", unique = false, nullable = true, length = 255)
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	@Column(name = "label", unique = false, nullable = true, length = 255)
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
	

	
	

}
