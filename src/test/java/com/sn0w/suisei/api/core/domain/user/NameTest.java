package com.sn0w.suisei.api.core.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void nameOnSuccess() {
        String firstName = "Abdurrahman";
        String lastName = "Ali";
        String fullName = firstName+ " " +lastName;

        Name name = Name.of(firstName, lastName);

        Assertions.assertEquals(firstName, name.getFirstName());
        Assertions.assertEquals(lastName, name.getLastName());
        Assertions.assertEquals(fullName, name.getFullName());
    }

    @Test
    public void nameOnFailFirstNameNull() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String firstName = "Abdurrahman";
            Name.of(firstName, null);
        });

        String exception = "null";
        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

    @Test
    public void nameOnFailLastNameNull() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String lastName = "Ali";
            Name.of(null, lastName);
        });

        String exception = "null";
        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

    @Test
    public void nameOnFailFirstNameEmpty() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String firstName = "Abdurrahman";
            Name.of(firstName, " ");
        });

        String exception = "empty";
        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

    @Test
    public void nameOnFailLastNameEmpty() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String lastName = "Ali";
            Name.of(" ", lastName);
        });

        String exception = "empty";
        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

    @Test
    public void nameOnFailLastNameWithSymbol() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String firstName = "Abdurrahman*";
            String lastName = "Ali";
            Name.of(firstName, lastName);
        });

        String exception = "letters";
        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

    @Test
    public void nameOnFailLastNameWithNumber() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String firstName = "Abdurrahman";
            String lastName = "Al1";
            Name.of(firstName, lastName);
        });

        String exception = "letters";
        Assertions.assertTrue(ex.getMessage().contains(exception));
    }

}
