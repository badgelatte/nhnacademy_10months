<%--
  Created by IntelliJ IDEA.
  User: nhnacademy
  Date: 24. 1. 9.
  Time: 오후 1:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>register</title>
    <link rel="stylesheet" href="/style.css" />
    <meta charset="UTF-8" />
    <script>
        var value = document.querySelector('input[name="gender"]:checked').value;
    </script>
</head>
<body>
    <!-- todo action 주소 설정
                //등록
                    action = /student/register
                //수정
                    action = /student/update
            -->
    <c:choose>
        <c:when test="${empty student}">
            <c:url value="/student/register" var="action"/>
        </c:when>
        <c:otherwise>
            <c:url value="/student/update?id=${student.id}" var="action"/>
        </c:otherwise>
    </c:choose>
<form method="post" action="${action}">
<table border="1" width="100%" >
    <tr>
        <th>ID</th>
        <td><input type="text" name="id" id="id" value="${student.id}"></td>
    </tr>
    <tr>
        <th>이름</th>
        <td><input type="text" name="name" id="name" value="${student.name}"></td>
    </tr>
    <tr>
        <th>성별</th>
        <td >
            <input type="radio" name="gender" id="gender_man" value="남" checked>
            <label for="gender_man">남</label>
            <input type="radio" name="gender" id="gender_woman" value="여">
            <label for="gender_woman">여</label>
        </td>
    </tr>
    <tr>
        <th>나이</th>
        <td>
            <input type="text" name="age" id="age" value="${student.age}">
        </td>
    </tr>
</table><br>

    <p>
<%--        <button type="submit">--%>
            <c:choose>
                <c:when test="${empty student}">
                    <input type="submit" value="등록">
                </c:when>
                <c:otherwise>
                    <input type="submit" value="수정">
                </c:otherwise>
            </c:choose>
<%--        </button>--%>
    </p>
</form>
</body>
</html>
