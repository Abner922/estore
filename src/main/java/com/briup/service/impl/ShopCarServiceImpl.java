package com.briup.service.impl;

import com.briup.bean.ShopCar;
import com.briup.dao.ShopCarMapper;
import com.briup.service.IShopCarService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author SDX
 * @create 2022-09-26 11:54
 */

public class ShopCarServiceImpl implements IShopCarService {
    SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
    ShopCarMapper mapper = sqlSession.getMapper(ShopCarMapper.class);

    @Override
    public void saveShopCar(ShopCar sc) {
        //先要判断 我现在加入购物车的这本书，在购物车表里有没有
        //根据用户ID和书籍ID来查询数据库中，该用户有没有添加过该本书的记录
        ShopCar shopCar = mapper.queryShopCarByCidAndBId(sc.getCustomerId(),sc.getBookId());
        //如果有的话，更新num
        //mapper.updateShopCar
        if (shopCar != null) {
            int sumNum = shopCar.getNum() + sc.getNum();
            sc.setNum(sumNum);
            mapper.updateShopCar(sc);
        } else {
            //如果没有的话，直接添加一条新纪录
            mapper.saveShopCar(sc);
        }
        sqlSession.commit();


    }

    @Override
    public void updateShopCar(ShopCar sc) {
        mapper.updateShopCar(sc);
        sqlSession.commit();
    }

    @Override
    public ShopCar queryShopCarByCidAndBId(int cid, int bid) {
        return null;
    }

    @Override
    public List<ShopCar> findShopCarsByCustomer(Integer id) {
        List<ShopCar> shopCarsByCustomer = mapper.findShopCarsByCustomer(id);
        return shopCarsByCustomer;
    }

    @Override
    public void deleteShopCarByCidAndBId(int cid, int bid) {
        mapper.deleteShopCarByCidAndBId(cid,bid);
        sqlSession.commit();
    }

    @Override
    public void deleteShopCarByCustomerId(int cid) {
        mapper.deleteShopCarByCustomerId(cid);
        sqlSession.commit();
    }
}
