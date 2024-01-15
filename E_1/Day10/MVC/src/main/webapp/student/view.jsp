<%--
  Created by IntelliJ IDEA.
  User: nhnacademy
  Date: 24. 1. 9.
  Time: 오후 2:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>학생-조회</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<table border="1">
    <tbody>
    <!-- todo view 구현 -->
    <tr>
        <th>아이디</th>
        <td >${student.id}</td>
    </tr>
    <tr>
        <th>이름</th>
        <td>${student.name}</td>
    </tr>
    <tr>
        <th>성별</th>
        <td>${student.gender}</td>
    </tr>
    <tr>
        <th>나이</th>
        <td>${student.age}</td>
    </tr>
    <tr>
        <th>등록일</th>
        <td>${student.createdAt}</td>
    </tr>
    </tbody>
</table>
<ul>
    <a href="/student/list">리스트</a>
    <!--  ${update_link} 설정 c:url -->
    <%--c:url은 기본으로 get 요청이 생성된다--%>
    <c:url value="/student/update?id=${student.id}" var="update_link"/>
    <a href="${update_link}">수정</a>
        <!-- 삭제버튼 구현, method=post -->
    <form method="post" name="id" action="/student/delete">
        <input type="hidden" name="id" value="${student.id}">
        <input type="submit" value="삭제">
    </form>

 </ul>

</body>
</html>