package org.project.servlet.fixer.page;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.entity.Fixer;
import org.project.service.UserService;
import org.project.service.impl.UserServiceImpl;
import org.project.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/fixerdevicemaintain")
public class FixerDeviceMaintain extends HttpServlet {

    UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        Fixer fixer = (Fixer) req.getSession().getAttribute("fixer");
        String Fid = String.valueOf(fixer.getFid());
        context.setVariable("whoami", "维修工");
        context.setVariable("name", fixer.getFName());
        context.setVariable("fix_device_list", service.getMaintainDeviceByFixerId(Fid));
        ThymeleafUtil.process("fixerdevicemaintain.html",context,resp.getWriter());
    }
}
