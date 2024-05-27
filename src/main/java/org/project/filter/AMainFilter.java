package org.project.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import org.project.entity.Admin;
import org.project.entity.Fixer;
import org.project.entity.User;

import java.io.IOException;

@WebFilter("/*")
public class AMainFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String url = req.getRequestURI();
        if (!url.contains("static/") && !url.endsWith("project/login") && !url.endsWith("project/adminlogin") && !url.endsWith("project/fixerlogin")){//允许静态资源和登录网页的加载

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            Admin admin = (Admin) session.getAttribute("admin");
            Fixer fixer = (Fixer) session.getAttribute("fixer");
            System.out.println(user);
            System.out.println(admin);
            if (user == null && admin == null && fixer == null ){//如果用户没登陆，就重定向
                System.out.println("我是重定向");
                res.sendRedirect("/project/login");
                return;
            }
        }
        chain.doFilter(req, res);
    }
}
