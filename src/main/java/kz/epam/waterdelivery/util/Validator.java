package kz.epam.waterdelivery.util;

import kz.epam.waterdelivery.entity.Entity;
import java.util.regex.Pattern;

public class Validator {

    private Validator() {
    }

    public static boolean validateLoginEmail(String loginEmail) {
        return Pattern.compile(Entity.EMAIL_REGEX).matcher(loginEmail).matches();
    }

    public static boolean validatePassword(String password) {
        return Pattern.compile(Entity.PASSWORD_REGEX).matcher(password).matches();
    }

    public static boolean validateNames(String names) {
        return Pattern.compile(Entity.NAMES_REGEX).matcher(names).matches();
    }
}
