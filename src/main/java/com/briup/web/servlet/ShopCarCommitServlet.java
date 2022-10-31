package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.bean.ShopAddress;
import com.briup.bean.ShopCar;
import com.briup.service.IShopAddressService;
import com.briup.service.IShopCarService;
import com.briup.service.impl.ShopAddressServiceImpl;
import com.briup.service.impl.ShopCarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/shopCarCommit")
public class ShopCarCommitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //该表单用于提交

        //清空当前用户的购物车
        IShopAddressService shopAddressService = new ShopAddressServiceImpl();
        IShopCarService shopCarService = new ShopCarServiceImpl();

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        Integer customerId = customer.getId();
        //首先你要查询当前用户的所有的收货地址存储到session以便confirm显式
        List<ShopAddress> addressList = shopAddressService.findAddressByCustomerId(customerId);
        session.setAttribute("addressList",addressList);

        shopCarService.deleteShopCarByCustomerId(customerId);
        //把session里的购物记录放到一个confirmList里
        List<ShopCar> shopCarList = (List<ShopCar>) session.getAttribute("shopCarByCustomerList");
        session.setAttribute("confirm",shopCarList);
        //移除原本的shopcarList
        session.removeAttribute("shopCarByCustomerList");
        //跳转到confirm.jsp
        resp.sendRedirect("/confirm.jsp");
    }
}