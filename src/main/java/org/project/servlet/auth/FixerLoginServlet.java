package org.project.servlet.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.service.AdminService;
import org.project.service.FixerService;
import org.project.service.impl.AdminServiceImpl;
import org.project.service.impl.FixerServiceImpl;
import org.project.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/fixerlogin")
public class FixerLoginServlet extends HttpServlet {
    FixerService service;

    @Override
    public void init() throws ServletException {
        service = new FixerServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        if(req.getSession().getAttribute("login-failure") != null) {
            context.setVariable("failure", true);
            req.getSession().removeAttribute("failure");
        }
        if(req.getSession().getAttribute("fixer") != null) {
            resp.sendRedirect("index");
            return;
        }
        ThymeleafUtil.process("fixerlogin.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Fid = req.getParameter("Fid");
        String Fpassword = req.getParameter("Fpassword");
        String remeberme = req.getParameter("remember-me");
        if(service.auth(Fid,Fpassword, req.getSession())){
            resp.sendRedirect("index");
        } else {
            req.getSession().setAttribute("login-failure", 1);
            this.doGet(req, resp);
        }
    }
}
