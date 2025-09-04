package com.devsu.customerservice.infraestructure.controllers.out;

public record CustomerInfoResponse(

        Long personId,

        String name,

        String identification,

        String clientId,

        String status,

        String address,

        String phone
) {
}
