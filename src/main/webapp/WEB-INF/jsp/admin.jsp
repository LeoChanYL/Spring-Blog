<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>User List</title>
    <style>
        table, th, td {
            border: 0;
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
<br>
<a href="/">Back to Home</a>
<br>
<h1>User List</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Roles</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.ID}</td>
            <td>${user.NAME}</td>
            <td>${user.EMAIL}</td>
            <td>${user.Phone}</td>
            <td><c:forEach items="${user.ROLES}" var="role">${role.AUTHORITY}</c:forEach></td>
            <td>${user.DESCRIPTION}</td>
            <td>

                <form action="/profile/${user.name}" method="Get" style="display: inline-block;">
                    <button type="submit">Detail</button>
                </form>
                <form action="/SetAdmin/${user.name}" method="POST" style="display: inline-block;">
                    <button type="submit">Set Admin</button>
                </form>
                <form action="/delete/User/${user.name}" method="POST" style="display: inline-block;">
                    <button type="submit">Delete User</button>
                </form>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

