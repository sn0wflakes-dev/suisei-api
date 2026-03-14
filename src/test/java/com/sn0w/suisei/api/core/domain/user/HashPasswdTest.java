package com.sn0w.suisei.api.core.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashPasswdTest {
    @Test
    public void emailOnSuccess() {
        String expectHashPass = "secret";
        Password password = Password.of("secret");

        Assertions.assertEquals(expectHashPass, password.getValue());
    }

    @Test
    public void emailOnFailWithNullValue() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Password.of(null);
        });

        String errMessage = "null";


        Assertions.assertTrue(ex.getMessage().contains(errMessage));
    }

    @Test
    public void emailOnFailWithEmptyValue() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Password.of(" ");
        });

        String errMessage = "empty";

        Assertions.assertTrue(ex.getMessage().contains(errMessage));
    }
}
