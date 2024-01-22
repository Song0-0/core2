<%@ page import="com.study.dto.BoardDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.study.dao.BoardDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width" , initial-scale="1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <title>MVC2 - Board</title>
</head>

<body style="background-color: lightblue;">
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>자유 게시판 - 목록</h1>
            <hr>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>카테고리</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>등록 일시</th>
                    <th>수정 일시</th>
                </tr>
                </thead>

                <tbody>
                <%
                    BoardDAO boardDAO = BoardDAO.getDao();
                    List<BoardDTO> list = boardDAO.getList();

                for(int i=0; i<list.size(); i++){
                %>
                <tr>
                    <td><%= list.get(i).getCategoryName()%></td>
                    <td> <a href = "view.jsp?id = <%= list.get(i).getId()%>">
                        <%= list.get(i).getTitle() %> </a> </td>
                    <td><%= list.get(i).getRegName() %></td>
                    <td><%= list.get(i).getViews() %></td>
                    <td><%= list.get(i).getRegDt() %></td>
                    <td><%= list.get(i).getModDt() %></td>
                </tr>
                <% } %>
                </tbody>
            </table>

            <div class="text-right">
                <a href="write.jsp" class="btn btn-primary pull-right">등록</a>
            </div>

        </div>
    </div>
</div>
</body>

</html>
