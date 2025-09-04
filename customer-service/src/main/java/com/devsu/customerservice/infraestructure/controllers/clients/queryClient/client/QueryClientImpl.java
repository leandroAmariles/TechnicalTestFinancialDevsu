package com.devsu.customerservice.infraestructure.controllers.clients.queryClient.client;

import com.devsu.customerservice.domain.model.Client;
import com.devsu.customerservice.exeptions.ClientException;
import com.devsu.customerservice.infraestructure.controllers.clients.queryClient.IQueryClient;
import com.devsu.customerservice.infraestructure.controllers.clients.queryClient.dto.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QueryClientImpl implements IQueryClient {

    @Value("${client.query-data-service.url}")
    private String queryDataServiceUrl;

    @Value("${client.query-data-service.replace.word}")
    private String replaceWord;

    @Value("${client.query-data-service.path-creation}")
    private String queryDataCreateCustomerPath;

    @Value("${client.query-data-service.path-update}")
    private String queryDataUpdateCustomerPath;

    @Value("${client.query-data-service.path-delete}")
    private String queryDataDeleteCustomerPath;


    private final WebClient webClient;

    @Override
    public Mono<CustomerResponse> sendCreateCustomer(Client client) {
        return webClient.post().uri(queryDataServiceUrl.concat(queryDataCreateCustomerPath))
                .headers(httpHeaders -> {
                    httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
                }).bodyValue(client).retrieve().bodyToMono(CustomerResponse.class)
                .onErrorResume(e -> Mono.error(new ClientException("Error from client service: " + e.getMessage())));
    }

    @Override
    public Mono<CustomerResponse> sendUpdateCustomer(Client client, String identification) {
        return webClient.patch().uri(queryDataServiceUrl.concat(queryDataUpdateCustomerPath.concat(identification)))
                .headers(httpHeaders -> {
                    httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
                }).bodyValue(client).retrieve().bodyToMono(CustomerResponse.class)
                .onErrorResume(e -> Mono.error(new ClientException("Error from client service: " + e.getMessage())));
    }

    @Override
    public Mono<CustomerResponse> sendDeleteCustomer(Long clientId) {
        return webClient.delete().uri(queryDataServiceUrl.concat(queryDataDeleteCustomerPath
                .replace(replaceWord, clientId.toString())))
                .headers(httpHeaders ->  {
                    httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
                }).retrieve().bodyToMono(CustomerResponse.class)
                .onErrorResume(e -> Mono.error(new ClientException("Error from client service: " + e.getMessage())));
    }


}
