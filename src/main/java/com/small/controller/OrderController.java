package com.small.controller;

import com.small.domain.OrderResult;
import com.small.dto.OrderFormResponseDTO;
import com.small.mapper.OrderFormCustomMapper;
import java.util.Objects;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class OrderController {

	private static Logger log = LoggerFactory.getLogger(OrderController.class);

//	@GetMapping
//	public String index(Model model) {
//		return "order/index";
//	}
//
//	@Autowired
//	private OrderFormCustomMapper orderFormCustomMapper;
//
//	@Autowired
//	private OrderService orderService;


	/**
	 * 주문서페이지
	 * @param itemId
	 * @param quantity
	 * @param model
	 * @return
	 */
	@RequestMapping( value ={"/form"})
	public String orderForm(@RequestParam("ItemNo") Long itemId, @RequestParam("OrderQty") int quantity, Model model) {

//		Objects.requireNonNull(itemId, "ItemNo must not be null");
//		Objects.requireNonNull(quantity, "OrderQty must not be null");
//
//
//		String memberId = "test4plan";
//
//		OrderFormResponseDTO data = this.orderFormCustomMapper.toResponseDTO(this.orderService.getOrderdItem(itemId),
//						this.orderService.getSmilePointBalance(memberId),
//						this.orderService.getDeductTargetPoint(memberId),
//						this.orderService.getShippingAddress(memberId),
//						quantity);
//
//		model.addAttribute("orderFormData", data);

		return "만드는중 입니다.";

	}
//
//	/**
//	 * 주문 처리
//	 * @param itemId
//	 * @param quantity
//	 * @param model
//	 * @return
//	 */
//	//@PreAuthorize("isAuthenticated()")
//	@RequestMapping( value ={"/placeOrder"})
//	public String placeOrder(@RequestParam("ItemNo") Long itemId, @RequestParam("OrderQty") int quantity, Model model) {
//
//		Objects.requireNonNull(itemId, "ItemNo must not be null");
//		Objects.requireNonNull(quantity, "OrderQty must not be null");
//		String memberId = "test4plan";
//
//		OrderResult result = this.orderService.addOrder(itemId,quantity,memberId);
//
//		model.addAttribute("orderResult", result);
//
//		if(result.getIsSuccess()){
//			return "order/result";
//		}
//		else
//		{
//			return "order/failed";
//		}
//
//	}

}
