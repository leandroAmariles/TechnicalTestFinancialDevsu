package com.devsu.customerservice.application.services;

import com.devsu.customerservice.application.mapper.ClientMapper;
import com.devsu.customerservice.domain.service.ICustomerService;
import com.devsu.customerservice.infraestructure.controllers.clients.queryCacheClient.IQueryCacheClient;
import com.devsu.customerservice.infraestructure.controllers.clients.queryClient.IQueryClient;
import com.devsu.customerservice.infraestructure.controllers.in.CreateCustomer;
import com.devsu.customerservice.infraestructure.controllers.in.CustomerUpdateRequest;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerCreateResponse;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerInfoResponse;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final IQueryClient client;

    private final IQueryCacheClient iQueryCacheClient;

    private final ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);


    @Override
    public Mono<CustomerCreateResponse> createCustomer(CreateCustomer createCustomer) {
        return Mono.just(clientMapper.dtoCreateToClientModel(createCustomer))
                .flatMap(client::sendCreateCustomer)
                .map(clientMapper::dtoResponseClientToResponse);
    }

    @Override
    public Mono<CustomerInfoResponse> genCustomer(String id) {
        return iQueryCacheClient.getCustomerView(id)
                .map(clientMapper::dtoResponseCacheToResponse);
    }

    @Override
    public Mono<CustomerUpdateResponse> updateCustomer(CustomerUpdateRequest customerUpdateRequest, String id) {
        return Mono.just(clientMapper.dtoUpdateToClientModel(customerUpdateRequest))
                .flatMap(c ->client.sendUpdateCustomer(c, id))
                .map(clientMapper::dtoResponseClientToResponseUpdate);
    }

    @Override
    public Mono<Void> deleteCustomer(String id) {
        return client.sendDeleteCustomer(Long.valueOf(id)).then();
    }

    @Override
    public Flux<CustomerInfoResponse> getAllCustomers() {
        return iQueryCacheClient.getAllCustomerView()
                .map(clientMapper::dtoResponseCacheToResponse);
    }

}
