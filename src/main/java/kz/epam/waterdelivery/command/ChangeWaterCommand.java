package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.WaterDao;
import kz.epam.waterdelivery.entity.Entity;
import kz.epam.waterdelivery.entity.Water;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangeWaterCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ChangeWaterCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {

        String type = request.getParameter(Entity.PARAM_TYPE);
        Integer pricePerLiter = Integer.valueOf(request.getParameter(Entity.PARAM_PRICEPERLITER));
        Integer id = Integer.parseInt(request.getParameter(Entity.PARAM_ID));
        WaterDao waterDao = new WaterDao();
        Water water;
        List<Water> waterList;
        try {
            water = waterDao.getById(id);
            water.setType(type);
            water.setPricePerLiter(pricePerLiter);
            water.setId(id);
            waterDao.update(water);
            LOGGER.info("Water " + water + " was changed by administrator");
            waterList = waterDao.getAll();
            request.getSession().setAttribute(Entity.ATTR_WATER_LIST, waterList);
        } catch (DaoException e) {
            LOGGER.error("DaoException in ChangeWaterCommand", e);
            throw new CommandException(e);
        }
        return Entity.ADMIN_WATER;
    }
}
