package main.businessPackage;

import main.dataAccessPackage.UserDAO;
import main.dataAccessPackage.UserDAOImpl;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.UserModel;

import java.util.List;

public class UserManager {
    private UserDAO userDAO;

    public UserManager() throws ConnectionDataAccessException {
        setUserDAO(new UserDAOImpl());
    }

    public UserModel getUser(String email) throws ConnectionDataAccessException {
        return userDAO.getUser(email);
    }

    private void setUserDAO(UserDAO dao) {
        this.userDAO = dao;
    }

    public Boolean createUser(UserModel user) throws ConnectionDataAccessException {
        return userDAO.createUser(user);
    }

    public List<String> getColumnsNames() throws ConnectionDataAccessException {
        return userDAO.getColumnsNames();
    }
    public List<UserModel> getAllUsers() throws ConnectionDataAccessException {
        return userDAO.getAllUsers();
    }
}
