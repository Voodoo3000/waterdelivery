package kz.epam.waterdelivery;

import kz.epam.waterdelivery.dao.sql.UserImpl;
import kz.epam.waterdelivery.dao.sql.WaterImpl;
import kz.epam.waterdelivery.dao.sql.WaterTypeImpl;
import kz.epam.waterdelivery.entity.WaterType;

import java.sql.SQLException;
import java.util.List;

public class rn {
  public static void main(String[] args) throws SQLException {

                                   //USER
/*
        //create
        UserImpl userService = new UserImpl();
        User user = new User();
        user.setUserId(3);
        user.setFirstname("Andrey");
        user.setLastName("Vasiliyev");
        user.setLoginEmail("dink@mail.ru");
        user.setPassword("zxcv");
        user.setRole(User.Role.CLIENT);
        user.setWallet(7000);
        userService.add(user);

        //getByLogin
        UserImpl userService = new UserImpl();
        System.out.println(userService.getByLogin("logr@mail.ru"));

        //getAll
        UserImpl userService = new UserImpl();
        try {
            List<User> userList = userService.getAll();

            for (User a: userList){
                System.out.println(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //update
        UserImpl userService = new UserImpl();
        User user = new User();
        user.setUserId(2);
        user.setFirstname("Pavel");
        user.setLastName("Bobylev");
        user.setLoginEmail("logr@mail.ru");
        user.setPassword("qweasdzxc");
        user.setRole(User.Role.CLIENT);
        user.setWallet(9000);
        userService.update(user);
*/

                     //CUSTOMERORDER
/*
        //create
        CustomerOrderImpl orderService = new CustomerOrderImpl();
        CustomerOrder order = new CustomerOrder();
        order.setOrderId(2);
        order.setCustomerId(2);
        order.setOrderContentId(1);
        order.setAmount(1500);
        order.setAddress("8 microdistrict building 74");
        orderService.add(order);

        //getAll
        CustomerOrderImpl orderService = new CustomerOrderImpl();
        List<CustomerOrder> orderList = orderService.getAll();
        for (CustomerOrder a : orderList) {
            System.out.println(a);
        }

         //getById
        CustomerOrderImpl orderService = new CustomerOrderImpl();
        System.out.println(orderService.getById(2));

        //update
        CustomerOrderImpl orderService = new CustomerOrderImpl();
        CustomerOrder order = new CustomerOrder();
        order.setOrderId(2);
        order.setCustomerId(2);
        order.setOrderContentId(1);
        order.setAmount(1500);
        order.setAddress("6 microdistrict building 11");
        orderService.update(order);

        //remove
        CustomerOrderImpl orderService = new CustomerOrderImpl();
        CustomerOrder order = new CustomerOrder();
        order.setOrderId(3);
        orderService.remove(order);

*/

                      //ORDERCONTENT
/*
        //create
        OrderContentImpl contentService = new OrderContentImpl();
        OrderContent content = new OrderContent();
        content.setOrderContentId(0);
        content.setWaterId(0);
        content.setBottleSize(3);
        content.setQuantity(2);
        contentService.add(content);

        //getAll
        OrderContentImpl contentService = new OrderContentImpl();
        List<OrderContent> contentList = contentService.getAll();
        for (OrderContent a : contentList) {
            System.out.println(a);
        }

        //getById
        OrderContentImpl contentService = new OrderContentImpl();
        System.out.println(contentService.getById(3));

        //update
        OrderContentImpl contentService = new OrderContentImpl();
        OrderContent content = new OrderContent();
        content.setOrderContentId(1);
        content.setWaterId(2);
        content.setBottleSize(4);
        content.setQuantity(7);
        contentService.update(content);

        //remove
        OrderContentImpl contentService = new OrderContentImpl();
        OrderContent content = new OrderContent();
        content.setOrderContentId(1);
        contentService.remove(content);

*/

                           //BOTLESIZE
/*
        //create
        BottleSizeImpl sizeService = new BottleSizeImpl();
        BottleSize bottle = new BottleSize();
        bottle.setBottleId(5);
        bottle.setSize(25);
        sizeService.add(bottle);

        //getAll
        BottleSizeImpl sizeService = new BottleSizeImpl();
        List<BottleSize> bottleSizeList = sizeService.getAll();
        for (BottleSize a : bottleSizeList) {
            System.out.println(a);
        }

        //getById
        BottleSizeImpl sizeService = new BottleSizeImpl();
        System.out.println(sizeService.getById(5));

        //update
        BottleSizeImpl sizeService = new BottleSizeImpl();
        BottleSize bottle = new BottleSize();
        bottle.setBottleId(5);
        bottle.setSize(30);
        sizeService.update(bottle);

        //remove
        BottleSizeImpl sizeService = new BottleSizeImpl();
        BottleSize bottle = new BottleSize();
        bottle.setBottleId(5);
        sizeService.remove(bottle);

*/
                                     //WATER
/*
        //create
        WaterImpl waterService = new WaterImpl();
        Water water = new Water();
        water.setWaterId(3);
        water.setWaterTypeId(3);
        water.setPricePerLiter(500);
        waterService.add(water);

        //getAll
        WaterImpl waterService = new WaterImpl();
        try {
            List<Water> waterList = waterService.getAll();

            for (Water a: waterList){
                System.out.println(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //getById
        WaterImpl waterService = new WaterImpl();
        System.out.println(waterService.getById(0));

        //update
        WaterImpl waterService = new WaterImpl();
        Water water = new Water();
        water.setWaterId(3);
        water.setWaterTypeId(3);
        water.setPricePerLiter(900);
        waterService.update(water);

        //remove
        WaterImpl waterService = new WaterImpl();
        Water water = new Water();
        water.setWaterId(3);
        waterService.remove(water);

*/

                         //WATERTYPE
/*
        //create
        WaterTypeImpl typeService = new WaterTypeImpl();
        WaterType waterType = new WaterType();
        waterType.setWaterTypeId(3);
        waterType.setType("Dr Pepper");
        typeService.add(waterType);

        //getAll
        WaterTypeImpl typeService = new WaterTypeImpl();
        List<WaterType> waterTypeList = typeService.getAll();
        for (WaterType a : waterTypeList){
            System.out.println(a);
        }

        //getById
        WaterTypeImpl typeService = new WaterTypeImpl();
        System.out.println(typeService.getById(3));

        //update
        WaterTypeImpl typeService = new WaterTypeImpl();
        WaterType waterType = new WaterType();
        waterType.setWaterTypeId(3);
        waterType.setType("Dr Pepper Smooth");
        typeService.update(waterType);


        //remove
        WaterTypeImpl typeService = new WaterTypeImpl();
        WaterType waterType = new WaterType();
        waterType.setWaterTypeId(3);
        typeService.remove(waterType);
*/
    }
}
