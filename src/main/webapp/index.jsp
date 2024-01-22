<%@ page import="com.study.connection.ConnectionDB" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <title>JSP - Hello World</title>
</head>
<body style= "background-color : lightblue;">
<h1><%= "Connection Test - index.jsp" %></h1>

<a href="hello-servlet">Hello Servlet</a>

<%

    ConnectionDB t = new ConnectionDB();
    out.println(t.getConnection());

%>

</body>
</html>
