package com.mitrais.registration.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class PhoneValidatorTests {
    private PhoneValidator phoneValidator;
    @Before
    public void setUp() {
        phoneValidator = new PhoneValidator();
    }
    @Test
    public void testIsInvalid() {
        final String phone = "039999882277";
        final ConstraintValidatorContext cxt = null;
        final boolean expectedResult = false;

        // Run the test
        final boolean result = phoneValidator.isValid(phone, cxt);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testValidPhone() {
        final String phone = "0399998826";
        final ConstraintValidatorContext cxt = null;
        final boolean expectedResult = true;

        // Run the test
        final boolean result = phoneValidator.isValid(phone, cxt);

        // Verify the results
        assertEquals(expectedResult, result);
    }

}
