package service.validation;

import java.util.regex.Pattern;

public class RegisterValidation {
    public RegisterValidation() {
    }

    public static boolean validEmail(String email)
    {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }
    public static boolean validPassword(String password)
    {
        String regexPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return Pattern.compile(regexPattern)
                .matcher(password)
                .matches();
    }
}
