package com.sn0w.suisei.api.app.service;

import com.sn0w.suisei.api.app.usecase.UserUsecase;
import com.sn0w.suisei.api.core.domain.user.User;
import com.sn0w.suisei.api.core.exception.user.UserNotFound;
import com.sn0w.suisei.api.infra.database.jpa.entity.UserEntity;
import com.sn0w.suisei.api.infra.database.jpa.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserUsecase {

    private final UserJpaRepository userJpaRepository;

    public UserService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        UserEntity user =userJpaRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFound(username)
        );

        return User.reconstruct(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }
}
