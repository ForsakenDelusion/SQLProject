package org.project.servlet.admin.page;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.service.AdminService;
import org.project.service.impl.AdminServiceImpl;
import org.project.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/adminlogin")
public class AdminLoginServlet extends HttpServlet {
    AdminService service;

    @Override
    public void init() throws ServletException {
        service = new AdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            String Aid = null;
            String Apassword = null;
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("Aid")) Aid = cookie.getValue();
                if(cookie.getName().equals("Apassword")) Apassword = cookie.getValue();
            }
            if(Aid != null && Apassword != null){
                if (service.auth(Aid,Apassword, req.getSession())){
                    resp.sendRedirect("index");
                    return;
                };
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
        ThymeleafUtil.process("adminlogin.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Aid = req.getParameter("Aid");
        String Apassword = req.getParameter("Apassword");
        String remeberme = req.getParameter("remember-me");
        
        if(service.auth(Aid,Apassword, req.getSession())){
            if (remeberme != null) {
                Cookie cookie_Aid = new Cookie("Aid", Aid);
                cookie_Aid.setMaxAge(60 * 60 * 24 * 7);
                Cookie cookie_Apassword = new Cookie("Apassword", Apassword);
                cookie_Apassword.setMaxAge(60 * 60 * 24 * 7);
                resp.addCookie(cookie_Aid);
                resp.addCookie(cookie_Apassword);
            }
            resp.sendRedirect("index");
        } else {
            req.getSession().setAttribute("login-failure", 1);
            this.doGet(req, resp);
        }
    }
}
