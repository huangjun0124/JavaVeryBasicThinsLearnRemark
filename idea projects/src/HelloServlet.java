import utils.SimpleConsoleOut;

import javax.servlet.*;
import java.io.IOException;

public class HelloServlet implements Servlet {
    public HelloServlet(){
        SimpleConsoleOut.WriteLine("Hello Servlet 构造中..........");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        SimpleConsoleOut.WriteLine("Hello Servlet Inited..........");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        SimpleConsoleOut.WriteLine("Hello Servlet responsing..........");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        servletResponse.getWriter().write("你好，这是第一个servlet程序测试。。。");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        SimpleConsoleOut.WriteLine("Hello Servlet Destryed..........");
    }
}
