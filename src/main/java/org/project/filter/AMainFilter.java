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
            System.out.println("进入A过滤器");
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            Admin admin = (Admin) session.getAttribute("admin");
            Fixer fixer = (Fixer) session.getAttribute("fixer");
            System.out.println("我是user"+user);
            System.out.println("我是admin"+admin);
            System.out.println("我是fixer"+fixer);
            if (user == null && admin == null && fixer == null ){//如果用户没登陆，就重定向
                System.out.println("我是登录检测重定向");
                res.sendRedirect("/project/login");
                return;
            }
        }
        chain.doFilter(req, res);
    }
}
