package com.study.service;

import com.study.connection.ConnectionDB;
import com.study.dto.BoardDTO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BoardService {

    List<BoardDTO> list = new ArrayList<>();

    public List<BoardDTO> doSelect() throws Exception {
        Connection conn = null; //DB에 연결된 객체를 저장
        PreparedStatement pstmt = null; //Connection객체에 실행문을 던지는 역할(창구)
        ResultSet rs = null; //select 결과 값을 받아옴
        String qry = "";
        conn = ConnectionDB.getConnection();

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

            log.info("MODEL");

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
        return list;
    }
}
