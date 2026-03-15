package com.sn0w.suisei.api.app.usecase;

public interface OtpUsecase {
    void generateOtp(String username);
    void verifyOtp(String username, String code);
}
