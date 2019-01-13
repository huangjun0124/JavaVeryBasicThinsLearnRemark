package CookieSession;

import utils.DateFormat;
import utils.SimpleConsoleOut;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/CookieSession/LastVisitTime")
public class CookieTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        Cookie[] cks = req.getCookies();
        if(cks != null) // cookie 超时，则请求不会再携带超时的 cookie 数据
        {
            for (Cookie c : cks)
            {
                SimpleConsoleOut.WriteLine("Cookie Name ["+c.getName()+"], Value ["+c.getValue()+"]");
                if(c.getName().equals("lastVisitTime"))
                {
                    // 上次访问数据，转换为 datetime 返回
                    long milliSeconds = Long.parseLong(c.getValue());
                    Date date = new Date(milliSeconds);
                    resp.getWriter().write("上次访问时间："+ DateFormat.FormatDate(date));
                }
            }
        }

        SimpleConsoleOut.WriteLine("Request.getHeader('cookie') : " + req.getHeader("cookie"));
        Cookie ck = new Cookie("lastVisitTime", System.currentTimeMillis()+"");
        // 设置存活时间--1 分钟
        ck.setMaxAge(60 * 1); // -1 永久存储； 0 删除 cookie； 正整数则单位为秒
        resp.addCookie(ck);
    }
}
