package me.faun.authentication.util;

public class ValidationUtils {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

    public static boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }


    public static String getPasswordRequirements() {
        return """
                Password must:
                - Be at least 8 characters long
                - Contain at least one digit
                - Contain at least one lowercase letter
                - Contain at least one uppercase letter
                - Contain at least one special character (@#$%^&+=)
                - No whitespace allowed
                """;
    }
}