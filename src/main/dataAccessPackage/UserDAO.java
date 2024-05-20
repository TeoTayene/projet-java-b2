package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.UserModel;

import java.util.List;

public interface UserDAO {
    public Boolean createUser(UserModel user) throws ConnectionDataAccessException;
    public UserModel getUser(String email) throws ConnectionDataAccessException;
    public Boolean updateUser(UserModel user) throws ConnectionDataAccessException;
    public Boolean deleteUser(UserModel user) throws ConnectionDataAccessException;
    public List<String> getColumnsNames() throws ConnectionDataAccessException;

    public List<UserModel> getAllUsers() throws ConnectionDataAccessException;
}
