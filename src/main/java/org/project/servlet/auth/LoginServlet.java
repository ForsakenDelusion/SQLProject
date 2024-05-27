package org.project.servlet.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
            resp.sendRedirect("index");
        } else {
            req.getSession().setAttribute("login-failure", 1);
            this.doGet(req, resp);
        }
    }
}
