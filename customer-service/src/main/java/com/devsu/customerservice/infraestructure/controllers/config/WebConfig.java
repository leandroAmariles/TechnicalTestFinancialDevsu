package com.devsu.customerservice.infraestructure.controllers.config;


import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import javax.net.ssl.SSLException;
import java.time.Duration;

@Configuration
@Slf4j
public class WebConfig {

    @Value("${web.client.max.connections}")
    private int maxConnections;

    @Value("${web.client.acquire.timeout}")
    private long acquireTimeout;



    @Bean
    @Profile("dev")
    public org.springframework.web.reactive.function.client.WebClient inSecureWebClient() throws SSLException {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create(ConnectionProvider
                        .builder("connectionProvider")
                        .maxConnections(maxConnections)
                        .maxLifeTime(Duration.ofSeconds(acquireTimeout))
                        .pendingAcquireTimeout(Duration.ofSeconds(acquireTimeout))
                        .build())
                .secure(t -> t.sslContext(sslContext));
        return org.springframework.web.reactive.function.client.WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean
    @Profile("prod")
    public org.springframework.web.reactive.function.client.WebClient secureWebClient() throws SSLException {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .build();

        HttpClient httpClient = HttpClient.create(ConnectionProvider
                        .builder("connectionProvider")
                        .maxConnections(maxConnections)
                        .maxLifeTime(Duration.ofSeconds(acquireTimeout))
                        .pendingAcquireTimeout(Duration.ofSeconds(acquireTimeout))
                        .build())
                .secure(t -> t.sslContext(sslContext));
        log.info("Building client http connector with prod profile");
        return org.springframework.web.reactive.function.client.WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
