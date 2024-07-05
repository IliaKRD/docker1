package com.example.demo.Services.Impl;

import com.example.demo.Entity.Payloads.UpdateUserPayload;
import com.example.demo.Entity.User;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository repository;
    @Override
    public Optional<User> findUser(int id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<User> findUsers() {
        return null;
    }

    @Override
    public User saveUser(String name, String email, int age) {
        return repository.save(new User(null, name, email, age));
    }

    @Override
    public void updateUser(int id, @Valid UpdateUserPayload payload) {
        repository.findById(id).ifPresent(user-> {
            user.setAge(payload.age());
            user.setEmail(payload.email());
            user.setName(payload.name());
                });
    }

    @Override
    public void deleteUser(int id) {
        repository.deleteById(id);
    }
}
