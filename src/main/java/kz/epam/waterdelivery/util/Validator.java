package kz.epam.waterdelivery.util;

import java.util.regex.Pattern;

public class Validator {

    private static final String EMAIL_REGEX = "[\\w\\u002E\\u005F]{0,20}@([a-zA-Z]+\\u002E){1,2}[a-zA-Z]{2,3}";
    private static final String PASSWORD_REGEX = "[\\w]{3,20}";
    private static final String NAMES_REGEX = "([A-Z]{1}[a-z]{0,19})|([\\u0410-\\u042F]{1}[\\u0430-\\u044F]{0,19})";

    public static boolean validateLoginEmail(String loginEmail) {
        return Pattern.compile(EMAIL_REGEX).matcher(loginEmail).matches();
    }

    public static boolean validatePassword(String password) {
        return Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
    }

    public static boolean validateNames(String names) {
        return Pattern.compile(NAMES_REGEX).matcher(names).matches();
    }
}
