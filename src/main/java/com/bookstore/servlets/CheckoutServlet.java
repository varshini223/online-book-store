package com.bookstore.servlets;

import com.bookstore.dao.OrderDAO;
import com.bookstore.models.Book;
import com.bookstore.models.Order;
import com.bookstore.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Book> cart = (List<Book>) session.getAttribute("cart");
        if (user == null || cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp?error=No items in cart");
            return;
        }
        Order order = new Order(0, user.getId(), cart);
        OrderDAO.placeOrder(order);
        session.removeAttribute("cart");
        response.sendRedirect("orders.jsp?message=Order placed successfully");
    }
}
