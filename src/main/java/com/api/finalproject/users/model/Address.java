package com.api.finalproject.users.model;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String postalCode;
    private String city;
    private String country;
}
