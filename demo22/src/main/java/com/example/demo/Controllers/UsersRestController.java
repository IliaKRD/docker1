package com.example.demo.Controllers;

import com.example.demo.Entity.Payloads.UserPayload;
import com.example.demo.Entity.User;
import com.example.demo.Services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users-api/users")
@AllArgsConstructor
public class UsersRestController {

    UserService userService;

    @GetMapping()
    Iterable<User> getUsers(){
        return userService.findUsers();
    }


    @PostMapping()
    ResponseEntity<?> createUser(@Valid @RequestBody UserPayload userPayload, BindingResult bindingResult, UriComponentsBuilder uriBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        } else {
            User user = userService.saveUser(userPayload.name(), userPayload.email(), userPayload.age());
            return ResponseEntity.created(uriBuilder
                    .replacePath("/users-api/user/{userId}")
                    .build(Map.of("userId",user.getId())))
                    .body(user);
        }
    }



}
