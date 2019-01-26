package Servlet;

import DAL.BookDAL;
import Model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchBookServlet")
public class SearchBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDAL bookService = new BookDAL();
        List<Book> books = bookService.findBookByCondition(req.getParameter("id"),req.getParameter("name"),req.getParameter("category"),
                req.getParameter("minprice"),req.getParameter("maxprice"));
        ServletCommon.forwardToProductListPage(books, req, resp);
    }
}
