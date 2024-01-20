<%@ page import="com.study.connection.ConnectionDB" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Connection Test - index.jsp" %></h1>

<a href="hello-servlet">Hello Servlet</a>

<%

    ConnectionDB t = new ConnectionDB();
    out.println(t.getConnection());

%>

</body>
</html>
