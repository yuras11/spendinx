<%--
  Created by IntelliJ IDEA.
  User: Yuriy Kozlov
  Date: 29.07.2023
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
  <meta charset="utf-8">
  <link href="enterStyle.css" rel="stylesheet">
  <title>Вход</title>
</head>

<body background="background.jpg">
<div style="display: flex;
     justify-content: space-around;
     align-items: center">
  <h1 class="form-enter-text">Вход</h1>
  <h2 class = "form">
    <form action="enter" method="POST">
      Логин: <input name="Login" />
      <br><br>
      Пароль: <input name="Password" type="password" />
      <br><br>
      <input class = "input-button" type="submit" value="Войти"/>
    </form>
  </h2>
</div>
<footer class="not-registered">
  <div class="inner">
    <p>Еще не зарегистрированы? <a href="registration.jsp">Зарегистрироваться</a></p>
  </div>
</footer>
</body>
</html>
