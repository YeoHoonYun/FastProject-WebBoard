package yun.fast.webproject.board.Controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import yun.fast.webproject.board.DTO.Board;
import yun.fast.webproject.board.DTO.User;
import yun.fast.webproject.board.Service.BoardService;
import yun.fast.webproject.board.Service.BoardServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-18
 * Github : https://github.com/YeoHoonYun
 */
@WebServlet(name = "ReWriteBoardController", urlPatterns = "/board/rewrite")
public class ReWriteBoardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("rewrite_get");
        BoardService boardService = new BoardServiceImpl();
        Board board = boardService.selectOneBoard(Long.parseLong(req.getParameter("id")));

        req.setAttribute("board", board);
        RequestDispatcher dispatcherServlet = req.getRequestDispatcher("/WEB-INF/views/rewrite.jsp");
        dispatcherServlet.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("subject");
        String content = req.getParameter("content");
        Long groupno = Long.valueOf(req.getParameter("groupno"));;
        int grpord = Integer.parseInt(req.getParameter("grpord"));;
        int depth = Integer.parseInt(req.getParameter("depth"));;
        BoardService boardService = new BoardServiceImpl();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");

        boardService.updateGrp(groupno, grpord);
        boardService.createGrp(title, user.getId(), user.getNickname() ,content, groupno, grpord, depth);

        resp.sendRedirect("/board/main");
    }
}
