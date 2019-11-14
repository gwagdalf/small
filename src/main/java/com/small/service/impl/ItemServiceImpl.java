package com.small.service.impl;

import com.small.domain.Item;
import com.small.domain.ItemOption;
import com.small.repository.ItemNativeReposiotry;
import com.small.repository.ItemOptionRepository;
import com.small.repository.ItemRepository;
import com.small.service.ItemService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemNativeReposiotry itemNativeReposiotry;

    @Autowired
    private ItemOptionRepository itemOptionRepository;

    /**
     * 상품정보 저장
     *
     * @param item
     * @return
     */
    @Override
    public Item setItem(Item item) {
        return itemRepository.save(item);
    }

    /**
     * 리스팅 상품 조회
     * 검색엔진이 없으니~ DB를 뒤지자!
     *
     * @param pageble
     * @return
     */
    @Override
    public Page<Item> getDisplayList(Pageable pageble) {
        return itemRepository.findAll(pageble);
    }

    /**
     * 상품 조회
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Item> getItemById(long id) {
        return itemNativeReposiotry.findById(id);
//        return itemRepository.findById(id);
    }

    /**
     * 주문옵션 조회
     * @param itemId
     * @return
     */
    @Override
    public Optional<ItemOption> getItemOptionByItemId(long itemId) {
        return itemOptionRepository.findByItemId(itemId);
    }


    /**
     * 상품 상태 확인
     *
     * @param item
     * @return
     */
    protected boolean checkBasicAvailablility(Item item) {
        Boolean available = item.getIsActive();
        if (available == null) {
            available = true;
        }
        if (item != null && available && item.getQuantity() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 현재 사용가능한 재고 파악
     *
     * @param itemId
     * @return
     */
    @Override
    @Transactional()
    public Integer retrieveQuantityAvailable(Long itemId) {
        Item item = itemRepository.findById(itemId).get();
        if (item != null && checkBasicAvailablility(item)) {
            return item.getQuantity();
        }
        return 0;
    }

    /**
     * 재고 복구
     * @param itemId
     * @param quantity
     */
    @Override
    @Transactional()
    public Item incrementInventory(Long itemId, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity " + quantity + " is not valid. Must be greater than zero.");
        }
        Item item = itemRepository.findById(itemId).get();
        int inventoryAvailable = retrieveQuantityAvailable(itemId);
        int newInventory = inventoryAvailable + quantity;
        item.setQuantity(newInventory);
        itemRepository.save(item);
        return item;
    }

    /**
     * 재고 차
     * @param itemId
     * @param quantity
     * @return
     */
    @Override
    @Transactional()
    public Item decrementInventory(Long itemId, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity " + quantity + " is not valid. Must be greater than zero.");
        }
        Item item = itemRepository.findById(itemId).get();
        Integer inventoryAvailable = retrieveQuantityAvailable(itemId);

        if (inventoryAvailable == null) {
            return item;
        }
        if (inventoryAvailable < quantity) {
            throw new IllegalArgumentException(
                "There was not enough inventory to fulfill this request." + "/" + itemId + "/" +  quantity + "/" + inventoryAvailable);
        }

        int newInventory = inventoryAvailable - quantity;
        item.setQuantity(newInventory);
        itemRepository.save(item);

        return item;
    }

}


