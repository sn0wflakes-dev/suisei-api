package com.sn0w.suisei.api.core.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailTest {
    @Test
    public void emailOnSuccess() {
        String expectedEmail = "example@gmail.com";
        Email email = Email.of(expectedEmail);

        Assertions.assertEquals(expectedEmail, email.getValue());
    }

    @Test
    public void emailOnFailWithNullValue() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Email.of(null);
        });

        String errMessage = "null";

        Assertions.assertTrue(ex.getMessage().contains(errMessage));
    }

    @Test
    public void emailOnFailWithEmptyStringValue() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Email.of("");
        });

        String errMessage = "empty";

        Assertions.assertTrue(ex.getMessage().contains(errMessage));
    }

    @Test
    public void emailOnFailWithInvalidFormat() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Email.of("example&gmail.com");
        });

        String errMessage = "format";

        Assertions.assertTrue(ex.getMessage().contains(errMessage));
    }
}
