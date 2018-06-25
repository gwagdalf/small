package com.small.domain;

import com.small.domain.enums.EnumAddressType;
import lombok.Data;

@Data
public class Address {
    private String street1, street2, state, city, country;
    private Integer zipCode;
    private EnumAddressType addressType;
}
