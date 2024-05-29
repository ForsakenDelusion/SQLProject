package org.project.servlet.user.action;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.entity.User;
import org.project.service.UserService;
import org.project.service.impl.UserServiceImpl;
import org.project.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/add-borrow")
public class AddBorrowServlet extends HttpServlet {

    UserService service;
    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String whoami;
        User user = (User) req.getSession().getAttribute("user");
        whoami = "用户";
        context.setVariable("whoami", whoami);
        context.setVariable("name", user.getUname());
        context.setVariable("device_name_list", service.getIdleDeviceName());
        ThymeleafUtil.process("add-borrow.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Dname = req.getParameter("device_name");
        User user = (User) req.getSession().getAttribute("user");
        String Uid = String.valueOf(user.getUid());
        String Did = service.getOldestDeviceByName(Dname);
        service.borrowDevice(Uid,Did);
        resp.sendRedirect("borrowreturn");

    }
}
