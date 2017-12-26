package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.BottleSizeDao;
import kz.epam.waterdelivery.entity.BottleSize;
import kz.epam.waterdelivery.entity.Entity;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangeBottleSizeCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ChangeBottleSizeCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        double size = Double.parseDouble(request.getParameter(Entity.PARAM_SIZE));
        Integer id = Integer.parseInt(request.getParameter(Entity.PARAM_ID));
        BottleSize bottle;
        BottleSizeDao bottleSizeDao = new BottleSizeDao();
        try {
            bottle = bottleSizeDao.getById(id);
            bottle.setSize(size);
            bottle.setId(id);
            bottleSizeDao.update(bottle);
            LOGGER.info("Size of bottle " + bottle + " was changed " + "to " + size + " by administrator");
            List<BottleSize> bottleSizes;
            bottleSizes = bottleSizeDao.getAll();
            request.getSession().setAttribute(Entity.ATTR_BOTTLE_SIZES, bottleSizes);
        } catch (DaoException e) {
            LOGGER.error("DaoException in ChangeBottleSizeCommand", e);
            throw new CommandException(e);
        }
        return Entity.ADMIN_BOTTLE;
    }
}
