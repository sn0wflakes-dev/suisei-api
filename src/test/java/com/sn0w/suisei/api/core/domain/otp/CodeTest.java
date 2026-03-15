package com.sn0w.suisei.api.core.domain.otp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CodeTest {
    @Test
    public void codeOnSuccess() {
        String expectedId = "sn0wflakes";
        Otp otp = Otp.of("sn0wflakes");

        Assertions.assertEquals(expectedId, otp.getId().getUsername());
        Assertions.assertNotNull(otp.getCode().getValue());;
        System.out.println(otp.getCode().getValue());
    }
}
