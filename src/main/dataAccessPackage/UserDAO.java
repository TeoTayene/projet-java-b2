package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LocalityException;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;

import java.util.List;

public interface UserDAO {
    public Boolean createUser(UserModel user) throws ConnectionDataAccessException;
    public List<UserModel> getAllUsers() throws ConnectionDataAccessException;
    public UserModel getUser(String email) throws ConnectionDataAccessException;
    public Boolean updateUser(UserModel user) throws ConnectionDataAccessException;
    public Boolean deleteUser(UserModel user) throws ConnectionDataAccessException;
    public List<LocalityModel> getLocality(String countryName) throws ConnectionDataAccessException, LocalityException;
}
