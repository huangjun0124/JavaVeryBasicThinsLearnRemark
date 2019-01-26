package Servlet;

import DAL.BookDAL;
import Model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FindBookByIdServlet")
public class FindBookByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        BookDAL bookService  = new BookDAL();
        Book book = bookService.findBookById(id);

        req.setAttribute("book",book);
        req.getRequestDispatcher("/admin/products/edit.jsp").forward(req, resp);
    }
}
