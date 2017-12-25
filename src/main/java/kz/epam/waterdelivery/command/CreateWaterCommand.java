package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.WaterDao;
import kz.epam.waterdelivery.entity.Entity;
import kz.epam.waterdelivery.entity.Water;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CreateWaterCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(CreateWaterCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String type = request.getParameter(Entity.PARAM_TYPE);
        Integer pricePerLiter = Integer.valueOf(request.getParameter(Entity.PARAM_PRICEPERLITER));
        Water water = new Water();
        WaterDao waterDao = new WaterDao();
        List<Water> waterList;
        water.setType(type);
        water.setPricePerLiter(pricePerLiter);
        try {
            waterDao.add(water);
            LOGGER.info("Water was created by administrator" + water.getType() + " price per liter " + water.getPricePerLiter() + " kzt");
            waterList = waterDao.getAll();
            request.getSession().setAttribute(Entity.ATTR_WATER_LIST, waterList);
        } catch (DaoException e) {
            LOGGER.error("DaoException in CreateWaterCommand", e);
            throw new CommandException(e);
        }
        return Entity.ADMIN_WATER;
    }
}
