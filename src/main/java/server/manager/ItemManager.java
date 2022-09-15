package server.manager;

import core.db.DBConnectionProvider;
import core.model.Item;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private final Connection connection = DBConnectionProvider.getINSTANCE().getConnection();

    public void addItem(Item item){
        String sql = "INSERT INTO item(title, price,category_id, pic_url, user_id) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getCategoryId());
            ps.setString(4, item.getPicUrl());
            ps.setInt(5, item.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeItemById(int id){
        String sql = "DELETE FROM item WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item LIMIT 20";
        try {
            Statement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getItemsByUser(int id) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item WHERE user_id = ? LIMIT 20";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery(sql);
            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getItemsByCategory(int id) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item WHERE category_id = ? LIMIT 20";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return items;
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException, ParseException {
        return Item.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .price(resultSet.getDouble("price"))
                .categoryId(resultSet.getInt("category_id"))
                .picUrl(resultSet.getString("pic_url"))
                .userId(resultSet.getInt("user_id"))
                .build();
    }
}
