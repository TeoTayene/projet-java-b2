package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LikeSearchException;
import main.modelPackage.LikeModel;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class LikeDAOImpl implements LikeDAO {
    private Connection connection;

    public LikeDAOImpl() throws ConnectionDataAccessException {
        connection = ConnectionDataAccess.getInstance();
    }

    public List<LikeModel> getLikesBetween(Date startDate, Date endDate) throws LikeSearchException {
        List<LikeModel> likes = new ArrayList<>();
        try
        {
            String sql = "SELECT l.id, l.date, u.username, p.text " +
                    "FROM social_network.like l " +
                    "JOIN user u ON l.liked_by = u.id " +
                    "JOIN post p ON l.post_liked = p.id " +
                    "WHERE l.date BETWEEN ? AND ? ORDER BY l.date DESC";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LikeModel likeModel = new LikeModel(
                        resultSet.getInt("id"),
                        resultSet.getDate("date"),
                        resultSet.getString("username"),
                        resultSet.getString("text")
                );
                likes.add(likeModel);
            }

            if (likes.isEmpty()) throw new LikeSearchException("Aucun like trouvé pour cette période.");
            return likes;
        } catch (SQLException e) {
            throw new LikeSearchException(e.getMessage());
        }
    }
}
