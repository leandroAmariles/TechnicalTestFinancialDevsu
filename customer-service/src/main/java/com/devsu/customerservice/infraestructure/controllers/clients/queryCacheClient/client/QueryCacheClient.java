package com.devsu.customerservice.infraestructure.controllers.clients.queryCacheClient.client;

import com.devsu.customerservice.exeptions.ClientException;
import com.devsu.customerservice.exeptions.ResourceNotFoundException;
import com.devsu.customerservice.infraestructure.controllers.clients.queryCacheClient.IQueryCacheClient;
import com.devsu.customerservice.infraestructure.controllers.clients.queryCacheClient.dto.CustomerViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.rmi.ServerException;


@Service
@RequiredArgsConstructor
public class QueryCacheClient implements IQueryCacheClient {

    @Value("${client.query-cache-service.url}")
    private String baseUrl;

    @Value("${client.query-cache-service.client.path}")
    private String clientPath;

    @Value("${client.query-cache-service.clients.path}")
    private String clientsPath;

    private final WebClient webClient;


    @Override
    public Mono<CustomerViewResponse> getCustomerView(String customerId) {
        return webClient.get()
                .uri(baseUrl.concat(clientPath.concat("/" + customerId)))
                .headers(headers -> headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .retrieve()
                .onStatus(status -> status == HttpStatus.NOT_FOUND, response ->
                        Mono.error(new ResourceNotFoundException("Customer not found: " + customerId)))
                .onStatus(status -> status == HttpStatus.BAD_REQUEST, response ->
                        Mono.error(new ClientException("Invalid request for customer: " + customerId)))
                .bodyToMono(CustomerViewResponse.class)
                .onErrorResume(e -> Mono.error(new ClientException("Error from client service: " + e.getMessage())));
    }

    @Override
    public Flux<CustomerViewResponse> getAllCustomerView() {
        return webClient.get().uri(baseUrl.concat(clientsPath))
                .headers(headers -> headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .retrieve()
                .onStatus(status -> status == HttpStatus.NOT_FOUND, response ->
                        Mono.error(new ResourceNotFoundException("No customers found")))
                .onStatus(status -> status == HttpStatus.BAD_REQUEST, response ->
                        Mono.error(new ClientException("Invalid request for customers")))
                .bodyToFlux(CustomerViewResponse.class)
                .onErrorResume(e -> Mono.error(new ClientException("Error from client service: " + e.getMessage())));
    }
}
