package kz.epam.waterdelivery.command;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RefreshPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {
        String referer = request.getHeader("Referer");
        //get page name only, without path
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new CommandResult(referer, true);
    }
}
