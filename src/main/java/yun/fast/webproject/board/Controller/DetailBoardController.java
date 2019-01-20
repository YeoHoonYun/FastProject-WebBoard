package yun.fast.webproject.board.Controller;

import yun.fast.webproject.board.DTO.Board;
import yun.fast.webproject.board.Service.BoardService;
import yun.fast.webproject.board.Service.BoardServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-18
 * Github : https://github.com/YeoHoonYun
 */
@WebServlet(name = "DetailBoardController", urlPatterns = "/board/detail")
public class DetailBoardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("detail_get");
        BoardService boardService = new BoardServiceImpl();
        Board board = boardService.selectOneBoard(Long.parseLong(req.getParameter("id")));

        Long pre = boardService.pre(Long.parseLong(req.getParameter("id")));
        Long before = boardService.before(Long.parseLong(req.getParameter("id")));

        if(pre == null){
            pre = Long.parseLong(req.getParameter("id"));
        }else if(before == null){
            before = Long.parseLong(req.getParameter("id"));
        }

        req.setAttribute("pre", pre);
        req.setAttribute("before", before);

        req.setAttribute("board", board);
        RequestDispatcher dispatcherServlet = req.getRequestDispatcher("/WEB-INF/views/detail.jsp");
        dispatcherServlet.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
