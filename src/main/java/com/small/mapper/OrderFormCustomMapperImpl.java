package com.small.mapper;

import com.small.domain.Item;
import com.small.domain.SmilePointBalance;
import com.small.dto.OrderFormResponseDTO;
import java.math.BigDecimal;
import com.small.domain.Address;
import org.springframework.stereotype.Component;

/**
 * OrderFormCustomMapperImpl
 */
@Component
public class OrderFormCustomMapperImpl implements OrderFormCustomMapper {

    @Override
    public OrderFormResponseDTO toResponseDTO(Item srcItem, SmilePointBalance srcBalance, BigDecimal deductTargetPoint,  Address srcAddress, Integer srcOrderQty){

        String stockStatusMessage = "";

        if(srcItem.getIsActive() == true && srcItem.getQuantity() > 0){
            stockStatusMessage = "재고있음";
        }
        else{
            stockStatusMessage = "재고없음";
        }

        OrderFormResponseDTO res = OrderFormResponseDTO.builder()
                .item(srcItem)
                .address(srcAddress)
                .totalPrice(srcItem.getPrice().multiply(new BigDecimal(srcOrderQty)))
                .deductTargetPoint(deductTargetPoint)
                .stockStatusMessage(stockStatusMessage)
                .orderQty(srcOrderQty)
                .totalPoint(srcBalance.getSmilePoint())
                .build();

        return res;

    }
}
