package com.study.controller;

import com.study.connection.ConnectionDB;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/boardController")
@Slf4j
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BoardController() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "";

        try {
            conn = ConnectionDB.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


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
            }
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
