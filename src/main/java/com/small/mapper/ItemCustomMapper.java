package com.small.mapper;


import com.small.domain.Item;
import com.small.domain.ItemOption;
import com.small.dto.ViewItemPageResponseDTO;

public interface ItemCustomMapper {
    ViewItemPageResponseDTO toResponseDTO(Item srcItem, ItemOption srcOption);
}
