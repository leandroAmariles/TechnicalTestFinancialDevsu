package com.devsu.customerservice;


import com.devsu.customerservice.application.mapper.ClientMapper;
import com.devsu.customerservice.domain.model.Client;
import com.devsu.customerservice.infraestructure.controllers.clients.queryCacheClient.IQueryCacheClient;
import com.devsu.customerservice.infraestructure.controllers.clients.queryClient.IQueryClient;
import com.devsu.customerservice.infraestructure.controllers.clients.queryClient.dto.CustomerResponse;
import com.devsu.customerservice.infraestructure.controllers.in.CreateCustomer;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerCreateResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

public class CustomerServiceTest {


    @Test
    void createCustomer_returnsResponse() {
        IQueryClient client = Mockito.mock(IQueryClient.class);
        IQueryCacheClient cacheClient = Mockito.mock(IQueryCacheClient.class);
        ClientMapper mapper = Mockito.mock(ClientMapper.class);

        CreateCustomer createCustomer = Mockito.mock(CreateCustomer.class);
        Client clientModel = new Client();
        CustomerResponse response = CustomerResponse.builder().build();
        CustomerCreateResponse customerCreateResponse = CustomerCreateResponse.builder().build();

        Mockito.when(mapper.dtoCreateToClientModel(any())).thenReturn(clientModel);
        Mockito.when(client.sendCreateCustomer(clientModel)).thenReturn(Mono.just(response));
        Mockito.when(mapper.dtoResponseClientToResponse(response)).thenReturn(customerCreateResponse);

    }
}
