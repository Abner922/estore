package com.briup.web.servlet;

import com.briup.bean.*;
import com.briup.dao.OrderFormMapper;
import com.briup.service.IOrderFormService;
import com.briup.service.IOrderLineService;
import com.briup.service.IShopAddressService;
import com.briup.service.impl.OrderFormServiceImpl;
import com.briup.service.impl.OrderLineServiceImpl;
import com.briup.service.impl.ShopAddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/orderCommit")
public class OrderCommitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IShopAddressService shopAddressService = new ShopAddressServiceImpl();
        IOrderFormService orderFormService = new OrderFormServiceImpl();
        IOrderLineService orderLineService = new OrderLineServiceImpl();
        ShopAddress shopAddress = new ShopAddress();
        //1.接收参数
        String receiveName = req.getParameter("receiveName");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String shipAddId = req.getParameter("shipAddId");
        //获取当前登录用户
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        Integer customerId = customer.getId();

        //判断一下是新地址还是旧地址
        //如果接收到的这三个数据不为空，就认为是新地址
        if (receiveName.equals("") && address.equals("") && phone.equals("")) {
            //如果为空，就是旧地址
            if(shipAddId.equals("")){
                resp.sendRedirect("/confirm.jsp");
                return;
            }else{
                Integer addId = Integer.valueOf(shipAddId);
                shopAddress.setId(addId);
            }
        }else{
            //如果为空，就是新地址
            shopAddress.setReceiveName(receiveName);
            shopAddress.setAddress(address);
            shopAddress.setPhone(phone);
            shopAddress.setCustomer(customer);
            shopAddressService.saveAddress(shopAddress);
        }
        //可以通过接收参数形式获取旧地址的id，通过这个id查询地址信息（拿到这个id之后new shopAddress 把id放进去）

        List<ShopCar> shopCarList = (List<ShopCar>)session.getAttribute("confirm");
        double total = 0.0;
        for (ShopCar shopCar : shopCarList) {
            total = total + shopCar.getNum()*shopCar.getBook().getPrice();
        }
        //开始准备一个orderform对象（时间可以直接new Date）
        OrderForm orderForm = new OrderForm();
        orderForm.setCost(total);
        orderForm.setOrderdate(new Date());
        orderForm.setShopAddress(shopAddress);
        orderForm.setCustomer(customer);
        //并且调用对应的方法saveOrderForm（需要selectKey）
        orderFormService.saveOrderForm(orderForm);
        //把对应的订单项存入数据库中（需要订单表的id）
        for (ShopCar shopCar : shopCarList) {
            OrderLine orderLine = new OrderLine();
            orderLine.setBook(shopCar.getBook());
            orderLine.setNum(Long.valueOf(shopCar.getNum()));
            orderLine.setCost(shopCar.getNum()*shopCar.getBook().getPrice());
            orderLine.setOrderForm(orderForm);
            orderLineService.saveOrderLine(orderLine);
        }
        resp.sendRedirect("/orderList");
    }

}