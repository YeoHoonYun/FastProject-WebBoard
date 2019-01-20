package yun.fast.webproject.board.Controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import yun.fast.webproject.board.Service.BoardService;
import yun.fast.webproject.board.Service.BoardServiceImpl;
import yun.fast.webproject.board.DTO.User;

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
@WebServlet(name = "WriteBoardController", urlPatterns = "/board/write")
public class WriteBoardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/write.jsp");
        requestDispatcher.forward(req,resp);
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
        System.out.println(fileName);
        String title = multi.getParameter("subject");
        String content = multi.getParameter("content");
        BoardService boardService = new BoardServiceImpl();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");

        boardService.insertBoard(title,user.getId(), user.getNickname() ,content,fileName);
        resp.sendRedirect("/board/main");
    }
}
