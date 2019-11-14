package com.small.controller;

import com.small.domain.Item;
import com.small.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 상품도메인 내부 도메인 제공용 API
 */
@RestController
@RequestMapping(value = "/api/items")
public class ItemRestController {

  @Autowired
  private ItemService itemService;


  /**
   * 상품 조회
   *
   * @param itemId
   * @return
   */
  @GetMapping("/{itemId}")
  public ResponseEntity<Item> getItem(@PathVariable long itemId) {
    return ResponseEntity
        .ok(itemService.getItemById(itemId).orElseThrow(IllegalArgumentException::new));
  }


}
