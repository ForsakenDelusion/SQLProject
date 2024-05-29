package org.project.servlet.admin.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.service.AdminService;
import org.project.service.impl.AdminServiceImpl;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/changefixer")
public class ChangeFixerServlet extends HttpServlet {

    AdminService service;
    @Override
    public void init() throws ServletException {
        service = new AdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Did = req.getParameter("Did");
        String Fname = req.getParameter("Fname");
        service.changefixer(Did,Fname);
        resp.sendRedirect("devicemaintain");
    }

}
