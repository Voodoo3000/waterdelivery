package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.BottleSizeDao;
import kz.epam.waterdelivery.entity.BottleSize;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CreateBottleSizeCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(CreateBottleSizeCommand.class);
    private static final CommandResult RESULT = new CommandResult("do/admin_bottle", true);
    private static final String PARAM_SIZE = "size";
    private static final String ATTR_BOTTLE_SIZES = "bottleSizes";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        double size = Double.parseDouble(request.getParameter(PARAM_SIZE));
        BottleSize bottleSize = new BottleSize();
        BottleSizeDao bottleSizeDao = new BottleSizeDao();
        bottleSize.setSize(size);
        try {
            bottleSizeDao.add(bottleSize);
            LOGGER.info("Size of bottle " + size + " was created by administrator");
            List<BottleSize> bottleSizes;
            bottleSizes = bottleSizeDao.getAll();
            request.getSession().setAttribute(ATTR_BOTTLE_SIZES, bottleSizes);
        } catch (DaoException e) {
            LOGGER.error("DaoException in CreateBottleSizeCommand", e);
            throw new CommandException(e);
        }
        return RESULT;
    }
}
