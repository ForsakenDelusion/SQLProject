package org.project.servlet.device.action;

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

@WebServlet("/buydevice")
public class BuyDeviceServlet extends HttpServlet {
    AdminService service;
    @Override
    public void init() throws ServletException {
        service = new AdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        context.setVariable("whoami", "管理员");
        ThymeleafUtil.process("buy-device.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Dname = req.getParameter("device-name");
        String Dprice = req.getParameter("device-price");
        service.buyDevice(Dname,Dprice);
        resp.sendRedirect("devicesale");
    }
}
