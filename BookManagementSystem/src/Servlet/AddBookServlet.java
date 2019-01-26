package Servlet;

import DAL.BookDAL;
import Model.Book;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book();
        try {
            BeanUtils.populate(book, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        BookDAL bookService = new BookDAL();
        bookService.addBook(book);

        List<Book> books = bookService.findAllBooks();
        ServletCommon.forwardToProductListPage(books, req, resp);
    }
}
