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

@WebServlet("/shopCarDelete")
public class ShopCarDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer bookId = Integer.valueOf(req.getParameter("bookId"));
//        System.out.println(bookId);
        HttpSession session = req.getSession();
        Customer customer = (Customer)session.getAttribute("customer");
        Integer customerId = customer.getId();
//        System.out.println(id);
        IShopCarService shopCarService = new ShopCarServiceImpl();

        //此时执行删除操作
        shopCarService.deleteShopCarByCidAndBId(customerId,bookId);
        //在查询一下新的购物车记录
        List<ShopCar> shopCarsList = shopCarService.findShopCarsByCustomer(customerId);
        //如果查出来记录为空（删除的是最后一跳购物记录），可以跳转到主页
        if (shopCarsList.size() == 0) {
            resp.sendRedirect("/index.jsp");
        }else{
            //覆盖原本的购物车session
            session.setAttribute("shopCarByCustomerList",shopCarsList);
            //跳转到购物车页面 重新渲染页面
            resp.sendRedirect("/shopCart.jsp");
        }
    }
}