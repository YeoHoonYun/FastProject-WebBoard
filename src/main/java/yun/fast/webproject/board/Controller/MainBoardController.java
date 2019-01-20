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
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-18
 * Github : https://github.com/YeoHoonYun
 */
@WebServlet(name = "MainBoardController", urlPatterns = "/board/main")
public class MainBoardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int p = 1;
        if(req.getParameter("p") != null){
            p = Integer.parseInt(req.getParameter("p"));
            if(Integer.parseInt(req.getParameter("p")) <= 0){
                p = 1;
            }
        }
        System.out.println("main_get");
        BoardService boardService = new BoardServiceImpl();
        List<Board> list = boardService.selectLists(p);

        req.setAttribute("p",p);
        req.setAttribute("list", list);
        RequestDispatcher dispatcherServlet = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
        dispatcherServlet.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcherServlet = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
        dispatcherServlet.forward(req,resp);
    }
}
