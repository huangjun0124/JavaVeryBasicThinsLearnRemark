package utils;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/util/getVerifyCodeUsingJar")
public class VerifyCodeUsingJar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //浏览器刷新时就不会有缓存
        resp.addHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Expires", "0");

        ValidateCode code = new ValidateCode(120, 30, 4, 6);

        // 将验证码写入 Session---不同的浏览器、或者重新启动浏览器对应的sessionid 都不同
        req.getSession().setAttribute("verifyCode", code.getCode());

        code.write(resp.getOutputStream());
        // 写入缓存
        String cookie = req.getHeader("cookie");
        this.getServletContext().setAttribute(req.getRemoteAddr() + cookie, code.getCode());
    }
}
