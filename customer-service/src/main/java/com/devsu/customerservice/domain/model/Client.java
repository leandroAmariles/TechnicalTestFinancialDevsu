package com.devsu.customerservice.domain.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Client extends Person{

    private String clientId;

    private String password;

    private boolean status;


}
