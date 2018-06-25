package com.small.mapper;

import com.small.domain.Item;
import com.small.domain.ItemOption;
import com.small.dto.ViewItemPageResponseDTO;
import org.springframework.stereotype.Component;

/**
 * ItemCustomMapperImpl
 * 두개의 엔티티가 하나의 뷰모델로 합쳐져야 하는 경우
 */
@Component
public class ItemCustomMapperImpl implements ItemCustomMapper {

    @Override
    public ViewItemPageResponseDTO toResponseDTO(Item srcItem, ItemOption srcOption){

        ViewItemPageResponseDTO res = ViewItemPageResponseDTO.builder().itemId(srcItem.getId())
                .catalogId(srcItem.getCatalogId())
                .currency(srcItem.getCurrency())
                .description(srcItem.getDescription())
                .name(srcItem.getName())
                .quantity(srcItem.getQuantity())
                .isActive(srcItem.getIsActive())
                .optionName(srcOption.getOption())
                .optionId(srcOption.getId())
                .optionPrice(srcOption.getOptionPrice())
                .optionQuantity(srcOption.getQuantity()).build();

        return res;

    }
}
