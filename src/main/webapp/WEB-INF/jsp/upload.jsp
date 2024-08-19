<%--
  Created by IntelliJ IDEA.
  User: Chocobo
  Date: 21/4/2023
  Time: 6:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>

</head>

<body>
<%--back to home--%>
<br>
<a href="/">Back to Home</a>
<br>
<div style="text-align: center;">
    <form action="/upload" method="post" enctype="multipart/form-data">

        <label for="description">description:</label><br>
        <input type="text" id="description" name="description" value="description"><br>
        <label for="image">file:</label><br>
        <input type="file" id="image" name="image" value="image"><br>
        <input class="btn btn-primary" type="submit" value="submit">

    </form>
</div>


</body>
</html>
