<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yuriy Kozlov
  Date: 30.07.2023
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="profileStyle.css" rel="stylesheet">
    <title>Profile</title>
</head>
<style>
    .delete-btn, .delete-btn:hover{
        position: absolute;
        top: 0;
        right: 0;
        margin: 20px;
        background-color: red;
        color: white;
        border: none;
        border-radius: 5px;
        padding: 10px 20px;
        font-size: 16px;
        cursor: pointer;
    }
    .add-operation-btn, .add-operation-btn:hover{
        position: absolute;
        bottom: 0;
        right: 0;
        margin: 20px;
        background-color: darkgreen;
        color: white;
        border: none;
        border-radius: 5px;
        padding: 10px 20px;
        font-size: 16px;
        cursor: pointer;
    }

    .operations-table{
        border-collapse: collapse;
        border: black;
        position: absolute;
    }
    .operations-table td , th{
        border: 1px solid black;
        padding: 10px;
        text-align: center;
        cursor: pointer;
        font-size: 15px;
        color: black;
    }
</style>
<body>
<header class="masthead mb-auto">
    <a class="delete-btn" href="deleteProfile?UserId=${requestScope.user.id}">Delete profile</a>
    <a class="add-operation-btn" href="addOperation.jsp?UserId=${requestScope.user.id}">Add new operation</a>
</header>
<main>
<ul style="list-style: none;">
    <li><h1> Personal data: </h1><hr></li>
    <li><p><b> Personal id: </b> ${requestScope.user.id} </p></li>
    <li><p><b> Surname: </b> ${requestScope.user.surname} </p></li>
    <li><p><b> Name: </b> ${requestScope.user.name} </p></li>
    <li><p><b> Date of birth: </b> ${requestScope.user.dateOfBirth} </p></li>
</ul>

<ul style="list-style: none;">
    <li><h2> Your operations: </h2><hr></li>
</ul>
    <table class = "operations-table">
        <thead>
        <tr>
            <th>Type</th>
            <th>Object</th>
            <th>Volume</th>
            <th>Currency</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.operations}" var = "operation">
            <c:set var="income" value="${operation.getIncome().getIncomeSource().getName()}" />
            <c:set var="expenditure" value="${operation.getExpenditure().getExpenditureItem().getName()}" />
            <tr>
                <td>
                    <c:if test="${income == null}">
                       Expenditure
                    </c:if>
                    <c:if test="${expenditure == null}">
                        Income
                    </c:if>
                </td>
                <td>
                    <c:if test="${income == null}">
                        ${expenditure}
                    </c:if>
                    <c:if test="${expenditure == null}">
                        ${income}
                    </c:if>
                </td>
                <td> ${operation.volume} </td>
                <td> ${operation.currency.name} </td>
                <td> ${operation.dateOfOperation}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>



</main>
</body>
</html>
