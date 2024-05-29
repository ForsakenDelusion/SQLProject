package org.project.servlet.admin.action;

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

@WebServlet("/setmaintain")
public class SetMaintainServlet extends HttpServlet {

    AdminService service;
    @Override
    public void init() throws ServletException {
        service = new AdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        context.setVariable("whoami", "管理员");
        context.setVariable("fixer_list", service.getAllFixer());
        context.setVariable("Did",req.getParameter("Did"));
        ThymeleafUtil.process("add-fixer.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Did = req.getParameter("Did");//在前端用Thymeleaf获取到的Did值
        service.setDeviceMaintain(Did);
        String Fname = req.getParameter("fixer_name");
        service.setDeviceFixer(Fname,Did);
        resp.sendRedirect("status");
    }
}
