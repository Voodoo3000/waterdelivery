package kz.epam.waterdelivery.listener;

import kz.epam.waterdelivery.dao.sql.BottleSizeImpl;
import kz.epam.waterdelivery.dao.sql.WaterTypeImpl;
import kz.epam.waterdelivery.entity.BottleSize;
import kz.epam.waterdelivery.entity.WaterType;

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
        List<WaterType> waterTypes = new WaterTypeImpl().getAll();
        context.setAttribute("waterTypes", waterTypes);
        List<BottleSize> bottleSizes = new BottleSizeImpl().getAll();
        context.setAttribute("bottleSizes", bottleSizes);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
