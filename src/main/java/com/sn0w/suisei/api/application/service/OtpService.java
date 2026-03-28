package com.sn0w.suisei.api.application.service;

import com.sn0w.suisei.api.application.port.outbound.event.EventPublisher;
import com.sn0w.suisei.api.application.port.inbound.OtpUsecase;
import com.sn0w.suisei.api.core.domain.event.GenerateOtpEvent;
import com.sn0w.suisei.api.core.domain.event.UserRegisteredEvent;
import com.sn0w.suisei.api.core.domain.otp.Otp;
import com.sn0w.suisei.api.core.domain.user.User;
import com.sn0w.suisei.api.core.exception.otp.OtpExpire;
import com.sn0w.suisei.api.core.exception.otp.OtpNotMatch;
import com.sn0w.suisei.api.application.port.outbound.repository.UserRepository;
import com.sn0w.suisei.api.adapter.outbound.database.redis.repository.RedisRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class OtpService implements OtpUsecase {

    private static final Logger log = LogManager.getLogger(OtpService.class);

    private final RedisRepository redis;
    private final UserRepository userRepository;
    private final EventPublisher eventPublisher;

    public OtpService(
            RedisRepository redis,
            UserRepository userRepository,
            EventPublisher eventPublisher) {
        this.redis = redis;
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void generateOtp(String username) {
        try {

            User user = userRepository.findUserByUsername(username);

            Otp data = Otp.of(username);

            redis.deleteValue(username);

            redis.setValue(
                    data.getId().getUsername(),
                    data.getCode().getValue(),
                    data.getTimeToLive());

            eventPublisher.publish(GenerateOtpEvent.invoke(
                    user.getEmail().getValue(),
                    user.getName().getFullName(),
                    redis.getValue(username),
                    String.valueOf(300/60)
            ));

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
        try {

            User user = userRepository.findUserByUsername(username);

            String otp = redis.getValue(username);
            if (otp == null || otp.isBlank()) {
                throw new OtpExpire();
            }

            if (!otp.equals(code)) {
                throw new OtpNotMatch();
            }

            if (userRepository.verifyUserById(user.getUserId().getValue())) {
                eventPublisher.publish(UserRegisteredEvent.invoke(
                        user.getUsername().getValue(),
                        user.getName().getFirstName(),
                        user.getName().getLastName(),
                        user.getEmail().getValue(),
                        user.getTimestamp().getUpdatedAt()
                ));
            }

            redis.deleteValue(username);
        } catch (Exception e) {
            log.error("[ERROR:SERVICE] Failed to verify otp for : {}, error details : {}",
                    username,
                    e.getMessage());
        }
    }
}
