package com.study.controller;

import com.study.connection.ConnectionDB;
import com.study.dto.BoardDTO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/boardController")
@Slf4j
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BoardController() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * [Controller 부분]
         */
        Connection conn = null; //DB에 연결된 객체를 저장
        PreparedStatement pstmt = null; //Connection객체에 실행문을 던지는 역할(창구)
        ResultSet rs = null; //select 결과 값을 받아옴
        String qry = "";

        try {
            conn = ConnectionDB.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<BoardDTO> list = new ArrayList<>();

        /**
         * [MODEL 부분]
         */
        try {
            qry = "select " +
                    "c.name as categoryName, b.title, b.reg_name, b.views, b.reg_dt, b.mod_dt " +
                    "from board b " +
                    "inner join category c on c.id = b.category " +
                    "order by b.reg_dt desc";

            pstmt = conn.prepareStatement(qry);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                log.info(rs.getString("reg_name"));
                //BoardDTO 생성자를 이용하여 값을 입력
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setCategoryName(rs.getString("categoryName"));
                boardDTO.setTitle(rs.getString("title"));
                boardDTO.setRegName("regName");

                list.add(boardDTO);
            }
            log.info(String.valueOf(list.size()));

            /**
             * [View부분] - 나중에 웹이 읽을 수 있도록 만들어야하기때문에 자바파일이 아닌 jsp로 만드는 것이다.
             * 지금은 웹이 읽을 수 있도록 자바를 웹이 읽을 수 있도록 해준것이다.
             * 이 부분에 직접 화면 출력되는 부분을 넣어본다.
             * 여기에 있는 부분이 향후 view로 분리될 것이다.
             * 뷰를 호출하지 않는다.
             * 단지, view 부분을 표현하는 부분이다.
             */
            resp.setContentType("text/html");
            req.setCharacterEncoding("utf8");
            resp.setCharacterEncoding("utf8");
            PrintWriter out = resp.getWriter();
            out.println("<html><head></head></html>");
            out.println("<h1>Hello, world, Model2, Servlet Controller </h1> <p>this is sample servlet.</p>");

            for (BoardDTO boardDTO : list) {
                out.println(boardDTO.getCategoryName());
                out.println(boardDTO.getTitle());
            }

            out.println("</body></html>");

        } catch (Exception e) {
            log.info("Error =>" + e);
        } finally {
            /* Close */
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {

            }
        }
        log.info("Hi Servlet");
    }

    /**
     * request가 get or post 어떤 방식이든
     * 한군데서만 작업할 수 있게 doPost()로 받은 값을 doGet으로 전달하고
     * doGet에서만 로직을 구현하도록 합니다.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
