package kz.epam.waterdelivery.listener;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.BottleSizeDao;
import kz.epam.waterdelivery.dao.sql.WaterDao;
import kz.epam.waterdelivery.entity.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Locale;

public class ContextListener implements ServletContextListener {

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
            e.printStackTrace();
        }
        context.setAttribute(Entity.ATTR_WATER_LIST, waterList);
        context.setAttribute(Entity.ATTR_BOTTLE_SIZES, bottleSizes);
        context.setAttribute(Entity.PARAM_ROLES, User.Role.values());
        context.setAttribute(Entity.PARAM_STATES, User.State.values());
        context.setAttribute(Entity.PARAM_STATUSES, CustomerOrder.Status.values());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
