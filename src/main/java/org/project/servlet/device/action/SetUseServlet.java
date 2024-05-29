package org.project.servlet.device.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.service.AdminService;
import org.project.service.impl.AdminServiceImpl;

import java.io.IOException;

@WebServlet("/setuse")
public class SetUseServlet extends HttpServlet {
    AdminService service;

    @Override
    public void init() throws ServletException {
        service = new AdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Did = req.getParameter("Did");//在前端用Thymeleaf获取到的Did值
        service.setDeviceUse(Did);
        resp.sendRedirect("status");
    }
}
