package com.small.dto;

import com.small.domain.Address;
import com.small.domain.Item;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderFormResponseDTO {
    private Item item;
    private Address address;
    private Integer orderQty;
    private BigDecimal totalPrice;
    private String stockStatusMessage;
    private BigDecimal totalPoint;
    private BigDecimal deductTargetPoint;

}
