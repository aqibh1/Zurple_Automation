package resources.data.z57;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author adar
 * This is data factory mapped to JSON file in resources/data/ folder
 * This class will handle all the data related to search form
 *
 */
public class SearchFormData extends JSONData<SearchFormData>{
	
	SearchFormData searchFormData;
	
	@JsonProperty("input_search")
	String InputSearch;
	
	@JsonProperty("search_by")
	String SearchBy;
	
	@JsonProperty("minimum_price")
	String MinimumValue;
	
	@JsonProperty("maximum_value")
	String MaximumValue;
	
	@JsonProperty("number_of_beds")
	String NumberOfBeds;
	
	@JsonProperty("number_of_baths")
	String NumberOfBaths;
	
	@JsonProperty("property_type")
	String PropertyType;
	
	@JsonProperty("feature_any_all")
	String FeatureAnyAll;
	
	@JsonProperty("features")
	String Features;
	
	@JsonProperty("square_footage")
	String SquareFotage;
	
	@JsonProperty("view")
	String View;
	
	@JsonProperty("lot_size")
	String LotSize;
	
	@JsonProperty("style")
	String Style;
	
	@JsonProperty("status")
	String Status;

	@JsonProperty("year_built")
	String YearBuilt;
	
	
	public SearchFormData getSearchFormData() {
		return searchFormData;
	}
	public void setSearchFormData(SearchFormData searchFormData) {
		this.searchFormData = searchFormData;
	}
	public String getInputSearch() {
		return InputSearch;
	}
	public void setInputSearch(String inputSearch) {
		InputSearch = inputSearch;
	}
	public String getSearchBy() {
		return SearchBy;
	}
	public void setSearchBy(String searchBy) {
		SearchBy = searchBy;
	}
	public String getMinimumValue() {
		return MinimumValue;
	}
	public void setMinimumValue(String minimumValue) {
		MinimumValue = minimumValue;
	}
	public String getMaximumValue() {
		return MaximumValue;
	}
	public void setMaximumValue(String maximumValue) {
		MaximumValue = maximumValue;
	}
	public String getNumberOfBeds() {
		return NumberOfBeds;
	}
	public void setNumberOfBeds(String numberOfBeds) {
		NumberOfBeds = numberOfBeds;
	}
	public String getNumberOfBaths() {
		return NumberOfBaths;
	}
	public void setNumberOfBaths(String numberOfBaths) {
		NumberOfBaths = numberOfBaths;
	}
	public String getPropertyType() {
		return PropertyType;
	}
	public void setPropertyType(String propertyType) {
		PropertyType = propertyType;
	}
	public String getFeatureAnyAll() {
		return FeatureAnyAll;
	}
	public void setFeatureAnyAll(String featureAnyAll) {
		FeatureAnyAll = featureAnyAll;
	}
	public String getFeatures() {
		return Features;
	}
	public void setFeatures(String features) {
		Features = features;
	}
	public String getSquareFotage() {
		return SquareFotage;
	}
	public void setSquareFotage(String squareFotage) {
		SquareFotage = squareFotage;
	}
	public String getView() {
		return View;
	}
	public void setView(String view) {
		View = view;
	}
	public String getLotSize() {
		return LotSize;
	}
	public void setLotSize(String lotSize) {
		LotSize = lotSize;
	}
	public String getStyle() {
		return Style;
	}
	public void setStyle(String style) {
		Style = style;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getYearBuilt() {
		return YearBuilt;
	}
	public void setYearBuilt(String yearBuilt) {
		YearBuilt = yearBuilt;
	}
	
//	public SearchFormData setSearchFormData(String filename) throws JsonParseException, JsonMappingException, IOException{
//        ObjectMapper mapper = new ObjectMapper();
//		searchFormData = mapper.readValue(new File(/* System.getProperty("user.dir")+ */filename), SearchFormData.class);
//        return searchFormData;
//    }
	public SearchFormData() {
		
	}
	public SearchFormData(String pFileName) {
		searchFormData = (SearchFormData)setDataMapper(pFileName, SearchFormData.class);
	}
	
//	public SearchFormData setSearchFormData(String filename) throws JsonParseException, JsonMappingException, IOException{
//        ObjectMapper mapper = new ObjectMapper();
//		searchFormData = mapper.readValue(new File(System.getProperty("user.dir")+ filename), SearchFormData.class);
//        return searchFormData;
//    }
	public SearchFormData getSearchFormDataObject() {
		return searchFormData;
	}
}
