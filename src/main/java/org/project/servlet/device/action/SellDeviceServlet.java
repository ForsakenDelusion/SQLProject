package org.project.servlet.device.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.dao.DeviceMapper;
import org.project.service.AdminService;
import org.project.service.UserService;
import org.project.service.impl.AdminServiceImpl;
import org.project.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet("/selldevice")
public class SellDeviceServlet extends HttpServlet {

    AdminService service;

    @Override
    public void init() throws ServletException {
        service = new AdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Did = req.getParameter("Did");//在前端用Thymeleaf获取到的Did值
        if (!Objects.equals(service.getDeviceStatusById(Did), "空闲中")) {
            // 前端弹窗提示，该设备非空闲状态
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<script>");
            out.println("alert('该设备非空闲状态');");
            out.println("window.location.href = 'devicesale';");
            out.println("</script>");
            out.flush();
        } else {
            service.sellDevice(Did);
            resp.sendRedirect("devicesale");
        }
    }

}
