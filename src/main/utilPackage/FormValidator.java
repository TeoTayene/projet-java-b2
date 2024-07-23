package main.utilPackage;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidator {

    public static Boolean isValidEmail(String email) {
        // Expression régulière pour correspondre au format x@x.x
        String emailRegex = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";

        Pattern pattern = Pattern.compile(emailRegex);

        // Vérification de l'email par rapport au pattern
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static Boolean isNumberValid(String number) {
        // Expression régulière pour correspondre à un nombre
        String numberRegex = "^[0-9]+$";

        Pattern pattern = Pattern.compile(numberRegex);

        // Vérification du nombre par rapport au pattern
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();
    }

    public static Boolean validDateOfBirth(LocalDate dob) {
        LocalDate date1900 = LocalDate.of(1899, 12, 31);
        LocalDate dateToday = LocalDate.now().plusDays(1);

        return dob.isAfter(date1900) && dob.isBefore(dateToday);
    }

    public static Boolean stringContainsSpace(String str) {
        return str.length() != str.trim().length();
    }

    public static Boolean isOneStringEmpty(String... strings) {
        for (String string : strings) {
            if (string.trim().isEmpty()) {
                return true;
            }
        }

        return false;
    }

    public static Boolean validStringLength(String str, int min, int max) {
        return str.length() >= min && str.length() <= max;
    }

    public static Boolean validGender(char gender) {
        return (gender == 'm' || gender == 'w' || gender == 'o');
    }

    public static <T> Boolean isFieldNull(T field) {
        return field == null;
    }

    public static Boolean validId(Integer id) {
        return id > 0;
    }

    public static Boolean validBoolean(Boolean bool) {
        return bool != null;
    }
}
