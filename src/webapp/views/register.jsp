<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <h2>Create a New Account</h2>
    <form action="RegisterServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" name="password" required><br>

        <input type="submit" value="Register">
    </form>
    <a href="login.jsp">Already have an account? Login here</a>
</body>
</html>
