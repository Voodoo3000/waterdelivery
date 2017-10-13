package kz.epam.waterdelivery.command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final String MAIN_PAGE = "main";
    private static final String MAIN_PAGE_AUTHORIZED = "main_authorized";
    private static final String CABINET = "cabinet";
    private static final String CUSTOMER_CART = "customer_cart";
    private final static Map<String, Command> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("GET/main", new OpenPageCommand(MAIN_PAGE));
        COMMANDS.put("GET/authorized", new OpenPageCommand(MAIN_PAGE_AUTHORIZED));
        COMMANDS.put("GET/cabinet", new OpenPageCommand(CABINET));
        COMMANDS.put("POST/signup", new SignUpCommand());
        COMMANDS.put("POST/signin", new SignInCommand());
        COMMANDS.put("POST/edit_user", new EditUserCommand());
        COMMANDS.put("GET/signout", new SignOutCommand());
        COMMANDS.put("GET/locale", new ChangeLocaleCommand());
        COMMANDS.put("GET/refresh", new RefreshPageCommand());
        COMMANDS.put("GET/add_to_cart", new AddToCartCommand());
        COMMANDS.put("GET/open_customer_cart", new OpenPageCommand(CUSTOMER_CART));
    }

    public static Command get(String commandName) {
        return COMMANDS.get(commandName);
    }
}
