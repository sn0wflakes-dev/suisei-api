package com.sn0w.suisei.api.core.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsernameTest {
    @Test
    public void usernameOnSuccess() {
        String expectedUsername = "_Sn0wFlakes-01";
        Username username = Username.of("_Sn0wFlakes-01");

        Assertions.assertEquals(expectedUsername, username.getValue());
    }

    @Test
    public void usernameOnFailWithNull() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Username.of(null);
        });

        String exception = "null";

        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

    @Test
    public void usernameOnFailWithEmpty() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Username.of(" ");
        });

        String exception = "empty";

        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

    @Test
    public void usernameOnFailWithSymbol() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Username.of("Sn*wFlakes");
        });

        String exception = "contain";

        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

    @Test
    public void usernameOnFailWithMinLen() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Username.of("Sn");
        });

        String exception = "between";

        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

    @Test
    public void usernameOnFailWithMaxLen() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Username.of("whispering_willows_weeping_under_the_silver_moon");
        });

        String exception = "between";

        Assertions.assertTrue(ex.getMessage().contains(exception));
    }
}
