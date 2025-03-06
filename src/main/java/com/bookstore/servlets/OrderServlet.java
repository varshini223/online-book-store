package com.bookstore.servlets;

import com.bookstore.dao.OrderDAO;
import com.bookstore.models.Order;
import com.bookstore.models.User;
import com.bookstore.util.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (user.getRole().equals("admin")) {
            List<Order> orders = orderDAO.getAllOrders();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } else {

            List<Order> userOrders = orderDAO.getOrdersByUserId(user.getId());
            request.setAttribute("orders", userOrders);
            request.getRequestDispatcher("orders.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String shippingAddress = request.getParameter("shippingAddress");
        String paymentMethod = request.getParameter("paymentMethod");



        Order order = new Order();
        order.setUserId(user.getId());
        order.setShippingAddress(shippingAddress);
        order.setPaymentMethod(paymentMethod);


        boolean isOrderCreated = orderDAO.createOrder(order);

        if (isOrderCreated) {

            response.sendRedirect("orders?status=success");
        } else {

            request.setAttribute("errorMessage", "Order creation failed.");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }
    }
}
