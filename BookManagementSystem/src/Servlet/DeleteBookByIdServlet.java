package Servlet;

import DAL.BookDAL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteBookByIdServlet")
public class DeleteBookByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        BookDAL bookService = new BookDAL();
        bookService.deleteBookById(id);

        ServletCommon.forwardToProductListPage(bookService.findAllBooks(), req, resp);
    }
}
