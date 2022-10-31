package com.briup.service.impl;


import com.briup.bean.ShopAddress;
import com.briup.dao.CustomerMapper;
import com.briup.dao.ShopAddressMapper;
import com.briup.service.IShopAddressService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author SDX
 * @create 2022-09-27 15:27
 */

public class ShopAddressServiceImpl implements IShopAddressService {
    SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
    ShopAddressMapper mapper = sqlSession.getMapper(ShopAddressMapper.class);
    @Override
    public List<ShopAddress> findAddressByCustomerId(Integer id) {
        return mapper.findAddressByCustomerId(id);
    }

    @Override
    public void saveAddress(ShopAddress sd) {
        mapper.saveAddress(sd);
        sqlSession.commit();
    }

    @Override
    public ShopAddress findShopAddressById(Integer id) {
        return null;
    }
}
