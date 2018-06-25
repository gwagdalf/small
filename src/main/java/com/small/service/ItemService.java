package com.small.service;

import com.small.domain.Item;
import com.small.domain.ItemOption;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ItemService {
    Optional<Item> getItemById(long id);
    Optional<ItemOption> getItemOptionByItemId(long itemId);
    Item setItem(Item item);
    Page<Item> getDisplayList(Pageable pageble);
    Item decrementInventory(Long itemId, int quantity);
    Integer retrieveQuantityAvailable(Long itemId);
    Item incrementInventory(Long itemId, int quantity);
}
