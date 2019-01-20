package yun.fast.webproject.board.Controller;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import yun.fast.webproject.board.DTO.User;
import yun.fast.webproject.board.Service.UserService;
import yun.fast.webproject.board.Service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-18
 * Github : https://github.com/YeoHoonYun
 */
@WebServlet(name = "RegisterBoardController", urlPatterns = "/board/register")
public class RegisterBoardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcherServlet = req.getRequestDispatcher("/WEB-INF/views/register.jsp");
        dispatcherServlet.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("inputName");
        String nickName = req.getParameter("nickName");
        String inputEmail = req.getParameter("inputEmail");
        String passwd = req.getParameter("inputPassword");

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodepasswd = passwordEncoder.encode(passwd);

        User user = new User(name, nickName,inputEmail,encodepasswd);
        UserService userService = UserServiceImpl.getInstance();
        userService.userAdd(user);
        resp.sendRedirect("/login");
    }
}
