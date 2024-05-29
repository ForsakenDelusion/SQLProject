package org.project.servlet.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            Cookie cookie_Uid = new Cookie("Uid", "Uid");
            cookie_Uid.setMaxAge(0);
            Cookie cookie_Upassword = new Cookie("Upassword", "Upassword");
            cookie_Upassword.setMaxAge(0);
            resp.addCookie(cookie_Uid);
            resp.addCookie(cookie_Upassword);
            req.getSession().removeAttribute("user");
        } else if (req.getSession().getAttribute("admin") != null) {
            Cookie cookie_Aid = new Cookie("Aid", "Aid");
            cookie_Aid.setMaxAge(0);
            Cookie cookie_Apassword = new Cookie("Apassword", "Apassword");
            cookie_Apassword.setMaxAge(0);
            resp.addCookie(cookie_Aid);
            resp.addCookie(cookie_Apassword);
            req.getSession().removeAttribute("admin");
        } else  {
            Cookie cookie_Fid = new Cookie("Fid", "Fid");
            cookie_Fid.setMaxAge(60 * 60 * 24 * 7);
            Cookie cookie_Fpassword = new Cookie("Fpassword", "Fpassword");
            cookie_Fpassword.setMaxAge(60 * 60 * 24 * 7);
            resp.addCookie(cookie_Fid);
            resp.addCookie(cookie_Fpassword);
            req.getSession().removeAttribute("fixer");
        }



        resp.sendRedirect("login");
    }
}
