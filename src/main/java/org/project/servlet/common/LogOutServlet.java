package org.project.servlet.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            req.getSession().removeAttribute("user");
        } else if (req.getSession().getAttribute("admin") != null) {
            req.getSession().removeAttribute("admin");
        } req.getSession().removeAttribute("fixer");


        resp.sendRedirect("login");
    }
}
