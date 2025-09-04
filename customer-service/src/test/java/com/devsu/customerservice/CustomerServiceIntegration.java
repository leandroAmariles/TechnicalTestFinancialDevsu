package com.devsu.customerservice;

import com.devsu.customerservice.application.services.CustomerServiceImpl;
import com.devsu.customerservice.infraestructure.controllers.in.CreateCustomer;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerCreateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerServiceIntegration {


    @Autowired
    private CustomerServiceImpl customerService;

    @Test
    void createCustomer_integration() {
        CreateCustomer createCustomer = CreateCustomer.builder().build();
        Mono<CustomerCreateResponse> responseMono = customerService.createCustomer(createCustomer);
        CustomerCreateResponse response = responseMono.block();
        assertNotNull(response);

    }
}
