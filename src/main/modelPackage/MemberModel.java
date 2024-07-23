package main.modelPackage;

public class MemberModel {
    private Integer community;
    private Integer user;
    private String username;
    private String streetAndNumber;
    private String locationName;
    private int zipCode;
    private String country ;

    public MemberModel(String username, String streetAndNumber, String locationName, int zipCode, String country) {
        setUsername(username);
        setStreetAndNumber(streetAndNumber);
        setLocationName(locationName);
        setZipCode(zipCode);
        setCountry(country);
    }

    public Integer getCommunity() {
        return community;
    }

    public void setCommunity(Integer community) {
        this.community = community;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
