package com.sn0w.suisei.api.application.port.inbound;

public interface OtpUsecase {
    void generateOtp(String username);
    void verifyOtp(String username, String code);
}
