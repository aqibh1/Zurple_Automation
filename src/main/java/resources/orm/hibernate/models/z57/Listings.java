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
@Table(name = "listings", uniqueConstraints = {
@UniqueConstraint(columnNames = "listing_id")})

public class Listings extends Abstract{
	  
	    private Integer listingId;
	    private Integer accountId;
	    private Integer price;
	    private Integer saleType;
	    private String address;
	    private String city;
	    private String state;
	    private String zip;
	    private String county;
	    private Double bath;
	    private Double bed;
	    private String squareFootage;
	    private String lotSize;
	    private String yearBuilt;
	    private Integer propertyType;
	    private String title;
	    private String desciption;
	    private String accelatorCaption;
	    private String customLink;
	    private String embeded;
	    private String mls;
	    private Integer statusId;
	    
	    
		@Id
	    @GeneratedValue(strategy = IDENTITY)
	    @Column(name = "listing_id", unique = true, nullable = false)
	    public Integer getlistingId() {
	        return this.listingId;
	    }
	    public void setlistingId(Integer pListingId) {
	        this.listingId = pListingId;
	    }
	    @Column(name = "account_id", unique = true, nullable = false)
		public Integer getAccountId() {
			return accountId;
		}
		public void setAccountId(Integer accountId) {
			this.accountId = accountId;
		}
		@Column(name = "price", unique = false, nullable = true)
		public Integer getPrice() {
			return price;
		}
		public void setPrice(Integer price) {
			this.price = price;
		}
		@Column(name = "sale_type", unique = false, nullable = true)
		public Integer getSaleType() {
			return saleType;
		}
		public void setSaleType(Integer saleType) {
			this.saleType = saleType;
		}
		@Column(name = "address", unique = false, nullable = true)
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		@Column(name = "city", unique = false, nullable = true)
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		@Column(name = "state", unique = false, nullable = true)
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		@Column(name = "zip", unique = false, nullable = true)
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
		@Column(name = "county", unique = false, nullable = true)
		public String getCounty() {
			return county;
		}
		public void setCounty(String county) {
			this.county = county;
		}
		@Column(name = "bath", unique = false, nullable = true)
		public Double getBath() {
			return bath;
		}
		public void setBath(Double bath) {
			this.bath = bath;
		}
		@Column(name = "bed", unique = false, nullable = true)
		public Double getBed() {
			return bed;
		}
		public void setBed(Double bed) {
			this.bed = bed;
		}
		@Column(name = "sq_footage", unique = false, nullable = true)
		public String getSquareFootage() {
			return squareFootage;
		}
		public void setSquareFootage(String squareFootage) {
			this.squareFootage = squareFootage;
		}
		@Column(name = "lot_size", unique = false, nullable = true)
		public String getLotSize() {
			return lotSize;
		}
		public void setLotSize(String lotSize) {
			this.lotSize = lotSize;
		}
		@Column(name = "year_built", unique = false, nullable = true)
		public String getYearBuilt() {
			return yearBuilt;
		}
		public void setYearBuilt(String yearBuilt) {
			this.yearBuilt = yearBuilt;
		}
		@Column(name = "property_type", unique = false, nullable = true)
		public Integer getPropertyType() {
			return propertyType;
		}
		public void setPropertyType(Integer propertyType) {
			this.propertyType = propertyType;
		}
		@Column(name = "title", unique = false, nullable = true)
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		@Column(name = "description", unique = false, nullable = true)
		public String getDesciption() {
			return desciption;
		}
		public void setDesciption(String desciption) {
			this.desciption = desciption;
		}
		@Column(name = "accelerator_caption", unique = false, nullable = true)
		public String getAccelatorCaption() {
			return accelatorCaption;
		}
		public void setAccelatorCaption(String accelatorCaption) {
			this.accelatorCaption = accelatorCaption;
		}
		@Column(name = "custom_link", unique = false, nullable = true)
		public String getCustomLink() {
			return customLink;
		}
		public void setCustomLink(String customLink) {
			this.customLink = customLink;
		}
		@Column(name = "embeded", unique = false, nullable = true)
		public String getEmbeded() {
			return embeded;
		}
		public void setEmbeded(String embeded) {
			this.embeded = embeded;
		}
		@Column(name = "mls", unique = false, nullable = true)
		public String getMls() {
			return mls;
		}
		public void setMls(String mls) {
			this.mls = mls;
		}
		@Column(name = "status_id", unique = true, nullable = false)
		public Integer getStatusId() {
				return statusId;
		}
		public void setStatusId(Integer statusId) {
				this.statusId = statusId;
		}

}
