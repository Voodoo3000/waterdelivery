package kz.epam.waterdelivery.command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final String MAIN_PAGE = "main";
    private static final String ADMIN_ORDERS = "admin_orders";
    private static final String ADMIN_ORDER_CONTENT = "admin_order_content";
    private static final String ADMIN_WATER = "admin_water";
    private static final String ADMIN_BOTTLE_SIZE = "admin_bottle_size";
    private static final String ERROR404_PAGE = "error404_page";
    private static final String ERROR500_PAGE = "error500_page";
    private final static Map<String, Command> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("GET/main", new OpenPageCommand(MAIN_PAGE));
        COMMANDS.put("GET/open_admin_page", new OpenAdminPageCommand(MAIN_PAGE));
        COMMANDS.put("GET/admin_orders", new OpenPageCommand(ADMIN_ORDERS));
        COMMANDS.put("GET/admin_content", new OpenPageCommand(ADMIN_ORDER_CONTENT));
        COMMANDS.put("GET/admin_water", new OpenPageCommand(ADMIN_WATER));
        COMMANDS.put("GET/admin_bottle", new OpenPageCommand(ADMIN_BOTTLE_SIZE));
        COMMANDS.put("GET/error404", new OpenPageCommand(ERROR404_PAGE));
        COMMANDS.put("GET/error500", new OpenPageCommand(ERROR500_PAGE));
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
        COMMANDS.put("POST/change_order" , new ChangeOrderCommand());
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
