<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <h2>Checkout</h2>
    <form action="CheckoutServlet" method="post">
        <label for="address">Shipping Address:</label>
        <input type="text" name="address" required><br>

        <label for="payment">Payment Method:</label>
        <select name="payment" required>
            <option value="creditCard">Credit Card</option>
            <option value="paypal">PayPal</option>
        </select><br>

        <input type="submit" value="Place Order">
    </form>
</body>
</html>
