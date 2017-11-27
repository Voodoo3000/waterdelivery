package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.WaterDao;
import kz.epam.waterdelivery.entity.Water;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class ChangeWaterCommand implements Command {

    private static final CommandResult RESULT =  new CommandResult("do/admin_water", true);
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_PRICEPERLITER = "pricePerLiter";
    private static final String PARAM_ID = "id";
    private static final String ATTR_WATER_LIST = "waterList";

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        String type = request.getParameter(PARAM_TYPE);
        Integer pricePerLiter = Integer.valueOf(request.getParameter(PARAM_PRICEPERLITER));
        Integer id = Integer.parseInt(request.getParameter(PARAM_ID));

        WaterDao waterDao = new WaterDao();
        Water water = waterDao.getById(id);

        water.setType(type);
        water.setPricePerLiter(pricePerLiter);
        water.setId(id);
        waterDao.update(water);

        List<Water> waterList = waterDao.getAll();
        request.getSession().setAttribute(ATTR_WATER_LIST, waterList);

        return RESULT;
    }
}
