package com.api.finalproject.model;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String postalCode;
    private String city;
    private String country;
}
