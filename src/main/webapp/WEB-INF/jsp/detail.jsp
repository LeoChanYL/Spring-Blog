<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!-- showDetail.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Image Detail</title>

</head>
<body>

<br>
<a href="/">Back to Home</a>
<br>
<%--image--%>
<div style="text-align: center;">
<h1>Image Description: ${img.Name}</h1>
<h2>Uploaded By: <a href="/profile/${img.USER_ID}"> ${img.USER_ID}</a></h2>
<h3>Created At: ${img.Created_AT}</h3>

<img src="data:image/png;base64,${img.IMAGE}" width="50%" height="auto">

<security:authorize access="hasRole('ROLE_ADMIN')">
    <form action="/delete/Photo/${Iid}" method="post">
        <input type="submit" value="Delete">
    </form>
</security:authorize>

</div>
<security:authorize access="hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')">
    <h2>Add Comment</h2>
<%--    id= url--%>

    <form action="/Comment/${Iid}" method="post">
        <input type="text" name="comment">

        <input type="submit" value="Add">
    </form>
</security:authorize>



<%--comments--%>

<h2>Comments</h2>
<c:set var="commentList" value="${Comments}" />
<c:forEach var="comment" items="${commentList}">
    <hr>
    <p><a href="/profile/${comment.UID}">${comment.UID} </a>- ${comment.Created_AT}</p>

    <p>${comment.Comment}</p>
    <security:authorize access="hasRole('ROLE_ADMIN')">
        <form action="/delete/Comment/${comment.id}" method="post">
            <input type="submit" value="Delete">
        </form>
    </security:authorize>
</c:forEach>
</body>
</html>
