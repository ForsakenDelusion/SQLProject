package org.project.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.project.dao.UserMapper;
import org.project.entity.Admin;
import org.project.entity.Fixer;
import org.project.entity.User;
import org.project.service.UserService;
import org.project.service.impl.UserServiceImpl;
import org.project.utils.MybatisUtil;

import java.io.IOException;

@WebFilter("/*")
public class BUserFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入B过滤器");
        String url = req.getRequestURI();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Fixer fixer = (Fixer) session.getAttribute("fixer");

        UserService service = new UserServiceImpl();
        boolean isBan = !service.userBanUrl(req);//调用UserService接口里的判断网页是不是禁止用户访问的方法

        if((fixer!=null || user!=null ) && isBan){
            System.out.println("我是status重定向");
            res.sendRedirect("index");
            return;
        }

        chain.doFilter(req, res);
    }
}
