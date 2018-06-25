package com.small.mapper;

import com.small.domain.Item;
import com.small.domain.SmilePointBalance;
import com.small.dto.OrderFormResponseDTO;
import java.math.BigDecimal;
import com.small.domain.Address;


public interface OrderFormCustomMapper {
    OrderFormResponseDTO toResponseDTO(Item srcItem, SmilePointBalance srcBalance,
        BigDecimal deductTargetPoint, Address srcAddress, Integer srcOrderQty);
}
