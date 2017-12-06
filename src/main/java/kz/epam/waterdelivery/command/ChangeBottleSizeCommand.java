package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.BottleSizeDao;
import kz.epam.waterdelivery.entity.BottleSize;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangeBottleSizeCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ChangeBottleSizeCommand.class);
    private static final CommandResult RESULT = new CommandResult("do/admin_bottle", true);
    private static final String PARAM_SIZE = "size";
    private static final String PARAM_ID = "id";
    private static final String ATTR_BOTTLE_SIZES = "bottleSizes";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        double size = Double.parseDouble(request.getParameter(PARAM_SIZE));
        Integer id = Integer.parseInt(request.getParameter(PARAM_ID));
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
            request.getSession().setAttribute(ATTR_BOTTLE_SIZES, bottleSizes);
        } catch (DaoException e) {
            LOGGER.error("DaoException in ChangeBottleSizeCommand", e);
            throw new CommandException(e);
        }
        return RESULT;
    }
}
