package com.briup.service.impl;


import com.briup.bean.Book;
import com.briup.dao.BookMapper;
import com.briup.service.IBookService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author SDX
 * @create 2022-09-23 15:14
 */

public class BookServiceImpl implements IBookService {
    SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
    BookMapper mapper = sqlSession.getMapper(BookMapper.class);
    @Override
    public List<Book> findAllBooks() {
        return mapper.findAllBooks();
    }

    @Override
    public Book findBookById(Integer id) {
        return mapper.findBookById(id);
    }
}
