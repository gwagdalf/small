//package com.small.controller;
//
//import com.small.domain.Item;
//import com.small.service.ItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 상품도메인 내부 도메인 제공용 API
// */
//@RestController
//@RequestMapping(
//		value="/inventory"
//)
//public class InventoryRestController {
//
//	@Autowired
//	private ItemService itemService;
//
//	/**
//	 * 재고차감
//	 * ~ 나중에 인벤토리 서비스로~
//	 * @param itemId
//	 * @param quantity
//	 * @return
//	 */
//	@GetMapping("/decrementInventory")
//	public Item decrementInventory(@RequestParam("itemId") Long itemId, @RequestParam("quantity") int quantity) {
//		return this.itemService.decrementInventory(itemId,quantity);
//	}
//
//	/**
//     * 재고증감
//	 * @param itemId
//     * @param quantity
//     * @return
//     */
//	@GetMapping("/incrementInventory")
//	public Item incrementInventory(@RequestParam("itemId") Long itemId, @RequestParam("quantity") int quantity) {
//		return this.itemService.incrementInventory(itemId, quantity);
//	}
//
//	/**
//	 * 재고체크
//	 * @param itemId
//	 * @return
//	 */
//	@GetMapping("/retrieveQuantityAvailable/{itemId}")
//	public Integer retrieveQuantityAvailable(@PathVariable Long itemId) {
//		return this.itemService.retrieveQuantityAvailable(itemId);
//	}
//}
