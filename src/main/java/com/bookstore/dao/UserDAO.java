package com.bookstore.dao;

import com.bookstore.models.User;
import com.bookstore.util.DBConnection;
import java.sql.*;
import java.util.Collections;
import java.util.List;

public class UserDAO {
    public static User getUserByEmail(String email) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addUser(User user) {
    }

    public List<User> getAllUsers() {
        return Collections.emptyList();
    }
}