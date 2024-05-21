package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Connection connection;

    private List<String> columnNames;

    public UserDAOImpl() throws ConnectionDataAccessException {
        connection = ConnectionDataAccess.getInstance();
    }

    @Override
    public Boolean createUser(UserModel user) throws ConnectionDataAccessException {
        return null;
    }

    @Override
    public UserModel getUser(String email) throws ConnectionDataAccessException {
        return null;
    }

    @Override
    public Boolean updateUser(UserModel user) throws ConnectionDataAccessException {
        try{
            PreparedStatement ps = connection.prepareStatement(" UPDATE user SET username = ?, password = ?, date_of_birth = ? , gender = ? , street_and_number = ? , phone_number = ? , bio = ? , admin = ? WHERE email = ?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setDate(3, (Date) user.getDateOfBirth());
            ps.setString(4, String.valueOf(user.getGender()));
            ps.setString(5, user.getStreetAndNumber());
            ps.setString(6, user.getPhoneNumber());
            ps.setString(7, user.getBio());
            ps.setBoolean(8, user.isAdmin());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deleteUser(UserModel user) throws ConnectionDataAccessException {
        try{
            PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE email = ?");
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> getColumnsNames() throws ConnectionDataAccessException {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user LIMIT 1");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnNames;
    }

    public List<UserModel> getAllUsers() throws ConnectionDataAccessException {
        List<UserModel> users = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                UserModel user = new UserModel();
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDateOfBirth(rs.getDate("date_of_birth"));
                user.setGender(rs.getCharacterStream("gender").toString().charAt(0));
                user.setStreetAndNumber(rs.getString("street_and_number"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setBio(rs.getString("bio"));
                user.setAdmin(rs.getBoolean("admin"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
