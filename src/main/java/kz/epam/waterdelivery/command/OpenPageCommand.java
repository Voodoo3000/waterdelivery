package kz.epam.waterdelivery.command;

import javax.servlet.http.HttpServletRequest;

public class OpenPageCommand implements Command {

    private CommandResult result;

    public OpenPageCommand(String page) {
        result = new CommandResult(page);
    }
    @Override
    public CommandResult execute(HttpServletRequest req) {
               return result;
    }
}
