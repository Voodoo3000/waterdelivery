package kz.epam.waterdelivery.util;

import java.util.regex.Pattern;

public class Validator {

    private static final String EMAIL_REGEX = "[\\w\\u002E\\u005F]{0,20}@([a-zA-Z]+\\u002E){1,2}[a-zA-Z]{2,3}";
    private static final String PASSWORD_REGEX = "[\\w]{3,20}";

    public static boolean loginEmailValidator(String loginEmail){
        return Pattern.compile(EMAIL_REGEX).matcher(loginEmail).matches();
    }

    public static boolean passwordValidator(String password){
        return Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
    }

}
