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

@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAL bookService = new BookDAL();
        List<Book> books = bookService.findAllBooks();
        if(books != null){
            request.setAttribute("bookList", books);
            request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
        }
    }
}
