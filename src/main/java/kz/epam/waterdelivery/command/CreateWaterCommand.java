package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.WaterDao;
import kz.epam.waterdelivery.entity.Water;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class CreateWaterCommand implements Command {

    private static final CommandResult RESULT =  new CommandResult("do/admin_water", true);
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_PRICEPERLITER = "pricePerLiter";
    private static final String ATTR_WATER_LIST = "waterList";

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        String type = request.getParameter(PARAM_TYPE);
        Integer pricePerLiter = Integer.valueOf(request.getParameter(PARAM_PRICEPERLITER));

        Water water = new Water();
        WaterDao waterDao = new WaterDao();

        water.setType(type);
        water.setPricePerLiter(pricePerLiter);
        waterDao.add(water);

        List<Water> waterList = waterDao.getAll();
        request.getSession().setAttribute(ATTR_WATER_LIST, waterList);

        return RESULT;
    }
}
