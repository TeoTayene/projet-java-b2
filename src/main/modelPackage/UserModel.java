package main.modelPackage;

import main.utilPackage.FormValidator;

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

    // overload pour les messages privés car pas besoin de tout les champs
    public UserModel(String username) {
        setUsername(username);
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
        if (FormValidator.isFieldNull(email)
                || FormValidator.isOneStringEmpty(email)
                || FormValidator.stringContainsSpace(email)
                || !FormValidator.validStringLength(email, 1, 50)
                || !FormValidator.isValidEmail(email))
           return;


        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (FormValidator.isFieldNull(username) || FormValidator.isOneStringEmpty(username) || !FormValidator.validStringLength(username, 1, 20))
            return;

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (FormValidator.isFieldNull(password) || FormValidator.isOneStringEmpty(password))
            return;
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        if (FormValidator.isFieldNull(dateOfBirth) || !FormValidator.validDateOfBirth(dateOfBirth.toLocalDate()))
            return;
        this.dateOfBirth = dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        if (!FormValidator.validGender(gender)) return;
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
        if (FormValidator.isFieldNull(streetAndNumber) || FormValidator.isOneStringEmpty(streetAndNumber))
            return;
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
