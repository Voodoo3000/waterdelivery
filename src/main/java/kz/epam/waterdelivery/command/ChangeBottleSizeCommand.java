package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.BottleSizeDao;
import kz.epam.waterdelivery.entity.BottleSize;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class ChangeBottleSizeCommand implements Command {

    private static final CommandResult RESULT =  new CommandResult("do/admin_bottle", true);
    private static final String PARAM_SIZE = "size";
    private static final String PARAM_ID = "id";
    private static final String ATTR_BOTTLESIZE_LIST = "bottleSizeList";

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        double size = Double.parseDouble(request.getParameter(PARAM_SIZE));
        Integer id = Integer.parseInt(request.getParameter(PARAM_ID));

        BottleSize bottle = new BottleSize();
        BottleSizeDao bottleSizeDao = new BottleSizeDao();

        bottle.setSize(size);
        bottle.setId(id);
        bottleSizeDao.update(bottle);

        List<BottleSize> bottleSizeList = bottleSizeDao.getAll();
        request.getSession().setAttribute(ATTR_BOTTLESIZE_LIST, bottleSizeList);

        return RESULT;
    }
}
