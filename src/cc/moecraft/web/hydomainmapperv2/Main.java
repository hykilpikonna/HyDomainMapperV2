package cc.moecraft.web.hydomainmapperv2;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 此类由 Hykilpikonna 在 2018/04/19 创建!
 * Created by Hykilpikonna on 2018/04/19!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class Main implements ServletContextListener
{
    public static final String VERSION = "2.0.1";

    private static Main instance;

    public static Main getInstance()
    {
        return instance;
    }

    private MappingConfig mappingConfig;

    public static MappingConfig mappingConfig()
    {
        return getInstance().mappingConfig;
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        //Notification that the servlet context is about to be shut down.
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        // do all the tasks that you need to perform just after the server starts
        //Notification that the web application initialization process is starting

        instance = this;

        mappingConfig = new MappingConfig();
    }
}