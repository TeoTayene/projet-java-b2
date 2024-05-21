package main.controllerPackage;

import main.businessPackage.UserManager;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.UserModel;

import java.util.List;

public class UserController {
    private UserManager userManager;

    public UserController() throws ConnectionDataAccessException {
        userManager = new UserManager();
    }

    public Boolean createUser(UserModel user) throws ConnectionDataAccessException {
        return userManager.createUser(user);
    }

    public List<String> getColumnsNames() throws ConnectionDataAccessException {
        return userManager.getColumnsNames();
    }

    public UserModel getUser(String email) throws ConnectionDataAccessException {
        return userManager.getUser(email);
    }

    public List<UserModel> getAllUsers() throws ConnectionDataAccessException {
        return userManager.getAllUsers();
    }

}
