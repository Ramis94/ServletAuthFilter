<%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 28.10.16
  Time: 18:05
  To change this template use File | Settings | File Templates.

  р50

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
</head>
<body>
    <h1>Личный кабинет</h1>

    ${sessionScope.message}

    <form action="/user/update" method="POST">
        Email: <input type="email" name="email" value="${email}"/>
        <br>
        Имя: <input type="text" name="login" value="${login}"/>
        <br>
        <p>Для смены пароля, введите новый пароль</p>
        Пароль: <input type="password" name="password" />
        <br>
        Повторите пароль: <input type="password" name="confirm_password" />
        <br>
        <input type="submit" value="Обновить" />
    </form>

    <a href="/logout">Выйти</a>
</body>
</html>
