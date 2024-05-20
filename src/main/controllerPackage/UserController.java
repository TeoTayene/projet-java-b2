package main.controllerPackage;

import main.businessPackage.UserManager;
import main.dataAccessPackage.UserDAO;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LocalityException;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;

import java.util.List;

public class UserController implements UserDAO {
    private UserManager userManager;

    public UserController() throws ConnectionDataAccessException {
        userManager = new UserManager();
    }

    public Boolean createUser(UserModel user) throws ConnectionDataAccessException {
        return userManager.createUser(user);
    }

    @Override
    public List<UserModel> getAllUsers() throws ConnectionDataAccessException {
        return userManager.getAllUsers();
    }

    @Override
    public UserModel getUser(String email) throws ConnectionDataAccessException {
        return userManager.getUser(email);
    }

    @Override
    public Boolean updateUser(UserModel user) throws ConnectionDataAccessException {
        return userManager.updateUser(user);
    }

    @Override
    public Boolean deleteUser(UserModel user) throws ConnectionDataAccessException {
        return userManager.deleteUser(user);
    }

    @Override
    public List<LocalityModel> getLocality(String countryName) throws ConnectionDataAccessException, LocalityException {
        return userManager.getLocality(countryName);
    }
}
