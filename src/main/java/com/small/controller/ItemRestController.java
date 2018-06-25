package com.small.controller;

import com.small.domain.Item;
import com.small.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 상품도메인 내부 도메인 제공용 API
 */
@RestController
@RequestMapping(
        value = "/items"
)
public class ItemRestController {

//    @Autowired
//    private ItemService itemService;
//
//    /**
//     * 상품 조회
//     *
//     * @param itemId
//     * @return
//     */
//    @GetMapping("/viewitem/{itemId}")
//    public Item viewitem(@PathVariable long itemId) {
//        return this.itemService.getItemById(itemId).orElseThrow(IllegalArgumentException::new);
//    }

}
