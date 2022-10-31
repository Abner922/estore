package com.briup.web.servlet;


import com.briup.service.ICustomerService;
import com.briup.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author SDX
 * @create 2022-09-23 9:20
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前session对象
        HttpSession session = req.getSession();
        //销毁session
        session.invalidate();
        //跳转回主页
        resp.sendRedirect("/index.jsp");
    }
}
