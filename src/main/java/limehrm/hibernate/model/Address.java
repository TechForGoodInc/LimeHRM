package limehrm.hibernate.model;

public class Address {
    private String streetName;
    private String apartmentOrUnitNumber;
    private String city;
    private String state;
    private String zip;
    private String country;
    
    public Address() {}
    
    public Address(String streetName, String apartmentOrUnitNumber, String city, String state, String zip, String country) {
        this.streetName = streetName;
        this.apartmentOrUnitNumber = apartmentOrUnitNumber;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }
    
    
    /** 
     * @return String
     */
    public String getStreetName() {
        return streetName;
    }
    
    
    /** 
     * @param streetName
     * @return Address
     */
    public Address setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getApartmentOrUnitNumber() {
        return apartmentOrUnitNumber;
    }
    
    
    /** 
     * @param apartmentOrUnitNumber
     * @return Address
     */
    public Address setApartmentOrUnitNumber(String apartmentOrUnitNumber) {
        this.apartmentOrUnitNumber = apartmentOrUnitNumber;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getCity() {
        return city;
    }
    
    
    /** 
     * @param city
     * @return Address
     */
    public Address setCity(String city) {
        this.city = city;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getState() {
        return state;
    }
    
    
    /** 
     * @param state
     * @return Address
     */
    public Address setState(String state) {
        this.state = state;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getZip() {
        return zip;
    }
    
    
    /** 
     * @param zip
     * @return Address
     */
    public Address setZip(String zip) {
        this.zip = zip;
        return this;
    }
    
    
    /** 
     * @return String
     */
    public String getCountry() {
        return country;
    }
    
    
    /** 
     * @param country
     * @return Address
     */
    public Address setCountry(String country) {
        this.country = country;
        return this;
    }
}
