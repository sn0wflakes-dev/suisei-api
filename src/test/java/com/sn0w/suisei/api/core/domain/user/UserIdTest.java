package com.sn0w.suisei.api.core.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserIdTest {
    @Test
    public void userIdOnSuccess() {
        UserId userIdOne = UserId.generateId();
        UserId userIdTwo = UserId.generateId();

        Assertions.assertNotNull(userIdOne.getValue());
        Assertions.assertNotNull(userIdTwo.getValue());
        Assertions.assertNotEquals(userIdOne.getValue(), userIdTwo.getValue());
    }
}
