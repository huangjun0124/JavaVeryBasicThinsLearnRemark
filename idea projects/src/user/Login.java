package user;

import user.model.User;
import utils.SimpleConsoleOut;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("userId");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");// 不勾选则这里没有传值过来
        remember = remember == null ? "false" : "true";
        User user = UserContext.getUserById(username);
        Boolean isLoginValid = false;
        if( user != null && user.getPassword().equals(password)){
            isLoginValid = true;
        }
        if(isLoginValid){
            // 写入 session； 若要注销登陆：req.getSession().invalidate();
            req.getSession().setAttribute("curLogedUserId", user.getUserId());
            if(  remember.equals("true") )
            {
                Cookie ckName = new Cookie("username", username);
                ckName.setMaxAge(60 * 10);
                resp.addCookie(ckName);
                Cookie ckRemember = new Cookie("remember", remember);
                ckRemember.setMaxAge(60 * 10);
                resp.addCookie(ckRemember);

                Cookie ckPass = new Cookie("password", password);
                ckPass.setMaxAge(60 * 2);
                resp.addCookie(ckPass);
            }
        }
        else
        {
            // 用户校验失败,清除cookie
            // 否则浏览器本地还会缓存登陆成功的人的密码
            Cookie[] cks = req.getCookies();
            if(cks != null)
            {
                for (Cookie c : cks)
                {
                    c.setMaxAge(0);
                    resp.addCookie(c);
                }
            }
        }
    }
}
