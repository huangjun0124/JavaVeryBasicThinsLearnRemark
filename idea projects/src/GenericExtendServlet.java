import utils.SimpleConsoleOut;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class GenericExtendServlet extends GenericServlet {
    public GenericExtendServlet(){
        SimpleConsoleOut.WriteLine("GenericExtendServlet 初始化.....");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().write("Extends GenericServlet class, 你好，欢迎访问!");
    }
}
