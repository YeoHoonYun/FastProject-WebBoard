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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-18
 * Github : https://github.com/YeoHoonYun
 */
@WebServlet(name = "LoginBoardController", urlPatterns = "/login")
public class LoginBoardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("inputEmail");
        String passwd = req.getParameter("inputPassword");
        System.out.println(email);

        UserService userService = UserServiceImpl.getInstance();
        User user = userService.selectUser(email);

        if(user != null && user.getPasswd() != null){
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            boolean matches = passwordEncoder.matches(passwd,user.getPasswd());

            if(matches){
                System.out.println("매칭됫다.");
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("userInfo", user);
                resp.sendRedirect("/board/main");
            }
            else {
                System.out.println("매칭안된다.");
                resp.sendRedirect("/login");
            }
        }
        else {
            System.out.println("우짜냐??");
            resp.sendRedirect("/login");
        }
    }
}
