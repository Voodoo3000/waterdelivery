package kz.epam.waterdelivery.command;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public interface Command{
    CommandResult execute(HttpServletRequest request) throws IOException;
}
