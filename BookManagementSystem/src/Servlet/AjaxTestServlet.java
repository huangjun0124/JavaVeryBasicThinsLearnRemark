package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AjaxTest")
public class AjaxTestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String randomNum = req.getParameter("randomNum");
        resp.getWriter().write("{ \"userName\": \"" + userName + "\",\"randomNum\":\"" +randomNum+"\"}");
    }
}
