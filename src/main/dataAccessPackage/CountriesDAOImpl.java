package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.CountriesDAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountriesDAOImpl implements CountriesDAO {
    private Connection connection;
    private List<String> countries;

    public CountriesDAOImpl() throws ConnectionDataAccessException {
        connection = ConnectionDataAccess.getInstance();
    }

    @Override
    public List<String> getCountries() throws CountriesDAOException {
        try {
            String sqlInstruction = "SELECT name FROM country";
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sqlInstruction);
            countries = new ArrayList<>();

            while (resultSet.next()) {
                countries.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new CountriesDAOException(e.getMessage());
        }
        return countries;
    }
}
