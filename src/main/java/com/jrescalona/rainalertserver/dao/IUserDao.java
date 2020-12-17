package com.jrescalona.rainalertserver.dao;

import com.jrescalona.rainalertserver.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserDao {
    /**
     * Creates a random UUID
     * then inserts new user using UUID
     * Invokes overloaded method
     * @param user User
     * @return 0 if successful, 1 otherwise
     */
    default int insertUser(User user) {
        UUID userId = UUID.randomUUID();
        return insertUser(userId, user);
    };

    int insertUser(UUID id, User user);
    Optional<User> selectUserById(UUID id);
    List<User> selectAllUsers();
    int updateUserById(UUID id, User user);
    int deleteUserById(UUID id);
}
