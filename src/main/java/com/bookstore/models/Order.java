package com.bookstore.models;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    private int id;
    private int userId;
    private int bookId;
    private int quantity;

    public Order(int id, int userId, int bookId, int quantity) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public Order() {

    }

    public Order(int i, int id, List<Book> cart) {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getShippingAddress() {
        return "";
    }

    public String getPaymentMethod() {
        return "";
    }

    public void setShippingAddress(String shippingAddress) {
    }

    public void setPaymentMethod(String paymentMethod) {
    }

    public void setOrderDate(Timestamp orderDate) {
    }

    public void setStatus(String status) {
    }
}