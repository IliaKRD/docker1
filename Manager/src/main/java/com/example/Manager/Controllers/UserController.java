package com.example.Manager.Controllers;

import com.example.Manager.Client.UsersRestClient;
import com.example.Manager.Entity.Payloads.UpdateUserPayload;
import com.example.Manager.Entity.User;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/{userId:\\d+}")
public class UserController {
    private final UsersRestClient usersRestClient;
    private final MessageSource messageSource;

    @ModelAttribute("user")
    public User user(@PathVariable("userId") int userId) {
    return (User) usersRestClient.findUserById(userId).orElseThrow(() -> new NoSuchElementException("errors.user.not_found"));
}

    @GetMapping()
    String findUser(){
        return "users/user";
    };

    @GetMapping("update")
    String updateUser(){
        return "users/update";
    }
//    @PostMapping("update")
//        String updateUser1(UserPayload payload, @ModelAttribute("user") User user){
//        userService.updateUser(user.getId(), payload);
//        return "redirect:/users/%d".formatted(user.getId());
//    }

    @PostMapping("update")
    String updateUser1(@ModelAttribute(value = "user") User user,
                       @Valid UpdateUserPayload payload,
                       BindingResult bindingResult,
                       Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "users/update";
        } else {
            usersRestClient.updateUser(user.getId(), payload);
            return "redirect:/users/%d".formatted((user.getId()));
        }
    }

    @GetMapping("delete")
    String deleteUser(@ModelAttribute("user") User user) {
        usersRestClient.deleteUser(user.getId());
        return "redirect:/users/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(
            NoSuchElementException exception,
            Model model,
            HttpServletResponse response,
            Locale locale) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error",
                messageSource.getMessage(exception.getMessage(),
                        new Object[0],
                        exception.getMessage(),
                        locale));
        return "Errors/404";
    }
}

