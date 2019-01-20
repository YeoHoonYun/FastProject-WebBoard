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
        Long count_num = boardService.selectView();

        req.setAttribute("max",BoardServiceImpl.getMaxNum());
        req.setAttribute("count",count_num);
        req.setAttribute("p",p);

        int fromNum  = BoardServiceImpl.getMaxNum()*(p-1)+1;
        int toNum = BoardServiceImpl.getMaxNum()*(p-1)+BoardServiceImpl.getMaxNum();

        req.setAttribute("from",fromNum);
        req.setAttribute("to",toNum);

        if(toNum > count_num){
            req.setAttribute("to","");
        }else if(fromNum > count_num){
            req.setAttribute("from", "");
        }
        req.setAttribute("list", list);
        RequestDispatcher dispatcherServlet = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
        dispatcherServlet.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int p = 1;
        String word = "%%";
        if(req.getParameter("p") != null){
            p = Integer.parseInt(req.getParameter("p"));
            if(Integer.parseInt(req.getParameter("p")) <= 0){
                p = 1;
            }
        }
        if(req.getParameter("search") != null){
            word = "%" + req.getParameter("search")+"%";
        }
        System.out.println(word);
        System.out.println("post");
        BoardService boardService = new BoardServiceImpl();
        List<Board> list = boardService.selectLists(p, word);

        req.setAttribute("p",p);
        req.setAttribute("list", list);
        RequestDispatcher dispatcherServlet = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
        dispatcherServlet.forward(req,resp);
    }
}
