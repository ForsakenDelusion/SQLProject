package org.project.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.project.entity.Admin;
import org.project.entity.Fixer;
import org.project.entity.User;

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

        if((fixer!=null || user!=null ) && url.endsWith("project/status")){
            System.out.println("我是status重定向");
            res.sendRedirect("index");
            return;
        }

        chain.doFilter(req, res);
    }
}
