<%--
  Created by IntelliJ IDEA.
  User: Yuriy Kozlov
  Date: 31.07.2023
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new operation</title>
</head>
<body>
<div style="display: flex;
     justify-content: space-around;
     align-items: center">
  <h1 class="cover-heading-name">Adding new operation</h1>
  <h2 class = "form">
    <form action="addOperation" method="POST">
      Id: <input name="UserId" />
      <br><br>
      Type: <input type="radio" name ="operation" value="income" /> Income
      <input type="radio" name="operation" value="expenditure" /> Expenditure
      <br><br>
      Object: <input name="object" />
      <br><br>
      Volume: <input name="volume" />
      <br><br>
      Currency: <input name="currency" />
      <br><br>
      Date of operation: <input name="Date of operation" />
      <br><br>
      <input type="submit" value="Add"/>
    </form>
  </h2>
</div>
</body>
</html>
