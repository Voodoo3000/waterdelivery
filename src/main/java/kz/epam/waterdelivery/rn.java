package kz.epam.waterdelivery;

import java.sql.SQLException;

public class rn {
    public static void main(String[] args) throws SQLException {

        //USER
/*
        //create
        UserDao userService = new UserDao();
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
        UserDao userService = new UserDao();
        System.out.println(userService.getByLogin("logr@mail.ru"));

        //getAll
        UserDao userService = new UserDao();
        try {
            List<User> userList = userService.getAll();

            for (User a: userList){
                System.out.println(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //update
        UserDao userService = new UserDao();
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
        CustomerOrderDao orderService = new CustomerOrderDao();
        CustomerOrder order = new CustomerOrder();
        order.setOrderId(2);
        order.setCustomerId(2);
        order.setOrderContentId(1);
        order.setAmount(1500);
        order.setAddress("8 microdistrict building 74");
        orderService.add(order);

        //getAll
        CustomerOrderDao orderService = new CustomerOrderDao();
        List<CustomerOrder> orderList = orderService.getAll();
        for (CustomerOrder a : orderList) {
            System.out.println(a);
        }

         //getById
        CustomerOrderDao orderService = new CustomerOrderDao();
        System.out.println(orderService.getById(2));

        //update
        CustomerOrderDao orderService = new CustomerOrderDao();
        CustomerOrder order = new CustomerOrder();
        order.setOrderId(2);
        order.setCustomerId(2);
        order.setOrderContentId(1);
        order.setAmount(1500);
        order.setAddress("6 microdistrict building 11");
        orderService.update(order);

        //remove
        CustomerOrderDao orderService = new CustomerOrderDao();
        CustomerOrder order = new CustomerOrder();
        order.setOrderId(3);
        orderService.remove(order);

*/

        //ORDERCONTENT
/*
        //create
        OrderContentDao contentService = new OrderContentDao();
        OrderContent content = new OrderContent();
        content.setOrderContentId(0);
        content.setWaterId(0);
        content.setBottleSize(3);
        content.setQuantity(2);
        contentService.add(content);

        //getAll
        OrderContentDao contentService = new OrderContentDao();
        List<OrderContent> contentList = contentService.getAll();
        for (OrderContent a : contentList) {
            System.out.println(a);
        }

        //getById
        OrderContentDao contentService = new OrderContentDao();
        System.out.println(contentService.getById(3));

        //update
        OrderContentDao contentService = new OrderContentDao();
        OrderContent content = new OrderContent();
        content.setOrderContentId(1);
        content.setWaterId(2);
        content.setBottleSize(4);
        content.setQuantity(7);
        contentService.update(content);

        //remove
        OrderContentDao contentService = new OrderContentDao();
        OrderContent content = new OrderContent();
        content.setOrderContentId(1);
        contentService.remove(content);

*/

        //BOTLESIZE
/*
        //create
        BottleSizeDao sizeService = new BottleSizeDao();
        BottleSize bottle = new BottleSize();
        bottle.setBottleId(5);
        bottle.setSize(25);
        sizeService.add(bottle);

        //getAll
        BottleSizeDao sizeService = new BottleSizeDao();
        List<BottleSize> bottleSizeList = sizeService.getAll();
        for (BottleSize a : bottleSizeList) {
            System.out.println(a);
        }

        //getById
        BottleSizeDao sizeService = new BottleSizeDao();
        System.out.println(sizeService.getById(5));

        //update
        BottleSizeDao sizeService = new BottleSizeDao();
        BottleSize bottle = new BottleSize();
        bottle.setBottleId(5);
        bottle.setSize(30);
        sizeService.update(bottle);

        //remove
        BottleSizeDao sizeService = new BottleSizeDao();
        BottleSize bottle = new BottleSize();
        bottle.setBottleId(5);
        sizeService.remove(bottle);

*/
        //WATER
/*
        //create
        WaterDao waterService = new WaterDao();
        Water water = new Water();
        water.setWaterId(3);
        water.setWaterTypeId(3);
        water.setPricePerLiter(500);
        waterService.add(water);

        //getAll
        WaterDao waterService = new WaterDao();
        try {
            List<Water> waterList = waterService.getAll();

            for (Water a: waterList){
                System.out.println(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //getById
        WaterDao waterService = new WaterDao();
        System.out.println(waterService.getById(0));

        //update
        WaterDao waterService = new WaterDao();
        Water water = new Water();
        water.setWaterId(3);
        water.setWaterTypeId(3);
        water.setPricePerLiter(900);
        waterService.update(water);

        //remove
        WaterDao waterService = new WaterDao();
        Water water = new Water();
        water.setWaterId(3);
        waterService.remove(water);

*/

        //WATERTYPE
/*
        //create
        WaterTypeDao typeService = new WaterTypeDao();
        WaterType waterType = new WaterType();
        waterType.setWaterTypeId(3);
        waterType.setType("Dr Pepper");
        typeService.add(waterType);

        //getAll
        WaterTypeDao typeService = new WaterTypeDao();
        List<WaterType> waterTypeList = typeService.getAll();
        for (WaterType a : waterTypeList){
            System.out.println(a);
        }

        //getById
        WaterTypeDao typeService = new WaterTypeDao();
        System.out.println(typeService.getById(3));

        //update
        WaterTypeDao typeService = new WaterTypeDao();
        WaterType waterType = new WaterType();
        waterType.setWaterTypeId(3);
        waterType.setType("Dr Pepper Smooth");
        typeService.update(waterType);


        //remove
        WaterTypeDao typeService = new WaterTypeDao();
        WaterType waterType = new WaterType();
        waterType.setWaterTypeId(3);
        typeService.remove(waterType);
*/
    }
}
