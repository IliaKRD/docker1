package com.example.Manager.repository;

import com.example.Manager.Entity.UserAuthority;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAuthorityRepository extends CrudRepository<UserAuthority, Integer> {
    Optional<UserAuthority> findByUsername(String username);
}
