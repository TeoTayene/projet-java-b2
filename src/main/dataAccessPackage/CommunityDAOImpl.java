package main.dataAccessPackage;

import main.exceptionPackage.CommunityDAOException;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.CommunityModel;
import main.modelPackage.MemberModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommunityDAOImpl implements CommunityDAO {
    private Connection connection;

    public CommunityDAOImpl() throws ConnectionDataAccessException {
        connection = ConnectionDataAccess.getInstance();
    }

    public List<CommunityModel> getAllCommunities() throws CommunityDAOException {
        List<CommunityModel> communities = new ArrayList<>();
        try {
            String sql = "SELECT * FROM community";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                communities.add(fillCommunity(rs));
            }
        } catch (SQLException e) {
            throw new CommunityDAOException(e.getMessage());
        }
        return communities;
    }

    public List<MemberModel> getCommunityById(int id) throws CommunityDAOException {
        List<MemberModel> community = new ArrayList<>();
        try {
            String sql = "SELECT u.username, u.street_and_number, l.city, l.zip_code, p.name " +
                    "FROM social_network.member m " +
                    "JOIN user u ON m.user = u.id " +
                    "JOIN locality l ON u.home = l.code " +
                    "JOIN country p ON l.localisation = p.id " +
                    "WHERE m.community = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                community.add(fillMember(rs));
            }
        } catch (SQLException e) {
            throw new CommunityDAOException(e.getMessage());
        }
        return community;
    }

    private CommunityModel fillCommunity(ResultSet rs) throws SQLException {
       return new CommunityModel(
               rs.getInt("id"),
               rs.getString("name"),
               rs.getInt("creator")
       );
    }

    private MemberModel fillMember(ResultSet rs) throws SQLException {
        return new MemberModel(
                rs.getString("username"),
                rs.getString("street_and_number"),
                rs.getString("city"),
                rs.getInt("zip_code"),
                rs.getString("name")
        );
    }
}
