package kz.epam.waterdelivery.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final String MAIN_PAGE = "main";
    private static final String MAIN_PAGE_AUTHORIZED = "main_authorized";
    private final static Map<String, Command> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("GET/main", new OpenPageCommand(MAIN_PAGE));
        COMMANDS.put("GET/authorized", new OpenPageCommand(MAIN_PAGE_AUTHORIZED));
    }
    public static Command get(String commandName) {
        return COMMANDS.get(commandName);
    }
}
