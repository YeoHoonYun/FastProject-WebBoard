package yun.fast.webproject.board.filter;

import yun.fast.webproject.board.DTO.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName="SecurityFilter", urlPatterns = {"/board/delete", "/board/write", "/board/rewrite"})
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("요청이 올때");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        User logininfo = (User)session.getAttribute("userInfo");
        if(logininfo == null){
            response.sendRedirect("/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("서블릿이 실행된 이후");
    }

    @Override
    public void destroy() {

    }
}
