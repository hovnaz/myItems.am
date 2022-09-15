package server.manager;

import core.db.DBConnectionProvider;
import core.model.Category;
import core.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private final Connection connection = DBConnectionProvider.getINSTANCE().getConnection();

    void addCategory(Category category) {
        String sql = "INSERT INTO category(name) VALUE(?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    List<Category> getAllCategories() {
        String sql = "INSERT INTO category(name) VALUE(?)";
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                categories.add(getCategoryFromResultSet(resultSet));
            }
            ps.executeUpdate();
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    private Category getCategoryFromResultSet(ResultSet resultSet) throws SQLException, ParseException {
        return Category.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }
}
