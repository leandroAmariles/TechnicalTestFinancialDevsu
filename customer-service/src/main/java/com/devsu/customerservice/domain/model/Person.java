package com.devsu.customerservice.domain.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Person {

    private Long personId;

    private String name;

    private String gender;

    private int age;

    private String identification;

    private String address;

    private String phone;
}
