package server.manager;

import com.mysql.cj.Session;
import core.db.DBConnectionProvider;
import core.model.Item;
import core.model.User;

import java.sql.*;
import java.text.ParseException;

public class UserManager {
    private final Connection connection = DBConnectionProvider.getINSTANCE().getConnection();

    public void addUser(User user) {
        String sql = "INSERT INTO user(name, surname, email, password) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(1, user.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM user where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery(sql);
            if (resultSet.next()){
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user where email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery(sql);
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByAndPassword(String email, String password) {
        String sql = "SELECT * FROM user where email = ? and password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery(sql);
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException, ParseException {
        return User.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .build();
    }
}
