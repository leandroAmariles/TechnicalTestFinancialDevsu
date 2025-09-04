package com.devsu.customerservice.infraestructure.controllers;

import com.devsu.customerservice.infraestructure.controllers.in.CreateCustomer;
import com.devsu.customerservice.infraestructure.controllers.in.CustomerUpdateRequest;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerCreateResponse;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerInfoResponse;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerUpdateResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerController {

    ResponseEntity<Mono<CustomerCreateResponse>> createCustomer(CreateCustomer createCustomer);

    ResponseEntity<Mono<CustomerInfoResponse>> getCustomer(String id);

    ResponseEntity<Mono<CustomerUpdateResponse>> updateCustomer(String id, CustomerUpdateRequest customerUpdateRequest);

    ResponseEntity<Mono<Void>> deleteCustomer(String id);

    ResponseEntity<Flux<CustomerInfoResponse>> getAllCustomers();
}
