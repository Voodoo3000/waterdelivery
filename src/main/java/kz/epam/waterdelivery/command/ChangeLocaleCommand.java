package kz.epam.waterdelivery.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    private static final String REFERER = "Referer";
    private static final String LOCALE = "locale";

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        String language = request.getParameter(LOCALE);
        Locale locale = new Locale(language);
        session.setAttribute(LOCALE, locale);
        //return to last page with new locale
        String referer = request.getHeader(REFERER);
        //get page name only, without path
        referer = referer.substring(referer.lastIndexOf("/do") + 1, referer.length());
        return new CommandResult(referer, true);
    }
}
