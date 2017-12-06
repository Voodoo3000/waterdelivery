package kz.epam.waterdelivery.command;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface Command{
    CommandResult execute(HttpServletRequest request) throws CommandException;
}
