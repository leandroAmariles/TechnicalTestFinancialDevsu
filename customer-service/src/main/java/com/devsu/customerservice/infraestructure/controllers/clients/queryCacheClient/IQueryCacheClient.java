package com.devsu.customerservice.infraestructure.controllers.clients.queryCacheClient;

import com.devsu.customerservice.infraestructure.controllers.clients.queryCacheClient.dto.CustomerViewResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQueryCacheClient {

    Mono<CustomerViewResponse> getCustomerView(String customerId);

    Flux<CustomerViewResponse> getAllCustomerView();
}
