package com.devsu.customerservice.infraestructure.controllers.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateCustomer(

        @NotNull(message = "Name is required")
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotNull(message = "gender is required")
        String gender,

        @NotNull(message = "age is required")
        int age,

        @NotNull(message = "gender is required")
        @NotBlank(message = "identification cannot be blank")
        String identification,

        String address,

        String phone,

        @NotNull(message = "password is required")
        @NotBlank(message = "password cannot be blank")
        String password

) {
}
