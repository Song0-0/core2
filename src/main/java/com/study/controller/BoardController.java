package com.study.controller;

import com.study.connection.ConnectionDB;
import com.study.dto.BoardDTO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
                boardDTO.setRegName(rs.getString("reg_name"));
                boardDTO.setViews(rs.getInt("views"));
                boardDTO.setRegDt(rs.getString("reg_dt"));
                boardDTO.setModDt(rs.getString("mod_dt"));

                list.add(boardDTO);
            }
            log.info(String.valueOf(list.size()));

            //list의 값을 attribute를 통해 주고 받는다.
            req.setAttribute("board", list);

            //forward 방식
            //List의 값을 Attribute를 통해 전달한다.
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
            dispatcher.forward(req, resp);

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
