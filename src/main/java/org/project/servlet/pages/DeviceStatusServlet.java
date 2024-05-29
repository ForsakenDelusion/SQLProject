package org.project.servlet.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.service.AdminService;
import org.project.service.impl.AdminServiceImpl;
import org.project.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/status")
public class DeviceStatusServlet extends HttpServlet {

    AdminService service;

    @Override
    public void init() throws ServletException {
        service = new AdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String whoami;
        whoami = "管理员";
        context.setVariable("whoami", whoami);
        context.setVariable("device_status_list", service.getDeviceStatus());
        ThymeleafUtil.process("devicestatus.html",context,resp.getWriter());
    }
}
