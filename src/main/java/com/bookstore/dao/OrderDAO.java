package com.bookstore.dao;

import com.bookstore.models.Order;
import com.bookstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public static void placeOrder(Order order) {

    }

    public boolean createOrder(Order order) {
        boolean isOrderCreated = false;
        String query = "INSERT INTO orders (user_id, shipping_address, payment_method, order_date, status) VALUES (?, ?, ?, NOW(), ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getShippingAddress());
            statement.setString(3, order.getPaymentMethod());
            statement.setString(4, "Pending");  // Default order status

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                isOrderCreated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isOrderCreated;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders ORDER BY order_date DESC";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setShippingAddress(resultSet.getString("shipping_address"));
                order.setPaymentMethod(resultSet.getString("payment_method"));
                order.setOrderDate(resultSet.getTimestamp("order_date"));
                order.setStatus(resultSet.getString("status"));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setShippingAddress(resultSet.getString("shipping_address"));
                order.setPaymentMethod(resultSet.getString("payment_method"));
                order.setOrderDate(resultSet.getTimestamp("order_date"));
                order.setStatus(resultSet.getString("status"));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }


    public boolean updateOrderStatus(int orderId, String status) {
        boolean isOrderUpdated = false;
        String query = "UPDATE orders SET status = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, status);
            statement.setInt(2, orderId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                isOrderUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isOrderUpdated;
    }
}
