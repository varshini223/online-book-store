package com.bookstore.dao;


import com.bookstore.models.Book;
import com.bookstore.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM books";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getDouble("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static Book getBookById(int bookId) {
        return null;
    }

    public boolean addBook(Book newBook) {
        return false;
    }

    public boolean deleteBook(int bookId) {
        return false;
    }
}