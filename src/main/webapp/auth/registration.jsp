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
    <title>$Title$</title>
  </head>
  <body>

  ${errorMessage}

  <form action="/reg" method="POST">
    Email: <input type="email" name="email">
    <br>
    Пароль: <input type="password" name="password" />
    <br>
    Повторите пароль: <input type="password" name="confirm_password" />
    <br>
    Имя: <input type="text" name="login" />
    <br>
    <input type="submit" value="Submit" />
  </form>
  <a href="/auth">Авторизация</a>

  </body>
</html>
