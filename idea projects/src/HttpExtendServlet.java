import utils.SimpleConsoleOut;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpExtendServlet extends HttpServlet {
    public HttpExtendServlet(){
        SimpleConsoleOut.WriteLine("HttpExtendServlet 初始化.....");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Extends HttpServlet class, 你好，欢迎访问Ya !");
    }
}
