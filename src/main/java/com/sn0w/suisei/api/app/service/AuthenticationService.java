package com.sn0w.suisei.api.app.service;

import com.sn0w.suisei.api.app.usecase.AuthenticationUsecase;
import com.sn0w.suisei.api.core.domain.user.User;
import com.sn0w.suisei.api.core.exception.user.EmailAlreadyExist;
import com.sn0w.suisei.api.core.exception.user.InvalidCredential;
import com.sn0w.suisei.api.core.exception.user.PhoneNumberAlreadyExist;
import com.sn0w.suisei.api.core.exception.user.UsernameAlreadyExist;
import com.sn0w.suisei.api.core.repository.UserRepository;
import com.sn0w.suisei.api.infra.database.jpa.entity.UserEntity;
import com.sn0w.suisei.api.infra.database.jpa.repository.UserJpaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService implements AuthenticationUsecase {

    private static final Logger log = LogManager.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(
            UserRepository userRepository,
            UserJpaRepository userJpaRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(User user) {
        try {

            String bcryptHash = passwordEncoder.encode(user.getPassword().getValue());

            User reconstructedUser = User.reconstruct(
                    user.getUserId().getValue(),
                    user.getUsername().getValue(),
                    bcryptHash,
                    user.getName().getFirstName(),
                    user.getName().getLastName(),
                    user.getEmail().getValue(),
                    user.getPhoneNumber().getValue()
            );

            // Check identifier
            userJpaRepository.findByUsername(user.getUsername().getValue()).ifPresent(
                    data -> {
                        throw new UsernameAlreadyExist(user.getUsername().getValue());
                    }
            );

            // Check email
            userJpaRepository.findByEmail(user.getEmail().getValue()).ifPresent(
                    data-> {
                       throw new EmailAlreadyExist(user.getEmail().getValue());
                    }
            );

            // Check phone number
            userJpaRepository.findByPhoneNumber(user.getPhoneNumber().getValue()).ifPresent(
                    data -> {
                        throw new PhoneNumberAlreadyExist(user.getPhoneNumber().getValue());
                    }
            );

            userRepository.addUser(reconstructedUser);

            log.info("[SUCCESS:SERVICE] Success to add user with userId : {}", user.getUserId().getValue());
        } catch (Exception e) {
            log.error("[ERROR:SERVICE] Failed to add user with userId : {}. Error details : {}",
                    user.getUserId().getValue(),
                    e.getMessage());
            throw e;
        }
    }

    @Override
    public User login(String identifier, String password) {

        try {
            String hashedPassword = passwordEncoder.encode(password);

            if (identifier.contains("@")) {
                UserEntity user = userJpaRepository.findByEmail(identifier).orElseThrow(
                        () -> new InvalidCredential()
                );

                if (!Objects.equals(hashedPassword, user.getPassword())) {
                    throw new InvalidCredential();
                }

            }

            UserEntity user = userJpaRepository.findByUsername(identifier).orElseThrow(
                    () -> new InvalidCredential()
            );

            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new InvalidCredential();
            }

            log.info("[SUCCESS:SERVICE] Success to authenticate user with identifier : {}", identifier);

            return User.reconstruct(
                    user.getId(),
                    identifier,
                    hashedPassword,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhoneNumber()
            );
        } catch (Exception e) {
            log.error("[ERROR:SERVICE] Failed to authenticate user with identifier : {}. Error details : {}",
                    identifier,
                    e.getMessage());
            throw e;
        }
    }
}
