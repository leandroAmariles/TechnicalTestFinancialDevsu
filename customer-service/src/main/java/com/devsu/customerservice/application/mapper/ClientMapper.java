package com.devsu.customerservice.application.mapper;

import com.devsu.customerservice.domain.model.Client;
import com.devsu.customerservice.infraestructure.controllers.clients.queryCacheClient.dto.CustomerViewResponse;
import com.devsu.customerservice.infraestructure.controllers.clients.queryClient.dto.CustomerResponse;
import com.devsu.customerservice.infraestructure.controllers.in.CreateCustomer;
import com.devsu.customerservice.infraestructure.controllers.in.CustomerUpdateRequest;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerCreateResponse;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerInfoResponse;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerUpdateResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder = false))
public interface ClientMapper {

    Client dtoCreateToClientModel(CreateCustomer createCustomer);

    Client dtoUpdateToClientModel(CustomerUpdateRequest customerUpdateRequest);

    CustomerCreateResponse dtoResponseClientToResponse(CustomerResponse customerResponse);

    CustomerUpdateResponse dtoResponseClientToResponseUpdate(CustomerResponse customerResponse);

    CustomerInfoResponse dtoResponseCacheToResponse(CustomerViewResponse customerResponse);
}
