package com.sn0w.suisei.api.adapter.outbound.database.jdbc.mapper;

import com.sn0w.suisei.api.adapter.outbound.database.jpa.entity.UserEntity;
import com.sn0w.suisei.api.core.domain.shared.Timestamp;
import com.sn0w.suisei.api.core.domain.user.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.reconstruct(
                rs.getString("id"),
                rs.getString("identifier"),
                rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                Timestamp.of(rs.getObject("created_at", OffsetDateTime.class),
                        rs.getObject("updated_at", OffsetDateTime.class))
        );
    }
}
