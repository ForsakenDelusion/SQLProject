package org.project.servlet.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.service.UserService;
import org.project.service.impl.UserServiceImpl;
import org.project.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService service;
    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            String Uid = null;
            String Upassword = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Aid")) resp.sendRedirect("adminlogin");
                if (cookie.getName().equals("Fid")) resp.sendRedirect("fixerlogin");
                if(cookie.getName().equals("Uid")) Uid = cookie.getValue();
                if(cookie.getName().equals("Upassword")) Upassword = cookie.getValue();
            }
            if(Uid != null && Upassword != null){
                if (service.auth(Uid,Upassword, req.getSession())){
                    resp.sendRedirect("index");
                    return;
                }
            }
        }

        Context context = new Context();
        if(req.getSession().getAttribute("login-failure") != null) {
            context.setVariable("failure", true);
            req.getSession().removeAttribute("failure");
        }
        if(req.getSession().getAttribute("user") != null || req.getSession().getAttribute("admin") != null) {
            resp.sendRedirect("index");
            return;
        }
        ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Uid = req.getParameter("Uid");
        String Upassword = req.getParameter("Upassword");
        String remeberme = req.getParameter("remember-me");

        if(service.auth(Uid,Upassword, req.getSession())){
            if (remeberme != null) {
                Cookie cookie_Uid = new Cookie("Uid", Uid);
                cookie_Uid.setMaxAge(60 * 60 * 24 * 7);
                Cookie cookie_Upassword = new Cookie("Upassword", Upassword);
                cookie_Upassword.setMaxAge(60 * 60 * 24 * 7);
                resp.addCookie(cookie_Uid);
                resp.addCookie(cookie_Upassword);
            }
            resp.sendRedirect("index");
        } else {
            req.getSession().setAttribute("login-failure", 1);
            this.doGet(req, resp);
        }
    }
}
