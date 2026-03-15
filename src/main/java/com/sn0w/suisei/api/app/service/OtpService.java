package com.sn0w.suisei.api.app.service;

import com.sn0w.suisei.api.app.usecase.EmailUsecase;
import com.sn0w.suisei.api.app.usecase.OtpUsecase;
import com.sn0w.suisei.api.core.domain.email.Email;
import com.sn0w.suisei.api.core.domain.otp.Otp;
import com.sn0w.suisei.api.core.domain.user.User;
import com.sn0w.suisei.api.core.exception.otp.OtpExpire;
import com.sn0w.suisei.api.core.exception.otp.OtpNotMatch;
import com.sn0w.suisei.api.core.exception.user.UserNotFound;
import com.sn0w.suisei.api.infra.database.jpa.entity.UserEntity;
import com.sn0w.suisei.api.infra.database.jpa.repository.UserJpaRepository;
import com.sn0w.suisei.api.infra.database.redis.repository.RedisRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class OtpService implements OtpUsecase {

    private static final Logger log = LogManager.getLogger(OtpService.class);

    private final RedisRepository redis;
    private final UserJpaRepository repository;
    private final EmailUsecase email;

    public OtpService(
            RedisRepository redis,
            UserJpaRepository repository,
            EmailUsecase email) {
        this.redis = redis;
        this.repository = repository;
        this.email = email;
    }

    @Override
    public void generateOtp(String username) {
        try {
            UserEntity user = repository.findByUsername(username).orElseThrow(
                    () -> new UserNotFound(username)
            );

            User reconstruct = User.reconstruct(
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhoneNumber()
            );

            Otp data = Otp.of(username);

            redis.deleteValue(username);
            redis.setValue(data.getId().getUsername(), data.getCode().getValue(), 300);
            
            email.send(Email.otpMail(
                            reconstruct.getEmail().getValue(),
                            reconstruct.getName().getFullName(),
                            redis.getValue(username),
                            String.valueOf(300/60)));

            log.debug(redis.getValue(username));

            log.info("[SUCCESS:SERVICE] Success to generate otp for : {}", username);
        } catch (Exception e) {
            log.error("[ERROR:SERVICE] Failed to generate otp for : {}, error details : {}",
                    username,
                    e.getMessage());
        }
    }

    @Override
    public void verifyOtp(String username, String code) {
        String otp = redis.getValue(username);
        if (otp == null || otp.isBlank()) {
            throw new OtpExpire();
        }

        if (!otp.equals(code)) {
            throw new OtpNotMatch();
        }
    }
}
