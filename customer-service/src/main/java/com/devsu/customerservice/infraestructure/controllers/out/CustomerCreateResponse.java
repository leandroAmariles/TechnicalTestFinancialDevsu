package com.devsu.customerservice.infraestructure.controllers.out;

import lombok.Builder;

@Builder
public record CustomerCreateResponse(

        Long personId,

        String name,

        String gender,

        int age,

        String identification,

        String address,

        String phone,

        String clientId,

        String password,

        String status
) {
}
