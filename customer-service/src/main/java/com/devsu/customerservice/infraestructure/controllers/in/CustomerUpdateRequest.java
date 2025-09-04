package com.devsu.customerservice.infraestructure.controllers.in;

public record CustomerUpdateRequest (

        String name,

        String age,

        String address,

        String phone
){
}
