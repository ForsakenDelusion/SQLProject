package org.project.servlet.user.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.service.UserService;
import org.project.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/returndevice")
public class ReturnDeviceServlet extends HttpServlet {

    UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Did = req.getParameter("Did");//在前端用Thymeleaf获取到的Did值
        service.returnDevice(Did);
        resp.sendRedirect("borrowreturn");
    }

}
