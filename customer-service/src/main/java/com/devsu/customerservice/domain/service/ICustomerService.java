package com.devsu.customerservice.domain.service;

import com.devsu.customerservice.infraestructure.controllers.in.CreateCustomer;
import com.devsu.customerservice.infraestructure.controllers.in.CustomerUpdateRequest;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerCreateResponse;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerInfoResponse;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerUpdateResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService {


    Mono<CustomerCreateResponse> createCustomer(CreateCustomer createCustomer);

    Mono<CustomerInfoResponse> genCustomer(String id);

    Mono<CustomerUpdateResponse> updateCustomer(CustomerUpdateRequest customerUpdateRequest, String id);

    Mono<Void> deleteCustomer(String id);

    Flux<CustomerInfoResponse> getAllCustomers();
}
