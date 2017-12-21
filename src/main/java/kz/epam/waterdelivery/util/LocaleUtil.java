package kz.epam.waterdelivery.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class LocaleUtil {

    private static final String ATTR_LOCALE = "locale";

    public static Locale getSessionLocale(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(ATTR_LOCALE);
        if (locale == null) {
            locale = request.getLocale();
        }
        return locale;
    }
}
