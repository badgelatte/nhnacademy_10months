<%--
  Created by IntelliJ IDEA.
  User: nhnacademy
  Date: 24. 1. 10.
  Time: 오전 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>

<table>
    <tbody>
    <tr>
        <th>status_code</th>
        <td><!-- todo status_code 출력 -->
            ${status_code}
        </td>
    </tr>
    <tr>
        <th>exception_type</th>
        <td><!-- todo exception_type 출력 -->
            ${exception_type}
        </td>
    </tr>
    <tr>
        <th>message</th>
        <td><!-- todo message 출력 -->
            ${message}
        </td>
    </tr>
    <tr>
        <th>exception</th>
        <td><!-- todo exception 출력 -->
            ${exception}
        </td>
    </tr>
    <tr>
        <th>request_uri</th>
        <td><!-- todo request_uri 출력 -->
            ${request_uri}
        </td>
    </tr>
    </tbody>

</table>
</body>
</html>