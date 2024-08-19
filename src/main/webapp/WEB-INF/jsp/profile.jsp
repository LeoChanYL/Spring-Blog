
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>

</head>

<body>
<br>
<a href="/">Back to Home</a>
<br>
<div style="text-align: center;">
<h1>User Profile</h1>
<h2>${user.name}</h2>
<security:authorize access="hasRole('ROLE_ADMIN')">
    <form action="/delete/User/${user.name}" method="post" >
        <input type="submit" value="Delete User">
    </form>
    <form action="/setadmin/${user.name}" method="post" >
        <input type="submit" value="Set Admin">
</form>
</security:authorize>
<p>${user.description}</p>

<security:authorize access="hasRole('ROLE_ADMIN') or  ('${user.name}' == authentication.name)">

    <form action="./update/${user.name}" method="post" >
        <input type="text" name="description" placeholder="Title">
        <input type="submit" value="Update">
    </form>

</security:authorize>
</div>
<h2>Photos</h2>
<hr>
<c:if test="${photos.size() == 0}">
    <p>No photos yet</p>
<hr>
</c:if>
<c:forEach items="${photos}" var="photo">


    <h3>${photo.id}:${photo.name}</h3>
    <p>${photo.description}</p>

    <img src="data:image/png;base64,${photo.image}" width="30%" height="auto">
    <p>Uploaded on ${photo.created_at}</p>
    <a href="/showDetail/${photo.id}">View Details</a>
    <security:authorize access="hasRole('ROLE_ADMIN')">
        <form action="/delete/Photo/${Iid}" method="post">
            <input type="submit" value="Delete">
        </form>
    </security:authorize>
</div>
    <hr>
</c:forEach>

</div>
<h2>Comments</h2><hr>

<c:if test="${comments.size() == 0}">
    <p>No comments yet</p>
    <hr>
</c:if>
<c:forEach items="${comments}" var="comment">
<div>
    <h3>Comment on Photo id:"${comment.pid}":</h3>
    <p>${comment.comment}</p>
    <p>Commented on ${comment.created_at}</p>
    <a href="/showDetail/${comment.pid}">View Photo</a>
    <security:authorize access="hasRole('ROLE_ADMIN')">
        <form action="/delete/Comment/${comment.id}" method="post">
            <input type="submit" value="Delete">
        </form>
    </security:authorize>
</div>
    <hr>
</c:forEach>


</body>
</html>
