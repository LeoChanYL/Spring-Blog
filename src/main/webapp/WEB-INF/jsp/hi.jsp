
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>index page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
    <style>
        form {
            display: inline-block;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="p-4 shadow-4 rounded-3 " style="background-color: hsl(0, 0%, 94%); ">

<h1>TBD PhotoBlog!</h1>
<h2>Welcome ${name} !</h2>
        <hr class="my-4" />
<%--login button--%>
<security:authorize access="!hasRole('ROLE_ADMIN')and !hasRole('ROLE_USER')">
<form action="/login" method="get">
    <input class="btn btn-primary" type="submit" value="login"/>
</form>
<form action="/signup" method="get">
    <input class="btn btn-primary" type="submit" value="register"/>
</form>
</security:authorize>

<security:authorize access="hasRole('ROLE_ADMIN')">
    <form action="/admin" method="get">
        <input class="btn btn-primary" type="submit" value="Admin Page"/>
    </form>
</security:authorize>

<security:authorize access="hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')">
    <form action="/profile/${name}" method="get">
        <input class="btn btn-primary" type="submit" value="profile"/>
    </form>

    <form action="/logout" method="get">
        <input class="btn btn-primary" type="submit" value="logout"/>
    </form>

    <form action="/upload" method="get" >
        <input class="btn btn-primary" type="submit" value="Upload photo"/>
    </form>
</security:authorize>

    </div>

    <br>
<div class="row">
<c:forEach items="${List}" var="name">
    <div class="col-md-4">

<a href="/showDetail/${name.id}">
    <div class="card shadow-sm " style="margin-bottom: 20px;">
        <img src="data:image/png;base64,${name.image}" class="card-img-top" alt="image"  width="100%" height="auto"/>
        <div class="card-body bg-secondary text-white">
    <p class="card-text">${name.name}  by: ${name.uid},<br>
           Created at: ${name.timestamp}</p>
        </div>

    </div>
</a>
    </div>
</c:forEach>
</div>
</div>

</body>
</html>