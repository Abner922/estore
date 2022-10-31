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
 * @create 2022-09-22 11:06
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICustomerService customerService = new CustomerServiceImpl();
        //接收前端穿过来的参数
        String name = req.getParameter("name");
        //接收其他参数...
        String password = req.getParameter("password");
        String zipCode = req.getParameter("zipCode");
        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");
        String email = req.getParameter("email");

        Customer customer = new Customer();
        //3.调用对应的set方法set道对象当中
        customer.setName(name);
        customer.setPassword(password);
        customer.setZipCode(zipCode);
        customer.setAddress(address);
        customer.setTelephone(telephone);
        customer.setEmail(email);

        //调用对应方法
        HttpSession session = req.getSession();
        try{
            customerService.register(customer);
            //跳转到登录页面
            resp.sendRedirect("login.jsp");
        }catch(Exception e){
            e.printStackTrace();
            String message = e.getMessage();
            session.setAttribute("error",message);
            //跳转到注册页面
            resp.sendRedirect("register.jsp");
        }
    }
}
