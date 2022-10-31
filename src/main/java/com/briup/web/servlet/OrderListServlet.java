package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.bean.OrderForm;
import com.briup.service.IOrderFormService;
import com.briup.service.impl.OrderFormServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderList")
public class OrderListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IOrderFormService orderFormService = new OrderFormServiceImpl();
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        Integer customerId = customer.getId();
        List<OrderForm> orderFormList = orderFormService.findOrderFormByCustomerId(customerId);
        //当前用户所有的订单信息
        session.setAttribute("orderFormList",orderFormList);
        resp.sendRedirect("/orderlist.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}