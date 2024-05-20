package main.modelPackage;

public class LocalityModel {
    private int code;
    private String name;
    private String city;
    private int zipCode;

    public LocalityModel(int code, String name, String city, int zipCode) {
        setCode(code);
        setName(name);
        setCity(city);
        setZipCode(zipCode);
    }

    public LocalityModel() {}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
