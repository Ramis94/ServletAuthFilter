<%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 24.10.16
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Авторизация</title>
  </head>
  <body>

  ${errorMessage}<br>
  <form action="${pageContext.request.contextPath}/auth" method="POST">
    Email: <input type="email" name="email">
    <br/>
    Пароль: <input type="password" name="password" />
    <input type="submit" value="Login" />
  </form>

  <a href="/reg">Регистрация</a>

  </body>
</html>
