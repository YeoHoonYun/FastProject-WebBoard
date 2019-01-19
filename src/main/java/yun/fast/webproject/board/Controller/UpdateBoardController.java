package yun.fast.webproject.board.Controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import yun.fast.webproject.board.DTO.Board;
import yun.fast.webproject.board.Service.BoardService;
import yun.fast.webproject.board.Service.BoardServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-18
 * Github : https://github.com/YeoHoonYun
 */
@WebServlet(name = "UpdateBoardController", urlPatterns = "/board/update")
public class UpdateBoardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("update_get");
        BoardService boardService = new BoardServiceImpl();
        Board board = boardService.selectOneBoard(Long.parseLong(req.getParameter("id")));

        req.setAttribute("board", board);
        RequestDispatcher dispatcherServlet = req.getRequestDispatcher("/WEB-INF/views/update.jsp");
        dispatcherServlet.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext(); //어플리케이션에 대한 정보를 ServletContext 객체가 갖게 됨.
        String saveDir = context.getRealPath("file"); //절대경로를 가져옴
        int maxsize = 3 * 1024 * 1024; // 3MB
        String encoding = "UTF-8";
        MultipartRequest multi = new MultipartRequest(req, saveDir, maxsize, encoding, new DefaultFileRenamePolicy());
        String fileName = null;
        //파일 업로드
        try {
            Enumeration files = multi.getFileNames();
            String file = (String) files.nextElement();
            fileName = multi.getFilesystemName(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        Long id = Long.valueOf(multi.getParameter("boardId"));
        String title = multi.getParameter("subject");
        String content = multi.getParameter("content");
        BoardService boardService = new BoardServiceImpl();
        boardService.updateBoard(id, title, content,fileName);
        resp.sendRedirect("/board/main");
    }
}
