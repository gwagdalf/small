package com.small.service;

import com.small.domain.Address;
import com.small.domain.Item;
import com.small.domain.OrderInfo;
import com.small.domain.OrderResult;
import com.small.domain.OrderSnapShot;
import com.small.domain.SmilePointBalance;
import com.small.domain.enums.EnumOrderStatus;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderInfo findOrderInfoById(Long id);

    Page<OrderInfo> findOrderInfoByBuyerId(Pageable pageable, String buyerId);

    Page<OrderInfo> findOrderInfoByIdAndBuyerId(Pageable pageable, Long id, String buyerId);

    OrderResult addOrder(Long itemId, int orderQty, String buyerId);

    OrderSnapShot findOrderSnapShotByOrderId(Long orderId);

    OrderSnapShot findOrderSnapShotById(Long snapshotId);

    void setOrderStatus(Long id, EnumOrderStatus enumOrderStatus);

    Item getOrderdItem(Long itemId);



    Address getShippingAddress(String memberId);
}