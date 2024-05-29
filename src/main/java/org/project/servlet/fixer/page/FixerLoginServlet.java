package org.project.servlet.fixer.page;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.service.FixerService;
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
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            String Fid = null;
            String Fpassword = null;
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("Fid")) Fid = cookie.getValue();
                if(cookie.getName().equals("Fpassword")) Fpassword = cookie.getValue();
            }
            if(Fid != null && Fpassword != null){
                if (service.auth(Fid,Fpassword, req.getSession())){
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
            if(remeberme != null) {
                Cookie cookie_Fid = new Cookie("Fid", Fid);
                cookie_Fid.setMaxAge(60 * 60 * 24 * 7);
                Cookie cookie_Fpassword = new Cookie("Fpassword", Fpassword);
                cookie_Fpassword.setMaxAge(60 * 60 * 24 * 7);
                resp.addCookie(cookie_Fid);
                resp.addCookie(cookie_Fpassword);
            }
            resp.sendRedirect("index");
        } else {
            req.getSession().setAttribute("login-failure", 1);
            this.doGet(req, resp);
        }
    }
}
