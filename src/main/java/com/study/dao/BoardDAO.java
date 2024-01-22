package com.study.dao;

import com.study.connection.ConnectionDB;
import com.study.dto.BoardDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    private static BoardDAO dao;

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private BoardDAO() {
    }

    public synchronized static BoardDAO getDao() {
        if (dao == null) {
            dao = new BoardDAO();
        }
        return dao;
    }

    /**
     * 게시물 전체 조회
     */
    public List<BoardDTO> getList() {
        List<BoardDTO> arrBoard = new ArrayList<>();

        String sql;
        sql = "select " +
                "c.name as categoryName, b.title, b.reg_name, b.views, b.reg_dt, b.mod_dt " +
                "from board b " +
                "inner join category c on c.id = b.category " +
                "order by b.reg_dt desc";

        try {
            conn = ConnectionDB.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BoardDTO dto = new BoardDTO();
                dto.setCategoryName(rs.getString("categoryName"));
                dto.setTitle(rs.getString("title"));
                dto.setRegName(rs.getString("reg_name"));
                dto.setViews(rs.getInt("views"));
                dto.setRegDt(rs.getString("reg_dt"));
                dto.setModDt(rs.getString("mod_dt"));
                arrBoard.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrBoard;
    }

}
