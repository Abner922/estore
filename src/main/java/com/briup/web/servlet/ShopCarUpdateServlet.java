package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.bean.ShopCar;
import com.briup.service.IShopCarService;
import com.briup.service.impl.ShopCarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/shopCarUpdate")
public class ShopCarUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IShopCarService shopCarService = new ShopCarServiceImpl();
        //获取数据
        Integer bookId = Integer.valueOf(req.getParameter("bookId"));
        Integer num = Integer.valueOf(req.getParameter("num"));
        //获取当前用户的id
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        Integer customerId = customer.getId();
        ShopCar shopCar = new ShopCar(num, bookId, customerId);
        shopCarService.updateShopCar(shopCar);
        //在查询一下新的购物车记录
        List<ShopCar> shopCarList = shopCarService.findShopCarsByCustomer(customerId);
        //覆盖原本的购物车session
        session.setAttribute("shopCarByCustomerList", shopCarList);
        //跳转到购物车页面 重新渲染页面
        resp.sendRedirect("/shopCart.jsp");
    }
}