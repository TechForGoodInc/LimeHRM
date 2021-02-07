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
    
    public String getStreetName() {
        return streetName;
    }
    
    public Address setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }
    
    public String getApartmentOrUnitNumber() {
        return apartmentOrUnitNumber;
    }
    
    public Address setApartmentOrUnitNumber(String apartmentOrUnitNumber) {
        this.apartmentOrUnitNumber = apartmentOrUnitNumber;
        return this;
    }
    
    public String getCity() {
        return city;
    }
    
    public Address setCity(String city) {
        this.city = city;
        return this;
    }
    
    public String getState() {
        return state;
    }
    
    public Address setState(String state) {
        this.state = state;
        return this;
    }
    
    public String getZip() {
        return zip;
    }
    
    public Address setZip(String zip) {
        this.zip = zip;
        return this;
    }
    
    public String getCountry() {
        return country;
    }
    
    public Address setCountry(String country) {
        this.country = country;
        return this;
    }
}
