package org.project.servlet.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.service.IndexService;
import org.project.service.impl.IndexServiceImpl;
import org.project.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/devicesale")
public class DeviceSaleServlet extends HttpServlet {
    IndexService service;

    @Override
    public void init() throws ServletException {
        service = new IndexServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String whoami;
        whoami = "管理员";
        context.setVariable("whoami", whoami);
        context.setVariable("index_device_list", service.getNewestDeviceList());
        ThymeleafUtil.process("devicesale.html",context,resp.getWriter());
    }
}
