package com.bookstore.servlets;

import com.bookstore.dao.BookDAO;
import com.bookstore.models.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        Book book = BookDAO.getBookById(bookId);
        HttpSession session = request.getSession();
        List<Book> cart = (List<Book>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(book);
        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
}