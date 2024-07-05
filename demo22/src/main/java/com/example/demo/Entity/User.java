package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(schema = "list", name = "t_users")

@NamedQueries(
        {
        @NamedQuery(
                name = "User.FindUser",
                query = "SELECT u FROM User u WHERE u.name like :name"
        ),

        @NamedQuery(
        name = "User.FindUser1",
        query = "SELECT u FROM User u WHERE u.age BETWEEN :min AND :max"
)}
)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "c_name", nullable = false)
    String name;
    @Column(name = "c_email")
    String email;
    @Column(name = "c_age")
    Integer age;
}
