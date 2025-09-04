package com.devsu.customerservice.infraestructure.controllers.clients.queryClient;


import com.devsu.customerservice.domain.model.Client;
import com.devsu.customerservice.infraestructure.controllers.clients.queryClient.dto.CustomerResponse;
import reactor.core.publisher.Mono;


public interface IQueryClient {


    Mono<CustomerResponse> sendCreateCustomer(Client client);

    Mono<CustomerResponse> sendUpdateCustomer(Client client, String identification);

    Mono<CustomerResponse> sendDeleteCustomer(Long clientId);
}
