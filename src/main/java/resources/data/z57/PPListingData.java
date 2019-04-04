/**
 * 
 */
package resources.data.z57;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author adar
 *
 */
public class PPListingData extends JSONData<PPListingData>{
	
	@JsonProperty("address")
	String address;
	
	@JsonProperty("status")
	String status;
	
	@JsonProperty("city")
	String city;
	
	@JsonProperty("state")
	String state;
	
	@JsonProperty("lead_address")
	String lead_address;
	
	@JsonProperty("zip")
	String zip;
	
	@JsonProperty("county")
	String county;
	
	@JsonProperty("price")
	String price;
	
	@JsonProperty("mls")
	String mls;
	
	@JsonProperty("property_type")
	String propertyType;
	
	@JsonProperty("sale_type")
	String saleType;
	
	@JsonProperty("agent")
	String agent;
	
	@JsonProperty("brokerage")
	String brokerage;
	
	@JsonProperty("title")
	String title;
	
	@JsonProperty("description")
	String description;
	
	@JsonProperty("accelerator_caption")
	String acceleratorCaption;
	
	@JsonProperty("embeded")
	String embeded;
	
	@JsonProperty("virtual_tour_link")
	String virtualTourLink;
	
	@JsonProperty("bed")
	String bed;
	
	@JsonProperty("bath")
	String bath;
	
	@JsonProperty("interior")
	String interior;
	
	@JsonProperty("exterior")
	String exterior;
	
	@JsonProperty("square_footage")
	String sqFootage;
	
	@JsonProperty("lot_size")
	String lotSize;
	
	@JsonProperty("lot_details")
	String lotDetails;
	
	@JsonProperty("sale_info")
	String saleInfo;

	@JsonProperty("year_built")
	String yearBuilt;
	
	@JsonProperty("image_path")
	String imagePath;

	public String getAddress() {
		return address;
	}

	public String getStatus() {
		return status;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getLead_address() {
		return lead_address;
	}

	public String getZip() {
		return zip;
	}

	public String getCounty() {
		return county;
	}

	public String getPrice() {
		return price;
	}

	public String getMls() {
		return mls;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public String getSaleType() {
		return saleType;
	}

	public String getAgent() {
		return agent;
	}

	public String getBrokerage() {
		return brokerage;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getAcceleratorCaption() {
		return acceleratorCaption;
	}

	public String getEmbeded() {
		return embeded;
	}

	public String getVirtualTourLink() {
		return virtualTourLink;
	}

	public String getBed() {
		return bed;
	}

	public String getBath() {
		return bath;
	}

	public String getInterior() {
		return interior;
	}

	public String getExterior() {
		return exterior;
	}

	public String getSqFootage() {
		return sqFootage;
	}

	public String getLotSize() {
		return lotSize;
	}

	public String getLotDetails() {
		return lotDetails;
	}

	public String getSaleInfo() {
		return saleInfo;
	}

	public String getYearBuilt() {
		return yearBuilt;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setLead_address(String lead_address) {
		this.lead_address = lead_address;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setMls(String mls) {
		this.mls = mls;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public void setBrokerage(String brokerage) {
		this.brokerage = brokerage;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAcceleratorCaption(String acceleratorCaption) {
		this.acceleratorCaption = acceleratorCaption;
	}

	public void setEmbeded(String embeded) {
		this.embeded = embeded;
	}

	public void setVirtualTourLink(String virtualTourLink) {
		this.virtualTourLink = virtualTourLink;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	public void setBath(String bath) {
		this.bath = bath;
	}

	public void setInterior(String interior) {
		this.interior = interior;
	}

	public void setExterior(String exterior) {
		this.exterior = exterior;
	}

	public void setSqFootage(String sqFootage) {
		this.sqFootage = sqFootage;
	}

	public void setLotSize(String lotSize) {
		this.lotSize = lotSize;
	}

	public void setLotDetails(String lotDetails) {
		this.lotDetails = lotDetails;
	}

	public void setSaleInfo(String saleInfo) {
		this.saleInfo = saleInfo;
	}

	public void setYearBuilt(String yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	PPListingData listingData;
	public PPListingData() {
		// TODO Auto-generated constructor stub
	}
	public PPListingData(String pFileName){
		listingData=(PPListingData) setDataMapper(pFileName, PPListingData.class);
	}
	public PPListingData getListingData() {
		return listingData;
	}

}
