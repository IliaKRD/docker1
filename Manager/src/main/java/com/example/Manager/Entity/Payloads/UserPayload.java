package com.example.Manager.Entity.Payloads;

import jakarta.validation.constraints.*;

public record UserPayload (
        @NotNull(message = "{errors.create.name_null}")
                @Size(min=2, max=50, message = "{errors.create.name_null}")
        String name,
                @NotEmpty(message = "Email is null")
                @Email(message = "{errors.create.email_null}")
                @Size(min = 5, max = 100, message =  "Email must be between 5 and 50 characters")
        String email,
        @NotNull(message = "Age id null")

                @Min(value = 18, message = "Age must be greater than 18")
        Integer age){
}
