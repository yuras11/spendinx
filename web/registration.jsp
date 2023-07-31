<%--
  Created by IntelliJ IDEA.
  User: Yuriy Kozlov
  Date: 29.07.2023
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="registrationStyle.css">
    <title>Registration</title>

</head>
<body background="background.jpg">
<div style="display: flex;
     justify-content: space-around;
     align-items: center">
    <h1 class="cover-heading-name">Welcome to "spendinx"!</h1>
    <h2 class = "form">
        <form action="registration" method="POST">
            Name: <input name="Name" />
            <br><br>
            Surname: <input name="Surname" />
            <br><br>
            Date of birth: <input name="Date of birth" />
            <br><br>
            Login: <input name="Login" />
            <br><br>
            Password: <input name="Password" type="password" />
            <br><br>
            <input type="submit" value="Register"/>
        </form>
    </h2>
</div>
</body>
</html>
