package main.businessPackage;

import main.dataAccessPackage.UserDAO;
import main.dataAccessPackage.UserDAOImpl;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LocalityException;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;

import java.util.List;

public class UserManager implements UserDAO {
    private UserDAO userDAO;

    public UserManager() throws ConnectionDataAccessException {
        setUserDAO(new UserDAOImpl());
    }

    private void setUserDAO(UserDAO dao) {
        this.userDAO = dao;
    }

    public Boolean createUser(UserModel user) throws ConnectionDataAccessException {
       return userDAO.createUser(user);
    }

    @Override
    public List<UserModel> getAllUsers() throws ConnectionDataAccessException {
        return userDAO.getAllUsers();
    }

    @Override
    public UserModel getUser(String email) throws ConnectionDataAccessException {
        return userDAO.getUser(email);
    }

    @Override
    public Boolean updateUser(UserModel user) throws ConnectionDataAccessException {
        return userDAO.updateUser(user);
    }

    @Override
    public Boolean deleteUser(UserModel user) throws ConnectionDataAccessException {
        return userDAO.deleteUser(user);
    }

    @Override
    public List<LocalityModel> getLocality(String countryName) throws ConnectionDataAccessException, LocalityException {
        return userDAO.getLocality(countryName);
    }
}
