package kz.epam.waterdelivery.listener;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.BottleSizeDao;
import kz.epam.waterdelivery.dao.sql.WaterDao;
import kz.epam.waterdelivery.entity.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute(Entity.LOCALE, Entity.DEFAULT_LOCALE);
        List<Water> waterList = null;
        List<BottleSize> bottleSizes = null;
        try {
            waterList = new WaterDao().getAll();
            bottleSizes = new BottleSizeDao().getAll();
        } catch (DaoException e) {
            LOGGER.error("DaoException in ContextListener", e);
        }
        context.setAttribute(Entity.ATTR_WATER_LIST, waterList);
        context.setAttribute(Entity.ATTR_BOTTLE_SIZES, bottleSizes);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
