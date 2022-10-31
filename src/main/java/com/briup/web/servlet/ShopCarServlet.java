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

@WebServlet("/shopCarAdd")
public class ShopCarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer num = Integer.valueOf(req.getParameter("num"));
        Integer bookId = Integer.valueOf(req.getParameter("bookId"));
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        //customer.getId()通过这个方法取到用户的ID
        Integer customerId = customer.getId();
        //把num，bookId，customer封装成一个ShopCar类型
        ShopCar shopCar = new ShopCar(num,bookId,customerId);
        //调用service层的saveShopCar方法
        IShopCarService shopCarService = new ShopCarServiceImpl();
        shopCarService.saveShopCar(shopCar);

//        查询出当前用户的所有购物车记录
        List<ShopCar> shopCarsByCustomer = shopCarService.findShopCarsByCustomer(customerId);
//        shopCarsByCustomer.forEach(shopCar1 -> System.out.println(shopCar1));
//        把结果存进session中
//        double sum = 0.0;
//        for (ShopCar car : shopCarsByCustomer) {
//            sum += car.getNum()*car.getBook().getPrice();
////            System.out.println(sum);
//        }
//        session.setAttribute("sum",sum);
        session.setAttribute("shopCarByCustomerList",shopCarsByCustomer);
//        跳转到shopCar.jsp
        resp.sendRedirect("shopCart.jsp");

    }
}
