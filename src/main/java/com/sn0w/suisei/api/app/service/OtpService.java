package com.sn0w.suisei.api.app.service;

import com.sn0w.suisei.api.app.usecase.OtpUsecase;
import com.sn0w.suisei.api.core.domain.otp.Otp;
import com.sn0w.suisei.api.core.exception.otp.OtpExpire;
import com.sn0w.suisei.api.core.exception.otp.OtpNotMatch;
import com.sn0w.suisei.api.infra.database.redis.repository.RedisRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class OtpService implements OtpUsecase {

    private static final Logger log = LogManager.getLogger(OtpService.class);

    private final RedisRepository redis;

    public OtpService(RedisRepository redis) {
        this.redis = redis;
    }

    @Override
    public void generateOtp(String username) {
        try {
            String otp = redis.getValue(username);
            Otp data = Otp.of(username);

            if (otp == null || otp.isBlank()) {
                redis.setValue(username, data.getCode().getValue(), 300);

                log.info("[SUCCESS:SERVICE] Success to generate otp for : {}", username);
                log.debug(redis.getValue(username));
                return;
            }
            
            redis.deleteValue(username);
            redis.setValue(data.getId().getUsername(), data.getCode().getValue(), 300);

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
