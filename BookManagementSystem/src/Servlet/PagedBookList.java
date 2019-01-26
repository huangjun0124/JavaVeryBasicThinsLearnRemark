package Servlet;

import DAL.BookDAL;
import Model.Book;
import Model.Model.PageResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/PagedBookListServlet")
public class PagedBookList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDAL bookService = new BookDAL();
        String pageIndex = req.getParameter("pageIndex");
        int pageCnt = 1;
        if(pageIndex != null && !pageIndex.equals("")){
            pageCnt = Integer.parseInt(pageIndex);
        }
        PageResult<Book> bookList = bookService.getBookListWithPageCount(pageCnt);
        req.setAttribute("bookList", bookList);
        req.getRequestDispatcher("/admin/products/pagedlist.jsp").forward(req, resp);
    }
}
