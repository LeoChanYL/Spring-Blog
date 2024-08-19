<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>

<head>
  <title>Please Log In</title>
  
</head>
<body>

<br>
<a href="/">Back to Home</a>
<br>
<div style="text-align: center;">
<h1>Please Log In</h1>

<c:if test="${param.error != null}">
  <div>Invalid username and password.</div>
</c:if>
<c:if test="${param.logout != null}">
  <div>You have been logged out.</div>
</c:if>

<form th:action="@{/login}" method="post">
  <div>
    <input type="text" name="username" placeholder="Username"/>
  </div>
  <div>

    <input type="password" name="password" placeholder="Password"/>
  </div>
  <input type="submit" value="Log in" />
</form>
  <div style="text-align: center;">
</body>
</html>
