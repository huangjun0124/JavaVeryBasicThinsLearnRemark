package Servlet;

import Model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServletCommon {
    public static void forwardToProductListPage(List<Book> books, HttpServletRequest req,
                                                HttpServletResponse resp) throws ServletException, IOException {
        if(books != null){
            req.setAttribute("bookList", books);
            req.getRequestDispatcher("/admin/products/list.jsp").forward(req, resp);
        }
    }
}
