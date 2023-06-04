package org.example.service.ServiceImplements;

import org.example.entity.UserEntity;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserService {
    private List<UserEntity> userList = new ArrayList<>();

    @Override
    public List<UserEntity> getAllUsers() {
        return userList;
    }

    @Override
    public Optional<UserEntity> getUserById(long id) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return userList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        long nextId = getNextUserId();
        user.setId(nextId);
        userList.add(user);
        return user;
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        Optional<UserEntity> existingUser = getUserById(user.getId());
        if (existingUser.isPresent()) {
            UserEntity updatedUser = existingUser.get();
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setAddress(user.getAddress());
            updatedUser.setContact(user.getContact());
            updatedUser.setIdentityCardNumber(user.getIdentityCardNumber());
            return updatedUser;
        }
        return null;
    }

    @Override
    public void deleteUser(long id) {
        userList.removeIf(user -> user.getId() == id);
    }

    private long getNextUserId() {
        if (userList.isEmpty()) {
            return 1L;
        } else {
            long lastUserId = userList.get(userList.size() - 1).getId();
            return lastUserId + 1;
        }
    }
}
