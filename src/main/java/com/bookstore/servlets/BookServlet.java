package com.bookstore.servlets;

import com.bookstore.dao.BookDAO;
import com.bookstore.models.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = BookDAO.getAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("books.jsp").forward(request, response);
    }
}
