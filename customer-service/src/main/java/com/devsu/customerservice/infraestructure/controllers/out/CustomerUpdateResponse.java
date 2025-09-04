package com.devsu.customerservice.infraestructure.controllers.out;

public record CustomerUpdateResponse(

        String name,

        String age,

        String address,

        String phone
) {
}
