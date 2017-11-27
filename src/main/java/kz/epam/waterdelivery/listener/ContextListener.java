package kz.epam.waterdelivery.listener;

import kz.epam.waterdelivery.dao.sql.BottleSizeDao;
import kz.epam.waterdelivery.dao.sql.WaterDao;
import kz.epam.waterdelivery.entity.BottleSize;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.entity.User;
import kz.epam.waterdelivery.entity.Water;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Locale;

public class ContextListener implements ServletContextListener {
    private static final String ATTR_LOCALE = "locale";
    private static final String ATTR_WATER_LIST = "waterList";
    private static final String ATTR_BOTTLE_SIZES = "bottleSizes";
    private static final String ATTR_ROLES = "roles";
    private static final String ATTR_STATES = "states";
    private static final String ATTR_STATUSES = "statuses";
    private static final Locale DEFAULT_LOCALE = Locale.getDefault();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute(ATTR_LOCALE, DEFAULT_LOCALE);
        List<Water> waterList = new WaterDao().getAll();
        context.setAttribute(ATTR_WATER_LIST, waterList);
        List<BottleSize> bottleSizes = new BottleSizeDao().getAll();
        context.setAttribute(ATTR_BOTTLE_SIZES, bottleSizes);
        context.setAttribute(ATTR_ROLES, User.Role.values());
        context.setAttribute(ATTR_STATES, User.State.values());
        context.setAttribute(ATTR_STATUSES, CustomerOrder.Status.values());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
