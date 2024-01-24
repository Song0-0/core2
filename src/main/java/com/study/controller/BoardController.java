package com.study.controller;

import com.study.dto.BoardDTO;
import com.study.service.BoardService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        List<BoardDTO> list = new ArrayList<>();

        BoardService boardService = new BoardService();

        try {
            list = boardService.doSelect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //list의 값을 attribute를 통해 주고 받는다.
        req.setAttribute("board", list);

        //forward 방식
        //List의 값을 Attribute를 통해 전달한다.
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
        dispatcher.forward(req, resp);

        log.info("CONTROLLER");

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
