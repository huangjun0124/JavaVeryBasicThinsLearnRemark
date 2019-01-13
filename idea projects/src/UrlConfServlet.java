import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dispather")
public class UrlConfServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String siteName = (String)this.getServletContext().getInitParameter("MySiteName");
        // 下面不设置则 会乱码
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // 转发给别的 Servlet
        RequestDispatcher rd = req.getRequestDispatcher("/getHtp");
        rd.include(req, resp);// include 方法会将目标输出并入当前

        //只有在尚未向客户端输出响应时才可以调用forward()方法，如果页面缓存不为空，在重定向前将自动清除缓存
        //调用forward()方法后，原先存放在HttpResponse对象中的内容将会自动被清除.

        resp.getWriter().write("\nSiteName : " + siteName + "\n");
        resp.getWriter().write("ResourcePath : " + this.getServletContext().getRealPath("WEB-INF/web.xml"));
    }
}
