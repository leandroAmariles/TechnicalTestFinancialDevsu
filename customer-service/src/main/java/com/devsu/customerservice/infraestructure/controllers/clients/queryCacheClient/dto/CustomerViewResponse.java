package com.devsu.customerservice.infraestructure.controllers.clients.queryCacheClient.dto;

public record CustomerViewResponse (

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
