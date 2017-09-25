package kz.epam.waterdelivery.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Locale;

public class ContextListener implements ServletContextListener {
    private static final String ATTR_LOCALE = "locale";
    private static final Locale DEFAULT_LOCALE = Locale.getDefault();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute(ATTR_LOCALE, DEFAULT_LOCALE);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
