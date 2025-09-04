package com.devsu.customerservice.infraestructure.controllers.clients.queryClient.dto;

import lombok.Builder;

@Builder
public record CustomerResponse(

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
    ){
}
