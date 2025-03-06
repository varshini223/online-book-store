package com.bookstore.servlets;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.models.Book;
import com.bookstore.models.Order;
import com.bookstore.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private BookDAO bookDAO;
    private UserDAO userDAO;
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        bookDAO = new BookDAO();
        userDAO = new UserDAO();
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Book> books = bookDAO.getAllBooks();
        List<User> users = userDAO.getAllUsers();
        List<Order> orders = orderDAO.getAllOrders();

        request.setAttribute("books", books);
        request.setAttribute("users", users);
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookTitle = request.getParameter("bookTitle");
        String bookAuthor = request.getParameter("bookAuthor");
        double bookPrice = Double.parseDouble(request.getParameter("bookPrice"));
        int bookStock = Integer.parseInt(request.getParameter("bookStock"));

        Book newBook = new Book();
        newBook.setTitle(bookTitle);
        newBook.setAuthor(bookAuthor);
        newBook.setPrice(bookPrice);
        newBook.setStock(bookStock);

        boolean isBookAdded = bookDAO.addBook(newBook);

        if (isBookAdded) {
            response.sendRedirect("admin?status=bookAdded");
        } else {
            request.setAttribute("errorMessage", "Failed to add the book.");
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        boolean isBookDeleted = bookDAO.deleteBook(bookId);

        if (isBookDeleted) {
            response.sendRedirect("admin?status=bookDeleted");
        } else {
            response.sendRedirect("admin?status=error");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String status = request.getParameter("status");

        boolean isOrderUpdated = orderDAO.updateOrderStatus(orderId, status);

        if (isOrderUpdated) {
            response.sendRedirect("admin?status=orderUpdated");
        } else {
            response.sendRedirect("admin?status=error");
        }
    }
}
