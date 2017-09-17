package kz.epam.waterdelivery;

import kz.epam.waterdelivery.dao.h2Service.*;
import kz.epam.waterdelivery.entity.*;

import java.sql.SQLException;
import java.util.List;

public class rn {
   // public static void main(String[] args) throws SQLException {

                     //CUSTOMERORDER
/*
        //create
        CustomerOrderService orderService = new CustomerOrderService();
        CustomerOrder order = new CustomerOrder();
        order.setOrderId(2);
        order.setCustomerId(2);
        order.setOrderContentId(1);
        order.setAmount(1500);
        order.setAddress("8 microdistrict building 74");
        orderService.add(order);

        //getAll
        CustomerOrderService orderService = new CustomerOrderService();
        List<CustomerOrder> orderList = orderService.getAll();
        for (CustomerOrder a : orderList) {
            System.out.println(a);
        }

         //getById
        CustomerOrderService orderService = new CustomerOrderService();
        System.out.println(orderService.getById(2));

        //update
        CustomerOrderService orderService = new CustomerOrderService();
        CustomerOrder order = new CustomerOrder();
        order.setOrderId(2);
        order.setCustomerId(2);
        order.setOrderContentId(1);
        order.setAmount(1500);
        order.setAddress("6 microdistrict building 11");
        orderService.update(order);

        //remove
        CustomerOrderService orderService = new CustomerOrderService();
        CustomerOrder order = new CustomerOrder();
        order.setOrderId(3);
        orderService.remove(order);


*/

                      //ORDERCONTENT
/*
        //create
        OrderContentService contentService = new OrderContentService();
        OrderContent content = new OrderContent();
        content.setOrderContentId(0);
        content.setWaterId(0);
        content.setBottleSize(3);
        content.setQuantity(2);
        contentService.add(content);

        //getAll
        OrderContentService contentService = new OrderContentService();
        List<OrderContent> contentList = contentService.getAll();
        for (OrderContent a : contentList) {
            System.out.println(a);
        }

        //getById
        OrderContentService contentService = new OrderContentService();
        System.out.println(contentService.getById(3));

        //update
        OrderContentService contentService = new OrderContentService();
        OrderContent content = new OrderContent();
        content.setOrderContentId(1);
        content.setWaterId(2);
        content.setBottleSize(4);
        content.setQuantity(7);
        contentService.update(content);

        //remove
        OrderContentService contentService = new OrderContentService();
        OrderContent content = new OrderContent();
        content.setOrderContentId(1);
        contentService.remove(content);

*/

                           //BOTLESIZE
/*
        //create
        BottleSizeService sizeService = new BottleSizeService();
        BottleSize bottle = new BottleSize();
        bottle.setBottleId(5);
        bottle.setSize(25);
        sizeService.add(bottle);

        //getAll
        BottleSizeService sizeService = new BottleSizeService();
        List<BottleSize> bottleSizeList = sizeService.getAll();
        for (BottleSize a : bottleSizeList) {
            System.out.println(a);
        }

        //getById
        BottleSizeService sizeService = new BottleSizeService();
        System.out.println(sizeService.getById(5));

        //update
        BottleSizeService sizeService = new BottleSizeService();
        BottleSize bottle = new BottleSize();
        bottle.setBottleId(5);
        bottle.setSize(30);
        sizeService.update(bottle);

        //remove
        BottleSizeService sizeService = new BottleSizeService();
        BottleSize bottle = new BottleSize();
        bottle.setBottleId(5);
        sizeService.remove(bottle);

*/
                                     //WATER
/*
        //create
        WaterService waterService = new WaterService();
        Water water = new Water();
        water.setWaterId(3);
        water.setWaterTypeId(3);
        water.setPricePerLiter(500);
        waterService.add(water);

        //getAll
        WaterService waterService = new WaterService();
        try {
            List<Water> waterList = waterService.getAll();

            for (Water a: waterList){
                System.out.println(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //getById
        WaterService waterService = new WaterService();
        System.out.println(waterService.getById(0));

        //update
        WaterService waterService = new WaterService();
        Water water = new Water();
        water.setWaterId(3);
        water.setWaterTypeId(3);
        water.setPricePerLiter(900);
        waterService.update(water);

        //remove
        WaterService waterService = new WaterService();
        Water water = new Water();
        water.setWaterId(3);
        waterService.remove(water);

*/

                         //WATERTYPE
/*
        //create
        WaterTypeService typeService = new WaterTypeService();
        WaterType waterType = new WaterType();
        waterType.setWaterTypeId(3);
        waterType.setType("Dr Pepper");
        typeService.add(waterType);

        //getAll
        WaterTypeService typeService = new WaterTypeService();
        List<WaterType> waterTypeList = typeService.getAll();
        for (WaterType a : waterTypeList){
            System.out.println(a);
        }

        //getById
        WaterTypeService typeService = new WaterTypeService();
        System.out.println(typeService.getById(3));

        //update
        WaterTypeService typeService = new WaterTypeService();
        WaterType waterType = new WaterType();
        waterType.setWaterTypeId(3);
        waterType.setType("Dr Pepper Smooth");
        typeService.update(waterType);


        //remove
        WaterTypeService typeService = new WaterTypeService();
        WaterType waterType = new WaterType();
        waterType.setWaterTypeId(3);
        typeService.remove(waterType);
*/

                                                 //USER
/*

        //create
        UserService userService = new UserService();
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
        UserService userService = new UserService();
        System.out.println(userService.getByLogin("logr@mail.ru"));

        //getAll
        UserService userService = new UserService();
        try {
            List<User> userList = userService.getAll();

            for (User a: userList){
                System.out.println(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //update
        UserService userService = new UserService();
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
    }
//}
