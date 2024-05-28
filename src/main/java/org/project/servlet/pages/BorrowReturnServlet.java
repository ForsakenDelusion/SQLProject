package org.project.servlet.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.entity.Admin;
import org.project.entity.User;
import org.project.service.UserService;
import org.project.service.impl.UserServiceImpl;
import org.project.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/borrowreturn")
public class BorrowReturnServlet extends HttpServlet {


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
        context.setVariable("user", user.getUname());
        context.setVariable("user_borrow_return_device_list", service.getUserBorrowReturnDeviceList(user.getUid()));
        ThymeleafUtil.process("borrowreturn.html",context,resp.getWriter());
    }
}
