package com.briup.web.listener;


import com.briup.bean.Book;
import com.briup.bean.Category;
import com.briup.service.IBookService;
import com.briup.service.ICategoryService;
import com.briup.service.impl.BookServiceImpl;
import com.briup.service.impl.CategoryServiceImpl;
//import com.briup.service.impl.CategoryServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author SDX
 * @create 2022-09-23 10:09
 */
@WebListener
public class  IndexListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //1.获取servletContext对象
        ServletContext servletContext = sce.getServletContext();

        ICategoryService categoryService = new CategoryServiceImpl();
        List<Category> allCategorys = categoryService.findAllCategorys();
        //把查询结果存储到最大的作用于中
//        xxx.setAttribute("CategoryList",?);
        servletContext.setAttribute("CategoryList",allCategorys);

        //在监听器中查询图书 然后存入对应的作用域
        IBookService bookService = new BookServiceImpl();
        List<Book> allBooks = bookService.findAllBooks();
        servletContext.setAttribute("allBooks",allBooks);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
