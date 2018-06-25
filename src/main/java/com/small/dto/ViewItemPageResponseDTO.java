package com.small.dto;

import com.small.domain.Price;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ViewItemPageResponseDTO {
    private Long itemId;
    private String name;
    private Long catalogId;
    private String description;
    private BigDecimal price;
    private String currency;
    private Integer quantity;
    private Boolean isActive;
    private Long optionId;
    private String optionName;
    private Price optionPrice;
    private Integer optionQuantity;
}
