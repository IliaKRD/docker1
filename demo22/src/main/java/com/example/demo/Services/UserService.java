package com.example.demo.Services;

import com.example.demo.Entity.Payloads.UpdateUserPayload;
import com.example.demo.Entity.User;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;


public interface UserService {
    Optional<User> findUser(int id);



    void updateUser(int id, @Valid UpdateUserPayload user);
void deleteUser(int id);

    User saveUser(String name, String email, int age);

    Iterable<User> findUsers();
}