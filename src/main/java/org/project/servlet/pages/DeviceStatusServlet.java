package org.project.servlet.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.entity.Admin;
import org.project.entity.Fixer;
import org.project.entity.User;
import org.project.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/status")
public class DeviceStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String whoami;
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        User user = (User) req.getSession().getAttribute("user");


        if (admin != null) {
            whoami = "管理员";
            context.setVariable("whoami", whoami);
        } else if (user != null) {
            whoami = "用户";
            context.setVariable("whoami", whoami);
            context.setVariable("user", user.getUname());
        }


        ThymeleafUtil.process("devicestatus.html",context,resp.getWriter());
    }
}
