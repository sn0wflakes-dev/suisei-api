package com.sn0w.suisei.api.core.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RawPasswordTest {
    @Test
    public void rawPasswordOnSuccess() {
        String expectHashPass = "P@ssw0rdRoOt123!";
        RawPassword rawPassword = RawPassword.of("P@ssw0rdRoOt123!");

        Assertions.assertEquals(expectHashPass, rawPassword.getValue());
    }

    @Test
    public void rawPasswordOnFailWithNullValue() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RawPassword.of(null);
        });

        String errMessage = "null";


        Assertions.assertTrue(ex.getMessage().contains(errMessage));
    }

    @Test
    public void rawPasswordOnFailWithEmptyValue() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RawPassword.of(" ");
        });

        String errMessage = "empty";

        Assertions.assertTrue(ex.getMessage().contains(errMessage));
    }

    @Test
    public void rawPasswordOnFailWith15Char() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RawPassword.of("P@ssw0rdRoOt123");
        });

        String errMessage = "long";

        Assertions.assertTrue(ex.getMessage().contains(errMessage));
    }

    @Test
    public void rawPasswordOnFailWithNoUpperCase() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RawPassword.of("p@ssw0rdroot123!");
        });

        String errMessage = "special";

        Assertions.assertTrue(ex.getMessage().contains(errMessage));
    }

    @Test
    public void rawPasswordOnFailWithNoLowerCase() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RawPassword.of("P@SSW0RDROOT123!");
        });

        String errMessage = "special";

        Assertions.assertTrue(ex.getMessage().contains(errMessage));
    }

    @Test
    public void rawPasswordOnFailWithNoSpecialChar() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RawPassword.of("Passw0rdRoOt123A");
        });

        String errMessage = "special";

        Assertions.assertTrue(ex.getMessage().contains(errMessage));
    }
}
