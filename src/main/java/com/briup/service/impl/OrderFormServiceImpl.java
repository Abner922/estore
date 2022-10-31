package com.briup.service.impl;


import com.briup.bean.OrderForm;
import com.briup.dao.CustomerMapper;
import com.briup.dao.OrderFormMapper;
import com.briup.service.IOrderFormService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author SDX
 * @create 2022-09-28 11:22
 */

public class OrderFormServiceImpl implements IOrderFormService {
    SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
    OrderFormMapper mapper = sqlSession.getMapper(OrderFormMapper.class);
    @Override
    public void saveOrderForm(OrderForm of) {
        mapper.saveOrderForm(of);
        sqlSession.commit();
    }

    @Override
    public OrderForm findOrderFormByOrderid(Long orderid) {
        return null;
    }

    @Override
    public List<OrderForm> findOrderFormByCustomerId(Integer id) {
        return mapper.findOrderFormByCustomerId(id);
    }

    @Override
    public void deleteOrderFormById(Integer id) {

    }
}
