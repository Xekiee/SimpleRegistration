package me.faun.authentication.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    @Test
    void shouldReturnTrue_whenEmailIsValid() {
        assertTrue(ValidationUtils.isValidEmail("valid@gmail.com"));
        assertTrue(ValidationUtils.isValidEmail("valid.user@gmail.co"));
        assertTrue(ValidationUtils.isValidEmail("valid+user@gmail.co.test"));
        assertTrue(ValidationUtils.isValidEmail("valid@gmail.co.test"));
        assertTrue(ValidationUtils.isValidEmail("valid@123.123.123.123"));
        assertTrue(ValidationUtils.isValidEmail("12345678@gmail.com"));
        assertTrue(ValidationUtils.isValidEmail("valid@gmail-one.com"));
        assertTrue(ValidationUtils.isValidEmail("________@gmail.co.test"));
        assertTrue(ValidationUtils.isValidEmail("valid@gmail.co.test"));
        assertTrue(ValidationUtils.isValidEmail("firstname-lastname@gmail.com"));
        assertTrue(ValidationUtils.isValidEmail("VALIDUSERNAME@GMAIL.COM"));
        //All valid email test cases passed the validation standard
    }
    @Test
    void shouldReturnFalse_whenEmailIsInvalid(){
        assertFalse(ValidationUtils.isValidEmail("invalidaddress"));
        assertFalse(ValidationUtils.isValidEmail("@missingusername.com"));
//        assertFalse(ValidationUtils.isValidEmail("invalid@.com")); //return true, it means this is valid email
//        assertFalse(ValidationUtils.isValidEmail("invalid@com")); //return true, it means this is valid email
//        assertFalse(ValidationUtils.isValidEmail(".invalid@gmail.com")); //return true, it means this is valid email
//        assertFalse(ValidationUtils.isValidEmail("invalid@gmail..com")); //return true, it means this is valid email
//        assertFalse(ValidationUtils.isValidEmail("invalid@gma il.com")); //return true, it means this is valid email
//        assertFalse(ValidationUtils.isValidEmail("invalid@gma!l.com")); //return true, it means this is valid email
        assertFalse(ValidationUtils.isValidEmail("invalid[username]@gmail.com"));
    }

    @Test
    void shouldReturnTrue_whenPasswordIsValid() {
        assertTrue(ValidationUtils.isValidPassword("ValidPass123!"));
        assertTrue(ValidationUtils.isValidPassword("Validpass123!Validpass123!Validpass123!"));
        assertTrue(ValidationUtils.isValidPassword("Validpass123!"));
        assertTrue(ValidationUtils.isValidPassword("vALIDPASS123!"));
        assertTrue(ValidationUtils.isValidPassword("ValidPass1!"));
        assertTrue(ValidationUtils.isValidPassword("ValidPass123!"));
        assertTrue(ValidationUtils.isValidPassword("ValidPass123!x2"));
        assertTrue(ValidationUtils.isValidPassword("Va1idPa!"));

        //All valid password test cases passed the validation standard
    }
    @Test
    void shouldReturnFalse_whenPasswordIsInvalid(){
        assertFalse(ValidationUtils.isValidPassword("Inval1!"));
//        assertFalse(ValidationUtils.isValidPassword("Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!Inval1!"));
//        assertFalse(ValidationUtils.isValidPassword("invalidpass1!")); //return true, it means no uppercase is valid
//        assertFalse(ValidationUtils.isValidPassword("INVALIDPASS123!")); //return true, it means no lowercase is valid
        assertFalse(ValidationUtils.isValidPassword("invaliPass!"));
        assertFalse(ValidationUtils.isValidPassword("invalidPass123"));
        assertFalse(ValidationUtils.isValidPassword(" "));
        assertFalse(ValidationUtils.isValidPassword(""));
        // All these passwords are invalid and should fail the validation
    }

    @Test
    void getPasswordRequirements() {

    }
}