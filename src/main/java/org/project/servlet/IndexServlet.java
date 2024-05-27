package org.project.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.entity.Admin;
import org.project.entity.User;
import org.project.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String whoami;
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        User user = (User) req.getSession().getAttribute("user");

        if (admin == null) {
            whoami = "用户";
            context.setVariable("whoami", whoami);
            context.setVariable("name", user.getUname());
        } else {
            whoami = "管理员";
            context.setVariable("whoami", whoami);
        }


        ThymeleafUtil.process("index.html",context,resp.getWriter());
    }
}
