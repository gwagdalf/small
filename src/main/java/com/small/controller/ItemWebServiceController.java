package com.small.controller;

import com.small.domain.Item;
import com.small.domain.ItemOption;
import com.small.dto.ViewItemPageResponseDTO;
import com.small.exception.ResourceNotFoundException;
import com.small.mapper.ItemCustomMapper;
import com.small.service.ItemService;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value="/item-webservices", produces = "application/json")
public class ItemWebServiceController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCustomMapper itemCustomMapper;

    /**
     * Add Item (상품등록)
     * 따로 UI 를 만들지는 않음. 호출될때마다, 난수를 붙여서, 상품을 생성토록 함.
     * @return
     */
    @RequestMapping(value="/additem", method= {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Item> addItem() {

        Random random = new Random();

        Item item = new Item();
        item.setCurrency("KRW");
        item.setIsActive(true);
        item.setDescription("상세설명 테스트상세설명 테스트상세설명 테스트상세설명 테스트상세설명 테스트상세설명 테스트");
        item.setName(String.format("상품명-%d-상품 ", random.nextInt(10)));
        item.setQuantity(random.nextInt(9));
        item.setPrice(new BigDecimal( String.format("%d%s",random.nextInt(90),"00.0")));
        item.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        item.setLastModified(new Timestamp(System.currentTimeMillis()));
        item.setLastModifiedBy("test4plan");
        item.setCreatedBy("test4plan");
        itemService.setItem(item);

        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    /**
     * LP에서 상품리스트 조회(페이지)
     * @param _page
     * @return
     */
    @RequestMapping(value = "/getItemList", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Page<Item>> getItemList(@PageableDefault(sort = { "price" }, direction = Sort.Direction.DESC, size = 5) Pageable _page) {
        /// 요청시 : http://localhost:8080/getItemList?size=5&page=0&sort=price,asc
        Page<Item> result = this.itemService.getDisplayList(_page);

        return new ResponseEntity<Page<Item>>(result, HttpStatus.OK);
    }

    /**
     * 상품상세 가져오기
     * @param id
     * @return
     */
    @RequestMapping(value = "/getItemDetail/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<ViewItemPageResponseDTO> getItemDetail(@PathVariable long id) {

        Objects.requireNonNull(id, "ItemNo must not be null");

        Item item = this.itemService.getItemById(id).orElseThrow(ResourceNotFoundException::new);
        ItemOption itemOption = this.itemService.getItemOptionByItemId(id).orElse(null);
        ViewItemPageResponseDTO ret = itemCustomMapper.toResponseDTO(item, itemOption);

        return new ResponseEntity<ViewItemPageResponseDTO>(ret, HttpStatus.OK);
    }
}