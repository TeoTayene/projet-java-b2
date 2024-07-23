package main.dataAccessPackage;

import main.exceptionPackage.*;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;

import java.sql.Date;
import java.util.List;

public interface UserDAO {
    public Boolean createUser(UserModel user) throws UserCreationException;
    public Boolean updateUser(UserModel user) throws UpdateUserException, UserCreationException;
    public Boolean deleteUser(UserModel user) throws UserDeletionException;
    public List<UserModel> getAllUsers() throws UserSearchException;
    public UserModel getUser(int id) throws UserSearchException;
    public List<LocalityModel> getLocality(String countryName) throws LocalityException;
    public List<String> getColumnsNames() throws UserSearchException;
    public String getCountryNameByHome(int id) throws CountriesDAOException;
    public int getNbUser() throws UserSearchException;
    public List<UserModel> getUsersByCountry(String  name) throws UserSearchException;
    public List<UserModel> getUsersByAge(Date startDateOfBirth , Date endDateOfBirth) throws UserSearchException;
    public Boolean login(int id, String email, String password) throws LoginException;
}
