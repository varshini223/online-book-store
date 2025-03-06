package com.bookstore.servlets;

import com.bookstore.dao.UserDAO;
import com.bookstore.models.User;
import com.bookstore.util.PasswordHash;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = PasswordHash.hashPassword(request.getParameter("password"));
        User user = new User(0, name, email, password);
        UserDAO.addUser(user);
        response.sendRedirect("login.jsp?message=Registration successful");
    }
}