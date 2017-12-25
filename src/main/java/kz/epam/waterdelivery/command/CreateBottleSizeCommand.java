package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.BottleSizeDao;
import kz.epam.waterdelivery.entity.BottleSize;
import kz.epam.waterdelivery.entity.Entity;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CreateBottleSizeCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(CreateBottleSizeCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        double size = Double.parseDouble(request.getParameter(Entity.PARAM_SIZE));
        BottleSize bottleSize = new BottleSize();
        BottleSizeDao bottleSizeDao = new BottleSizeDao();
        bottleSize.setSize(size);
        try {
            bottleSizeDao.add(bottleSize);
            LOGGER.info("Size of bottle " + size + " was created by administrator");
            List<BottleSize> bottleSizes;
            bottleSizes = bottleSizeDao.getAll();
            request.getSession().setAttribute(Entity.ATTR_BOTTLE_SIZES, bottleSizes);
        } catch (DaoException e) {
            LOGGER.error("DaoException in CreateBottleSizeCommand", e);
            throw new CommandException(e);
        }
        return Entity.ADMIN_BOTTLE;
    }
}
