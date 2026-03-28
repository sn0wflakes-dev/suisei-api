package com.sn0w.suisei.api.adapter.outbound.database.jdbc.mapper;

import com.sn0w.suisei.api.adapter.outbound.database.jpa.entity.UserEntity;
import com.sn0w.suisei.api.core.domain.shared.Timestamp;
import com.sn0w.suisei.api.core.domain.user.User;

public class UserMapper {
    public static User toDomain(UserEntity userEntity) {
        Timestamp timestamp = Timestamp.of(userEntity.getCreatedAt(), userEntity.getUpdatedAt());

        return User.reconstruct(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPhoneNumber(),
                timestamp);
    }
}
