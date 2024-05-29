package org.project.servlet.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.entity.Admin;
import org.project.entity.Fixer;
import org.project.entity.User;
import org.project.service.IndexService;
import org.project.service.UserService;
import org.project.service.impl.IndexServiceImpl;
import org.project.service.impl.UserServiceImpl;
import org.project.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    IndexService indexService;
    UserService userService;
    @Override
    public void init() throws ServletException {
        indexService = new IndexServiceImpl();
        userService = new UserServiceImpl();
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

        context.setVariable("all_device", indexService.getDeviceCount());
        context.setVariable("all_user", userService.getUserCount());
        context.setVariable("using_device", indexService.getUsingDeviceCount());
        context.setVariable("maintain_device", indexService.getMaintainDeviceCount());
        context.setVariable("newest_device_list", indexService.getNewestDeviceList());
        ThymeleafUtil.process("index.html",context,resp.getWriter());
    }
}
