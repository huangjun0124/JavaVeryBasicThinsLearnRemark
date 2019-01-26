package Servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

public class ServletCommonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("ServletCommonFilter Inited......................................");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        LogRequestInfo((HttpServletRequest)servletRequest);
        servletResponse.setCharacterEncoding("utf-8");
        //servletResponse.setContentType("text/html;charset=utf-8");
        // 把请求传回过滤链
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private void LogRequestInfo(HttpServletRequest request){
        System.out.println("getRemoteUser：" + request.getRemoteUser());
        System.out.println("getRemoteAddr：" + request.getRemoteAddr());
        System.out.println("getRemoteHost：" + request.getRemoteHost());
        System.out.println("getMethod：" + request.getMethod());
        System.out.println("getRequestURL：" + request.getRequestURL());
        System.out.println("getRequestURI：" + request.getRequestURI());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements())
        {
            String name = headerNames.nextElement();
            System.out.println("Header ["+name+"] ： " + request.getHeader(name));
        }
        // 获取输入的参数
        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> stringEntry : map.entrySet())
        {
            System.out.println("Param [" + stringEntry.getKey() + "] : ");
            String[] values = stringEntry.getValue();
            System.out.println(String.join(", ", values));
        }
    }

    @Override
    public void destroy() {
        System.out.println("ServletCommonFilter destroyed......................................");
    }
}
