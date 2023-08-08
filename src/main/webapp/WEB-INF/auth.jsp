<%--
  Created by IntelliJ IDEA.
  User: ponom
  Date: 08.08.2023
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Authentication</title>
</head>
<body>
<h2>Login</h2>
<form action="auth" method="post">
    <label for="username">Username: </label>
    <input type="text" id="username" name="username" required><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
