<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books - Online Bookstore</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <div class="header">
        <h1>Welcome to the Online Bookstore</h1>
        <nav>
            <a href="home.jsp">Home</a>
            <a href="books.jsp">Books</a>
            <a href="cart.jsp">Cart</a>
            <a href="checkout.jsp">Checkout</a>
            <a href="logout.jsp">Logout</a>
        </nav>
    </div>

    <h2>Available Books</h2>

    <div class="books-list">
        <!-- Loop through the list of books -->
        <c:forEach var="book" items="${books}">
            <div class="book-item">
                <img src="assets/images/${book.image}" alt="${book.name}" />
                <h3>${book.name}</h3>
                <p>Author: ${book.author}</p>
                <p>Price: $${book.price}</p>
                <form action="CartServlet" method="post">
                    <input type="hidden" name="bookId" value="${book.id}" />
                    <input type="submit" value="Add to Cart" />
                </form>
            </div>
        </c:forEach>
    </div>

    <script src="assets/js/script.js"></script>
</body>
</html>
