package com.briup.web.servlet;


import com.briup.bean.Customer;
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
 * @create 2022-09-22 15:13
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICustomerService customerService = new CustomerServiceImpl();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        try{
            Customer customer = customerService.login(name, password);
            //在web层存入session
            session.setAttribute("customer",customer);
           resp.sendRedirect("index.jsp");
        }catch(Exception e){
            e.printStackTrace();
            String message = e.getMessage();
            session.setAttribute("error",message);
            //日过失败，跳转到登录
            resp.sendRedirect("login.jsp");
        }
    }
}
