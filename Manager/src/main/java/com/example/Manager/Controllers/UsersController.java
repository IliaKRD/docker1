package com.example.Manager.Controllers;

import com.example.Manager.Client.UsersRestClient;
import com.example.Manager.Entity.Payloads.UserPayload;
import com.example.Manager.Entity.User;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private final UsersRestClient usersRestClient;
//    @GetMapping("/users")
//    List<User> getUsers(){
//        return userService.findUsers();
//    }

    @GetMapping("list")
    String getUsers(Model model){
        model.addAttribute("users",usersRestClient.findAllUsers());
        return "users/list";
    }


    @GetMapping("new")
    String getNewUser(){
            return "users/new";
    }

    @PostMapping("new")
    String createUser(@Valid UserPayload userPayload, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", userPayload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList());
            return "users/new";
        } else {
            User user = usersRestClient.createUser(userPayload.name(), userPayload.email(), userPayload.age());
//        userService.saveUser(user);
            return "redirect:/users/%d".formatted((user.getId()));
        }
    }



}
