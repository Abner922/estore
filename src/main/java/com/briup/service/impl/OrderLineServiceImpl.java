package com.briup.service.impl;


import com.briup.bean.OrderLine;
import com.briup.dao.OrderLineMapper;
import com.briup.dao.ShopAddressMapper;
import com.briup.service.IOrderLineService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author SDX
 * @create 2022-09-28 18:12
 */
public class OrderLineServiceImpl implements IOrderLineService {
    SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
    OrderLineMapper mapper = sqlSession.getMapper(OrderLineMapper.class);
    @Override
    public void saveOrderLine(OrderLine ol) {
        mapper.saveOrderLine(ol);
        sqlSession.commit();
    }

    @Override
    public List<OrderLine> findOrderLineByOrderId(Integer id) {
        return null;
    }
}
