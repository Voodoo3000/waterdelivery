package kz.epam.waterdelivery.util;

import kz.epam.waterdelivery.entity.Entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class LocaleUtil {

    public static Locale getSessionLocale(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Entity.LOCALE);
        if (locale == null) {
            locale = request.getLocale();
        }
        return locale;
    }
}
