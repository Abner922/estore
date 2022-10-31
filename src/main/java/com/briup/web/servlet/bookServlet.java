package com.briup.web.servlet;


import com.briup.bean.Book;
import com.briup.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author SDX
 * @create 2022-09-26 9:14
 */
@WebServlet("/bookInfo")
public class bookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        System.out.println(bookId);
        int id = Integer.parseInt(bookId);

        BookServiceImpl bookService = new BookServiceImpl();
        Book bookById = bookService.findBookById(id);
        //存入session
        HttpSession session = req.getSession();
        session.setAttribute("bookInfo",bookById);
//        req.setAttribute("findBook",bookById);
        //跳转并显示
//        req.getRequestDispatcher("viewBook.jsp").forward(req,resp);
        resp.sendRedirect("viewBook.jsp");
    }
}
