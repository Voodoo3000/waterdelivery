package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private final static Map<String, Command> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("GET/main", new OpenPageCommand(Entity.MAIN_PAGE));
        COMMANDS.put("GET/open_admin_page", new OpenAdminPageCommand(Entity.MAIN_PAGE));
        COMMANDS.put("GET/admin_orders", new OpenPageCommand(Entity.ADMIN_ORDERS_PAGE));
        COMMANDS.put("GET/admin_water", new OpenPageCommand(Entity.ADMIN_WATER_PAGE));
        COMMANDS.put("GET/admin_bottle", new OpenPageCommand(Entity.ADMIN_BOTTLE_SIZE_PAGE));
        COMMANDS.put("GET/error", new OpenPageCommand(Entity.ERROR_PAGE));
        COMMANDS.put("GET/sign_out", new SignOutCommand());
        COMMANDS.put("GET/locale", new ChangeLocaleCommand());
        COMMANDS.put("GET/add_to_cart", new AddToCartCommand());
        COMMANDS.put("GET/open_cabinet", new OpenCabinetCommand());
        COMMANDS.put("GET/open_customer_cart", new OpenCartCommand());

        COMMANDS.put("POST/order", new GetOrderCommand());
        COMMANDS.put("POST/sign_up", new SignUpCommand());
        COMMANDS.put("POST/sign_in", new SignInCommand());
        COMMANDS.put("POST/edit_user", new EditUserCommand());
        COMMANDS.put("POST/remove_content", new RemoveContentCommand());
        COMMANDS.put("POST/cancel_order", new CancelOrderCommand());
        COMMANDS.put("POST/change_order", new ChangeOrderCommand());
        COMMANDS.put("POST/change_user", new ChangeUserCommand());
        COMMANDS.put("POST/change_water", new ChangeWaterCommand());
        COMMANDS.put("POST/create_water", new CreateWaterCommand());
        COMMANDS.put("POST/change_bottle_size", new ChangeBottleSizeCommand());
        COMMANDS.put("POST/create_size", new CreateBottleSizeCommand());
    }

    public static Command get(String commandName) {
        return COMMANDS.get(commandName);
    }
}
