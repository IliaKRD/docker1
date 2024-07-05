package com.example.Manager.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
    Integer id;
    String name;
    String email;
    Integer age;
}
