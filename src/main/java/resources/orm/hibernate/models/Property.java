package resources.orm.hibernate.models;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "properties", uniqueConstraints = {
        @UniqueConstraint(columnNames = "prop_id")})
public class Property
        implements java.io.Serializable {

    private Integer prop_id;

    private String prop_type;
    private String state;
    private String country;
    private String city;
    private String address;
    private String street_name;
    private String street_number;
    private String community;
    private Integer zip_code;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer price;
    private String status;
    private Integer year_built;
    private String equipment;

    private Integer square_feet;
    private Integer lot_sqft;

    private Boolean air_conditioning_yn;
    private Boolean balcony_yn;
    private Boolean basement_yn;
    private Boolean elevator_yn;
    private Boolean forced_air_yn;
    private Boolean foreclosure_yn;
    private Boolean furnished_yn;
    private Boolean garage_yn;
    private Boolean garden_yn;
    private Boolean gated_community_yn;
    private Boolean golf_course_yn;
    private Boolean horse_property_yn;
    private Boolean multilevel_yn;
    private Boolean new_construction_yn;
    private Boolean pets_yn;
    private Boolean pool_yn;
    private Boolean reo_yn;
    private Boolean short_sale_yn;
    private Boolean storage_yn;
    private Boolean view_yn;

    public Property() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "prop_id", unique = true, nullable = false)
    public Integer getId() {
        return this.prop_id;
    }

    public void setId(Integer prop_id) {
        this.prop_id = prop_id;
    }

    @Column(name = "prop_type", unique = false, nullable = false)
    public String getPropType() {
        return this.prop_type;
    }

    public void setPropType(String prop_type) {
        this.prop_type = prop_type;
    }

    @Column(name = "state", unique = false, nullable = false)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "country", unique = false, nullable = false)
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "city", unique = false, nullable = false)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "bedrooms", unique = false, nullable = false)
    public Integer getBedrooms() {
        return this.bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    @Column(name = "address", unique = false, nullable = false)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "street_name", unique = false, nullable = false)
    public String getStreetName() {
        return this.street_name;
    }

    public void setStreetName(String street_name) {
        this.street_name = street_name;
    }

    @Column(name = "street_number", unique = false, nullable = false)
    public String getStreetNumber() {
        return this.street_number;
    }

    public void setStreetNumber(String street_number) {
        this.street_number = street_number;
    }

    @Column(name = "community", unique = false, nullable = false)
    public String getCommunity() {
        return this.community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    @Column(name = "zip_code", unique = false, nullable = false)
    public Integer getZipCode() {
        return this.zip_code;
    }

    public void setZipCode(Integer zip_code) {
        this.zip_code = zip_code;
    }

    @Column(name = "bathrooms", unique = false, nullable = false)
    public Integer getBathrooms() {
        return this.bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    @Column(name = "price", unique = false, nullable = false)
    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Column(name = "status", unique = false, nullable = false)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "year_built", unique = false, nullable = false)
    public Integer getYearBuilt() {
        return this.year_built;
    }

    public void setYearBuilt(Integer year_built) {
        this.year_built = year_built;
    }

    @Column(name = "equipment", unique = false, nullable = false)
    public String getEquipment() {
        return this.equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    @Column(name = "square_feet", unique = false, nullable = false)
    public Integer getSquareFeet() {
        return this.square_feet;
    }

    public void setSquareFeet(Integer square_feet) {
        this.square_feet = square_feet;
    }

    @Column(name = "lot_sqft", unique = false, nullable = false)
    public Integer getLotSqft() {
        return this.lot_sqft;
    }

    public void setLotSqft(Integer lot_size) {
        this.lot_sqft = lot_size;
    }

    @Column(name = "air_conditioning_yn", unique = false, nullable = false)
    public Boolean getAirConditioning() {
        return this.air_conditioning_yn;
    }

    public void setAirConditioning(Boolean air_conditioning_yn) {
        this.air_conditioning_yn = air_conditioning_yn;
    }

    @Column(name = "balcony_yn", unique = false, nullable = false)
    public Boolean getBalcony() {
        return this.balcony_yn;
    }

    public void setBalcony(Boolean balcony_yn) {
        this.balcony_yn = balcony_yn;
    }

    @Column(name = "basement_yn", unique = false, nullable = false)
    public Boolean getBasement_yn() {
        return this.basement_yn;
    }

    public void setBasement_yn(Boolean basement_yn) {
        this.basement_yn = basement_yn;
    }

    @Column(name = "elevator_yn", unique = false, nullable = false)
    public Boolean getElevator_yn() {
        return this.elevator_yn;
    }

    public void setElevator_yn(Boolean elevator_yn) {
        this.elevator_yn = elevator_yn;
    }


    @Column(name = "forced_air_yn", unique = false, nullable = false)
    public Boolean getForced_air_yn() {
        return this.forced_air_yn;
    }

    public void setForced_air_yn(Boolean forced_air_yn) {
        this.forced_air_yn = forced_air_yn;
    }

    @Column(name = "foreclosure_yn", unique = false, nullable = false)
    public Boolean getForeclosure_yn() {
        return this.foreclosure_yn;
    }

    public void setForeclosure_yn(Boolean foreclosure_yn) {
        this.foreclosure_yn = foreclosure_yn;
    }

    @Column(name = "furnished_yn", unique = false, nullable = false)
    public Boolean getFurnished_yn() {
        return this.furnished_yn;
    }

    public void setFurnished_yn(Boolean furnished_yn) {
        this.furnished_yn = furnished_yn;
    }

    @Column(name = "garage_yn", unique = false, nullable = false)
    public Boolean getGarage_yn() {
        return this.garage_yn;
    }

    public void setGarage_yn(Boolean garage_yn) {
        this.garage_yn = garage_yn;
    }

    @Column(name = "garden_yn", unique = false, nullable = false)
    public Boolean getGarden_yn() {
        return this.garden_yn;
    }

    public void setGarden_yn(Boolean garden_yn) {
        this.garden_yn = garden_yn;
    }

    @Column(name = "gated_community_yn", unique = false, nullable = false)
    public Boolean getGated_community_yn() {
        return this.gated_community_yn;
    }

    public void setGated_community_yn(Boolean gated_community_yn) {
        this.gated_community_yn = gated_community_yn;
    }

    @Column(name = "golf_course_yn", unique = false, nullable = false)
    public Boolean getGolf_course_yn() {
        return this.golf_course_yn;
    }

    public void setGolf_course_yn(Boolean golf_course_yn) {
        this.golf_course_yn = golf_course_yn;
    }

    @Column(name = "horse_property_yn", unique = false, nullable = false)
    public Boolean getHorse_property_yn() {
        return this.horse_property_yn;
    }

    public void setHorse_property_yn(Boolean horse_property_yn) {
        this.horse_property_yn = horse_property_yn;
    }

    @Column(name = "multilevel_yn", unique = false, nullable = false)
    public Boolean getMultilevel_yn() {
        return this.multilevel_yn;
    }

    public void setMultilevel_yn(Boolean multilevel_yn) {
        this.multilevel_yn = multilevel_yn;
    }

    @Column(name = "new_construction_yn", unique = false, nullable = false)
    public Boolean getNew_construction_yn() {
        return this.new_construction_yn;
    }

    public void setNew_construction_yn(Boolean new_construction_yn) {
        this.new_construction_yn = new_construction_yn;
    }

    @Column(name = "pets_yn", unique = false, nullable = false)
    public Boolean getPets_yn() {
        return this.pets_yn;
    }

    public void setPets_yn(Boolean pets_yn) {
        this.pets_yn = pets_yn;
    }

    @Column(name = "pool_yn", unique = false, nullable = false)
    public Boolean getPool_yn() {
        return this.pool_yn;
    }

    public void setPool_yn(Boolean pool_yn) {
        this.pool_yn = pool_yn;
    }

    @Column(name = "reo_yn", unique = false, nullable = false)
    public Boolean getReo_yn() {
        return this.reo_yn;
    }

    public void setReo_yn(Boolean reo_yn) {
        this.reo_yn = reo_yn;
    }

    @Column(name = "short_sale_yn", unique = false, nullable = false)
    public Boolean getShort_sale_yn() {
        return this.short_sale_yn;
    }

    public void setShort_sale_yn(Boolean short_sale_yn) {
        this.short_sale_yn = short_sale_yn;
    }

    @Column(name = "storage_yn", unique = false, nullable = false)
    public Boolean getStorage_yn() {
        return this.storage_yn;
    }

    public void setStorage_yn(Boolean storage_yn) {
        this.storage_yn = storage_yn;
    }

    @Column(name = "view_yn", unique = false, nullable = false)
    public Boolean getView_yn() {
        return this.view_yn;
    }

    public void setView_yn(Boolean view_yn) {
        this.view_yn = view_yn;
    }




}
