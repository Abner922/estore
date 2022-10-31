package com.briup.test;


import com.briup.bean.ShopCar;
import com.briup.dao.CustomerMapper;
import com.briup.dao.ShopCarMapper;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author SDX
 * @create 2022-09-22 14:15
 */

public class Customer {
    @Test
    public void test01(){
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        ShopCarMapper mapper = sqlSession.getMapper(ShopCarMapper.class);

        ShopCar shopCar = mapper.queryShopCarByCidAndBId(1, 1);
        ShopCar shopCar1 = new ShopCar(2,2,2);
        System.out.println(shopCar);
        mapper.saveShopCar(shopCar1);
    }

    @Test
    public void test02(){
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        ShopCarMapper mapper = sqlSession.getMapper(ShopCarMapper.class);
        List<ShopCar> shopCarsByCustomer = mapper.findShopCarsByCustomer(1);
        for (ShopCar shopCar : shopCarsByCustomer) {
            System.out.println(shopCar);
        }
    }
}
