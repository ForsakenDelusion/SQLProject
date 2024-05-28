package org.project.servlet.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.entity.Admin;
import org.project.entity.Fixer;
import org.project.entity.User;
import org.project.service.NewestDeviceService;
import org.project.service.impl.NewestDeviceServiceImpl;
import org.project.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    NewestDeviceService service;

    @Override
    public void init() throws ServletException {
        service = new NewestDeviceServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String whoami;
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        User user = (User) req.getSession().getAttribute("user");
        Fixer fixer = (Fixer) req.getSession().getAttribute("fixer");

        if (admin != null) {
            whoami = "管理员";
            context.setVariable("whoami", whoami);
        } else if (user != null) {
            whoami = "用户";
            context.setVariable("whoami", whoami);
            context.setVariable("name", user.getUname());
        } else {
            whoami = "维修工";
            context.setVariable("whoami", whoami);
            context.setVariable("name", fixer.getFName());
        }

        context.setVariable("newest_device_list", service.getNewestDeviceList());
        ThymeleafUtil.process("index.html",context,resp.getWriter());
    }
}
