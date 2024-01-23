<%@ page import="com.study.dto.BoardDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    List<BoardDTO> list2 = (ArrayList<BoardDTO>) request.getAttribute("board");
%>

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
                    for (BoardDTO boardDTO : list2) {
                %>
                <tr>
                    <td><%= boardDTO.getCategoryName()%></td>
                    <td><a href="view.jsp?id = <%= boardDTO.getId()%>">
                        <%= boardDTO.getTitle() %></a></td>
                    <td><%= boardDTO.getRegName() %></td>
                    <td><%= boardDTO.getViews() %></td>
                    <td><%= boardDTO.getRegDt() %></td>
                    <td><%= boardDTO.getModDt() %></td>
                </tr>
                <%
                    }
                %>
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
