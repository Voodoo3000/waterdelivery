package kz.epam.waterdelivery.listener;

import kz.epam.waterdelivery.dao.sql.BottleSizeDao;
import kz.epam.waterdelivery.dao.sql.WaterDao;
import kz.epam.waterdelivery.entity.BottleSize;
import kz.epam.waterdelivery.entity.Water;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Locale;

public class ContextListener implements ServletContextListener {
    private static final String ATTR_LOCALE = "locale";
    private static final Locale DEFAULT_LOCALE = Locale.getDefault();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute(ATTR_LOCALE, DEFAULT_LOCALE);
        List<Water> waterList = new WaterDao().getAll();
        context.setAttribute("waterList", waterList);
        List<BottleSize> bottleSizes = new BottleSizeDao().getAll();
        context.setAttribute("bottleSizes", bottleSizes);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
