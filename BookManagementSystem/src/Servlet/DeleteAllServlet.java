package Servlet;

import DAL.BookDAL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteAllServlet")
public class DeleteAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameter("ids").split(",");
        BookDAL bookService = new BookDAL();
        bookService.deleteAllBook(ids);

        ServletCommon.forwardToProductListPage(bookService.findAllBooks(),req,resp);
    }
}
