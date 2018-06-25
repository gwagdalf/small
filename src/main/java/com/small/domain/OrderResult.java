package com.small.domain;


import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class OrderResult {

    public OrderResult(){
        this.isSuccess = false;
        this.resultMessage = "";
    }

    private Boolean isSuccess;
    private String resultMessage;
    private Long orderId;
    private BigDecimal remainPoint;
}
