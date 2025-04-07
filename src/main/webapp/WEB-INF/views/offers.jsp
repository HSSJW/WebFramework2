<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--이 라이브러리의 이름이 c로 사용가능 --%>

<%--
  Created by IntelliJ IDEA.
  User: nykim
  Date: 2025-03-14
  Time: 오후 5:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach var="offer" items="${key_offers}" >
        <p> <c:out value="${offer}"> </c:out> </p>
    </c:forEach>
</body>
</html>
