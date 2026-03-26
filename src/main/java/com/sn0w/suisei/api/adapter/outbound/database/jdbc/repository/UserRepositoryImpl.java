package com.sn0w.suisei.api.adapter.outbound.database.jdbc.repository;

import com.sn0w.suisei.api.core.domain.user.User;
import com.sn0w.suisei.api.application.port.outbound.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Logger log = LogManager.getLogger(UserRepositoryImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepositoryImpl(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO users (id, username, password, first_name, last_name, email, phone_number) " +
                    "VALUES (:userId, :identifier, :hashedPassword, :firstName, :lastName, :email, :phoneNumber)";

            MapSqlParameterSource param = new MapSqlParameterSource()
                    .addValue("userId", user.getUserId().getValue())
                    .addValue("identifier", user.getUsername().getValue())
                    .addValue("hashedPassword", user.getPassword().getValue())
                    .addValue("firstName", user.getName().getFirstName())
                    .addValue("lastName", user.getName().getLastName())
                    .addValue("email", user.getEmail().getValue())
                    .addValue("phoneNumber", user.getPhoneNumber().getValue());

            namedParameterJdbcTemplate.update(sql, param);

            log.debug("[SUCCESS:DAO] Success to add user with userId : {}", user.getUserId().getValue());
        } catch (Exception e) {
            log.error("[ERROR:DAO] Failed to add user with userId : {}. Error details : {}",
                    user.getUserId().getValue(),
                    e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean verifyUserById(String userId) {
        try {
            String sql = "UPDATE users " +
                    "SET verified = true, updated_at = :updatedAt " +
                    "WHERE id = :userId";

            MapSqlParameterSource param = new MapSqlParameterSource()
                    .addValue("userId", userId)
                    .addValue("updatedAt", OffsetDateTime.now());

            int rowAffected = namedParameterJdbcTemplate.update(sql, param);
            return rowAffected > 0;
        } catch (Exception e) {
            log.error("[ERROR:DAO] Failed to verify with userId : {}. Error details : {}",
                    userId,
                    e.getMessage());
            throw e;
        }
    }

    @Override
    public User deleteUserById(String userId) {
        return null;
    }
}
