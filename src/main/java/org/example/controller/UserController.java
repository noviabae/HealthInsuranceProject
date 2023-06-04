package org.example.controller;

import org.example.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private List<UserEntity> users = new ArrayList<>();

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity user) {
        long newId = users.size() + 1;
        user.setId(newId);
        users.add(user);
        return user;
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable long id) {
        Optional<UserEntity> user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
        return user.orElse(null);
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable long id, @RequestBody UserEntity updatedUser) {
        Optional<UserEntity> user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
        if (user.isPresent()) {
            UserEntity existingUser = user.get();
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setContact(updatedUser.getContact());
            existingUser.setIdentityCardNumber(updatedUser.getIdentityCardNumber());
            return existingUser;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable long id) {
        boolean isRemoved = users.removeIf(u -> u.getId() == id);
        return isRemoved;
    }

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return users;
    }

    @GetMapping("/search")
    public List<UserEntity> searchUsers(@RequestParam String keyword) {
        List<UserEntity> matchedUsers = users.stream()
                .filter(user -> user.getEmail().contains(keyword) ||
                        user.getAddress().contains(keyword) ||
                        user.getContact().contains(keyword) ||
                        user.getIdentityCardNumber().contains(keyword))
                .collect(Collectors.toList());
        return matchedUsers;
    }

}
