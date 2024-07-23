package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.DirectMessageException;
import main.modelPackage.DirectMessageModel;
import main.modelPackage.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectMessageDAOImpl implements DirectMessageDAO {
    private Connection connection;

    public DirectMessageDAOImpl() throws ConnectionDataAccessException {
        connection = ConnectionDataAccess.getInstance();
    }

    @Override
    public List<DirectMessageModel> getDirectMessagesByUserId(int userId) throws DirectMessageException {
        try {
            String sql = "SELECT dm.text, m.url, u.username, mt.type_name FROM direct_message dm " +
                    "LEFT JOIN media m on dm.id = m.attachment " +
                    "LEFT JOIN media_type mt on m.format = mt.id " +
                    "JOIN receiver r on dm.id = r.direct_message " +
                    "JOIN user u on u.id = r.user " +
                    "WHERE dm.sender = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            List<DirectMessageModel> directMessageModels = new ArrayList<>();
            while (rs.next()) {
                DirectMessageModel directMessageModel = new DirectMessageModel(
                        new UserModel(rs.getString("username")),
                        rs.getString("text"),
                        rs.getString("url"),
                        rs.getString("type_name")
                );
                directMessageModels.add(directMessageModel);
            }

            return directMessageModels;
        } catch (SQLException e) {
            throw new DirectMessageException(e.getMessage());
        }
    }
}
