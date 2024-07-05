package com.example.Manager.Client;

import com.example.Manager.Entity.Payloads.UpdateUserPayload;
import com.example.Manager.Entity.User;


import java.util.List;
import java.util.Optional;

public interface UsersRestClient {
    List<User> findAllUsers();
    User createUser(String name, String email, int age);
    Optional <User> findUserById(int id);
    void updateUser(int id, UpdateUserPayload user);
    void deleteUser(int id);
}
