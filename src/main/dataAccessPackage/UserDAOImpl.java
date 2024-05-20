package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LocalityException;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Connection connection;
    private List<UserModel> users;
    private List<LocalityModel> localities;

    public UserDAOImpl() throws ConnectionDataAccessException {
        connection = ConnectionDataAccess.getInstance();
    }

    @Override
    public Boolean createUser(UserModel user) throws ConnectionDataAccessException {
        return null;
    }

    @Override
    public List<UserModel> getAllUsers() throws ConnectionDataAccessException {
        List<UserModel> users = new ArrayList<>();
        return users;
    }

    @Override
    public UserModel getUser(String email) throws ConnectionDataAccessException {
        return null;
    }

    @Override
    public Boolean updateUser(UserModel user) throws ConnectionDataAccessException {
        return null;
    }

    @Override
    public Boolean deleteUser(UserModel user) throws ConnectionDataAccessException {
        return null;
    }

    @Override
    public List<LocalityModel> getLocality(String countryName) throws LocalityException {
        try {
            String query = "SELECT l.* FROM localisation AS loc " +
                    "JOIN locality AS l on loc.locality = l.code " +
                    "JOIN country AS c on loc.country = c.id " +
                    "WHERE c.name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, countryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            localities = new ArrayList<>();

            while (resultSet.next()) {
                LocalityModel locality = new LocalityModel(
                    resultSet.getInt("code"),
                    resultSet.getString("name"),
                    resultSet.getString("city"),
                    resultSet.getInt("zip_code")
                );
                localities.add(locality);
            }
        } catch (SQLException e) {
            throw new LocalityException(e.getMessage());
        }
        return localities;
    }
}
