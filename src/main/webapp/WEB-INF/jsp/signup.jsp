<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>

<body>
<%--back to home--%>
<br>
<a href="/">Back to Home</a>
<br>
<div style="text-align: center;">
<h2>Register</h2>

<form action="/signup" method="post">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br><br>
    <label for="email">Email:</label><br>
    <input type="text" id="email" name="email"><br><br>
    <label for="phone">Phone:</label><br>
    <input type="text" id="phone" name="phone"><br><br>
    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password"><br><br>
    <input type="submit" value="Register">
</form>
</div>
</body>
</html>
