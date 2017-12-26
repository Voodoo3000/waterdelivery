package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.entity.Entity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String language = request.getParameter(Entity.LOCALE);
        Locale locale = new Locale(language);
        LOGGER.info("Selected locale is :" + locale);
        session.setAttribute(Entity.LOCALE, locale);
        //return to last page with new locale
        String referer = request.getHeader(Entity.REFERER);
        //get page name only, without path
        referer = referer.substring(referer.lastIndexOf("/do") + 1, referer.length());
        return new CommandResult(referer, true);
    }
}
