package com.briup.service.impl;


import com.briup.bean.Category;
import com.briup.dao.CategoryMapper;
import com.briup.service.ICategoryService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author SDX
 * @create 2022-09-23 10:17
 */

public class CategoryServiceImpl implements ICategoryService {
    SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
    CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
    @Override
    public List<Category> findAllCategorys() {
//        这里不需要业务逻辑判断
        return mapper.findAllCategorys() ;
    }
}
