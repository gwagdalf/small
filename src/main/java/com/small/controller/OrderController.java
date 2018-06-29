package com.small.controller;

import com.small.domain.OrderResult;
import com.small.domain.SmilePointBalance;
import com.small.domain.SmilePointTrade;
import com.small.dto.OrderFormResponseDTO;
import com.small.mapper.OrderFormCustomMapper;
import com.small.service.OrderService;
import java.math.BigDecimal;
import java.util.Date;
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

	@GetMapping
	public String index(Model model) {
		return "order/index";
	}

	@Autowired
	private OrderFormCustomMapper orderFormCustomMapper;

	@Autowired
	private OrderService orderService;


	/**
	 * 주문서페이지
	 * @param itemId
	 * @param quantity
	 * @param model
	 * @return
	 */
	@RequestMapping( value ={"/form"})
	public String orderForm(@RequestParam("ItemNo") Long itemId, @RequestParam("OrderQty") int quantity, Model model) {

		Objects.requireNonNull(itemId, "ItemNo must not be null");
		Objects.requireNonNull(quantity, "OrderQty must not be null");


		// to do 아래는 차차 구현, 일단은 하드코딩

		String memberId = "test4plan";
		SmilePointBalance smilePointInfo
			= new SmilePointBalance("test4plan@ebay.com", BigDecimal.valueOf(500000), new Date(),new Date());
		SmilePointTrade smilePointTrade= new SmilePointTrade();
		BigDecimal deductTargetPoint = BigDecimal.valueOf(10);

		OrderFormResponseDTO data = this.orderFormCustomMapper.toResponseDTO(this.orderService.getOrderdItem(itemId),
			smilePointInfo,
			deductTargetPoint,
			this.orderService.getShippingAddress(memberId),
			quantity);

		model.addAttribute("orderFormData", data);

		return "order/orderForm";

	}

	/**
	 * 주문 처리
	 * @param itemId
	 * @param quantity
	 * @param model
	 * @return
	 */
	//@PreAuthorize("isAuthenticated()")
	@RequestMapping( value ={"/placeOrder"})
	public String placeOrder(@RequestParam("ItemNo") Long itemId, @RequestParam("OrderQty") int quantity, Model model) {

		Objects.requireNonNull(itemId, "ItemNo must not be null");
		Objects.requireNonNull(quantity, "OrderQty must not be null");
		String memberId = "test4plan";

		OrderResult result = this.orderService.addOrder(itemId,quantity,memberId);

		model.addAttribute("orderResult", result);

		if(result.getIsSuccess()){
			return "order/result";
		}
		else
		{
			return "order/failed";
		}

	}

}
