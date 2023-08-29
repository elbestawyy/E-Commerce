package com.ecommerce.lavana.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PurchaseResponse {
    @NotNull
    private final String orderTrackingNumber;
}
