import utils.GetAppSettings;
import utils.SimpleConsoleOut;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
需要在 web.xml 中配置 Listener 节点才能在服务器启动的时候调用
 */
public class StartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        SimpleConsoleOut.WriteLine("System Startup....");
        String path = servletContextEvent.getServletContext().getRealPath("/WEB-INF/appSettings.json");
        GetAppSettings.LoadSettings(path);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        SimpleConsoleOut.WriteLine("System down....");
    }
}
