package user;

import org.apache.commons.beanutils.BeanUtils;
import user.model.User;
import utils.SimpleConsoleOut;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get 请求直接返回 html 页面
        RequestDispatcher rd = req.getRequestDispatcher("/register.html");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        String verifyCode = request.getParameter("verifyCode").toLowerCase();
        // get verifyCode in Session
        String sessionCacheCode = (String)request.getSession().getAttribute("verifyCode");
        if(!sessionCacheCode.toLowerCase().equals(verifyCode))
        {
            response.setHeader("Refresh", 2 + ";");//自动刷新
            response.getWriter().write("SessionValidateWrong: 请输入正确的验证码！");
            return;
        }

        logRequestInfo(request);
        User user = generateUser(request);
        User user2 = new User();
        try {
            BeanUtils.populate(user2, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // 检查用户名是否已被占用
        if(UserContext.getUserById(user2.getUserId()) != null){
            response.setHeader("Refresh", 2 + ";");//自动刷新
            response.getWriter().write("UserIdWrong: 用户名在系统中已存在！");
            return;
        }
        if(UserContext.addUser(user2) == 0){
            response.setHeader("Refresh", 2 + ";");//自动刷新
            response.getWriter().write("注册失败！");
            return;
        }
        // 3 秒后自动跳转主页
        redirectToHomePage(3, response);
    }

    // index.html 和 "/" 作用一样
    private void redirectToHomePage(int seconds, HttpServletResponse response) throws IOException {
        //response.setHeader("Refresh", "1");//每隔一秒刷新一次
        response.setHeader("Refresh", seconds + ";URL=/");//秒后转到另一页面
        response.getWriter().write("注册成功! "+seconds+"秒后会自动跳转，若没有中转点击<a href='index.html'>这里</a>");
    }

    private void logRequestInfo(HttpServletRequest request){
        SimpleConsoleOut.WriteLine("getRemoteUser：" + request.getRemoteUser());
        SimpleConsoleOut.WriteLine("getRemoteAddr：" + request.getRemoteAddr());
        SimpleConsoleOut.WriteLine("getRemoteHost：" + request.getRemoteHost());
        SimpleConsoleOut.WriteLine("getMethod：" + request.getMethod());
        SimpleConsoleOut.WriteLine("getRequestURL：" + request.getRequestURL());
        SimpleConsoleOut.WriteLine("getRequestURI：" + request.getRequestURI());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements())
        {
            String name = headerNames.nextElement();
            SimpleConsoleOut.WriteLine("Header ["+name+"] ： " + request.getHeader(name));
        }

        // 获取输入的参数
        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> stringEntry : map.entrySet())
        {
            SimpleConsoleOut.WriteLine("Param [" + stringEntry.getKey() + "] : ");
            String[] values = stringEntry.getValue();
            SimpleConsoleOut.WriteLine(String.join(", ", values));
        }

    }

    private User generateUser(HttpServletRequest request){
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();
        try {
            for(Map.Entry<String, String[]> entry : map.entrySet()){
                String pname = entry.getKey();

                //反射
                //创建属性描述器
                PropertyDescriptor pd = new PropertyDescriptor(pname, User.class);
                //获取写入方法对象
                Method method = pd.getWriteMethod();
                //通过反射调用方法【怎么获取方法的参数类型】
                Class[] clzs = method.getParameterTypes();
                if(clzs.length == 0){
                    return null;
                }

                String clzName = clzs[0].toString();
                String[] values = entry.getValue();
                if(clzName.contains("[Ljava.lang.String")){
                    System.out.println("数组...");
                    method.invoke(user, (Object)values);
                }else{
                    System.out.println("非数组...");
                    method.invoke(user, values[0]);
                }

				/*String[] values = entry.getValue(); 这种方式有bug
				if(values.length == 1){
					method.invoke(user, values[0]);
				}else{
					method.invoke(user, (Object)values);
				}
				*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
