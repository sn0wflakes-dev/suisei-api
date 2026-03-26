package com.sn0w.suisei.api.core.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void userOnSuccess() {
        String username = "Sn0wflakes";
        String hashPassword = "secret";
        String firstName = "Abdurrahman";
        String lastName = "Ali";
        String fullName = firstName + " " + lastName;
        String phoneNumber = "087777102284";
        String email = "sn0w@gmail.com";

        User user = User.of(
                "Sn0wflakes",
                "secret",
                "Abdurrahman",
                "Ali",
                "sn0w@gmail.com",
                "087777102284"
        );

        // ensure not null
        Assertions.assertNotNull(user.getUserId().getValue());
        Assertions.assertNotNull(user.getUsername().getValue());
        Assertions.assertNotNull(user.getPassword().getValue());
        Assertions.assertNotNull(user.getName().getFullName());
        Assertions.assertNotNull(user.getPhoneNumber().getValue());
        Assertions.assertNotNull(user.getEmail().getValue());

        // ensure same with expected value
        Assertions.assertEquals(username, user.getUsername().getValue());
        Assertions.assertEquals(hashPassword, user.getPassword().getValue());
        Assertions.assertEquals(firstName, user.getName().getFirstName());
        Assertions.assertEquals(lastName, user.getName().getLastName());
        Assertions.assertEquals(fullName, user.getName().getFullName());
        Assertions.assertEquals(phoneNumber, user.getPhoneNumber().getValue());
        Assertions.assertEquals(email, user.getEmail().getValue());
    }
}
