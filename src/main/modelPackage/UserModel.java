package main.modelPackage;

import java.sql.Date;

public class UserModel {
    private int id;
    private String email;
    private String username;
    private String password;
    private Date dateOfBirth;
    private char gender;
    private Date createdAt;
    private String streetAndNumber;
    private String phoneNumber;
    private String bio;
    private boolean isAdmin;
    private Integer home;

    public UserModel(int id, String email, String username, String password, Date dateOfBirth, char gender,
                     Date createdAt, String streetAndNumber, String phoneNumber, String bio, boolean isAdmin, int home) {
        setId(id);
        setEmail(email);
        setUsername(username);
        setPassword(password);
        setDateOfBirth(dateOfBirth);
        setGender(gender);
        setCreatedAt(createdAt);
        setStreetAndNumber(streetAndNumber);
        setPhoneNumber(phoneNumber);
        setBio(bio);
        setAdmin(isAdmin);
        setHome(home);
    }

    public UserModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Integer getHome() {
        return home;
    }

    public void setHome(Integer home) {
        this.home = home;
    }
}
