package com.sn0w.suisei.api.core.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PhoneNumberTest {
    @Test
    public void phoneNumberOnSuccess() {
        String expectedPhoneNumber = "082125825405";
        PhoneNumber phoneNumber = PhoneNumber.of(expectedPhoneNumber);

        Assertions.assertEquals(expectedPhoneNumber, phoneNumber.getValue());
    }

    @Test
    public void phoneNumberOnFailNull() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           PhoneNumber.of(null);
        });

        String exception = "null";
        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

    @Test
    public void phoneNumberOnFailEmpty() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PhoneNumber.of(" ");
        });

        String exception = "empty";
        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

    @Test
    public void phoneNumberOnFailWithLetters() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PhoneNumber.of("08777710228A");
        });

        String exception = "digit";
        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

    @Test
    public void phoneNumberOnFailWithSymbol() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PhoneNumber.of("087777102284$");
        });

        String exception = "digit";
        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

    @Test
    public void phoneNumberOnFailWithMinLen() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PhoneNumber.of("08");
        });

        String exception = "must be";
        Assertions.assertTrue(ex.getMessage().contains(exception));
    }


    @Test
    public void phoneNumberOnFailWithMaxLen() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PhoneNumber.of("0877771022840293021021");
        });

        String exception = "must be";
        Assertions.assertTrue(ex.getMessage().contains(exception));
    }
}
