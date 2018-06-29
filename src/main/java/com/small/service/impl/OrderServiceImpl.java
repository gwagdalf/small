package com.small.service.impl;

import com.small.domain.Address;
import com.small.domain.Item;
import com.small.domain.OrderInfo;
import com.small.domain.OrderResult;
import com.small.domain.OrderSnapShot;
import com.small.domain.SmilePointBalance;
import com.small.domain.UseSmilePointResult;
import com.small.domain.enums.EnumOrderEventType;
import com.small.domain.enums.EnumOrderStatus;
import com.small.repository.OrderEventRepository;
import com.small.repository.OrderRepository;
import com.small.repository.OrderSnapShotRepository;
import com.small.service.ItemService;
import com.small.service.OrderService;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderEventRepository orderEventRepository;

    @Autowired
    private ItemService itemService;

//    @Autowired
//    private RewardFeignClient rewardClient;

    @Autowired
    private OrderSnapShotRepository orderSnapShotRepository;

    /**
     * 주문조회(id)기준
     *
     * @param id
     * @return
     */
    @Override
    public OrderInfo findOrderInfoById(Long id) {

        //주문 기초 정보 조회
        OrderInfo orderItem = orderRepository.findById(id).get();

        //주문 당시 상품 조회
        orderItem.setOrderdItemInfo(orderSnapShotRepository.findOrderSnapShotByOrderId(orderItem.getId()).get());

        return orderItem;
    }

    /**
     * 주문조회-페이징(buyerId)기준
     *
     * @param pageable
     * @param buyerId
     * @return
     */
    @Override
    public Page<OrderInfo> findOrderInfoByBuyerId(Pageable pageable, String buyerId) {

        Page<OrderInfo> res = orderRepository.findOrderInfoByBuyerId(pageable, buyerId);

        //순회하며, 조회 건에 대한 상품정보 바인딩
        for (OrderInfo item : res)
        {
            item.setOrderdItemInfo(orderSnapShotRepository.findById(item.getOrderSnapShotId()).get());
        }

        return res;
    }

    /**
     * 주문번호기준 스냅샷 상품 조회
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderSnapShot findOrderSnapShotByOrderId(Long orderId) {
        return orderSnapShotRepository.findOrderSnapShotByOrderId(orderId).get();
    }

    /**
     * 주문조회-페이징(id와buyerId기준)
     *
     * @param pageable
     * @param id
     * @param buyerId
     * @return
     */
    @Override
    public Page<OrderInfo> findOrderInfoByIdAndBuyerId(Pageable pageable, Long id, String buyerId) {

        Page<OrderInfo> res = orderRepository.findOrderInfoByIdAndBuyerId(pageable, id, buyerId);

        //순회하며, 조회 건에 대한 상품정보 바인딩
        for (OrderInfo item : res)
        {
            item.setOrderdItemInfo(orderSnapShotRepository.findById(item.getOrderSnapShotId()).get());
        }

        return res;
    }

    /**
     * 스냅샷 정보 조회(ID)기준
     * @param snapshotId
     * @return
     */
    @Override
    public OrderSnapShot findOrderSnapShotById(Long snapshotId){
        return orderSnapShotRepository.findById(snapshotId).get();
    }


    /**
     * 신규주문
     *
     * 이벤트:   +<--PURCHASED+  +<--ORDER_SAVED+  +<---SNAPSHOT_SAVED+ +<----STOCK_DEDUCTED+ +<----REWARD_SAVED+  <----CONFIRMED+
     * *         |            |  |          |  |           |  |            |
     * 상태:     +PURCHASED-------------------------------+PENDING----------------------------------------------+CONFIRMED+
     *
     * @param itemId
     * @param orderQty
     * @param buyerId
     * @return
     */
    @Override
    public OrderResult addOrder(Long itemId, int orderQty, String buyerId) {

        OrderResult ret = new OrderResult();

        if(orderQty < 1) {
            ret.setResultMessage("주문 수량은 한개 이상이어야 합니다.");
            return ret;
        }

        OrderInfo orderInfo = new OrderInfo();
        Item item = new Item();
        OrderSnapShot snapShot = new OrderSnapShot();
        UseSmilePointResult pointResult = new UseSmilePointResult();


        try{
            //상품번호로, 상품 정보 조회
            item = itemService.getItemById(itemId).orElse(new Item());

            //상품조회 불가
            if(item == null)  {
                ret.setResultMessage("상품을 불러오는데 실패 했습니다.");
                return ret;
            }


            //사용가능한 재고 조회
            Integer stock = itemService.retrieveQuantityAvailable(itemId);

            if(stock < orderQty){
                ret.setResultMessage("재고가 주문수량보다 적습니다. 확인 후 다시 주문 해 주세요");
                return ret;
            }

            //최초 상태값
            orderInfo.setOrderStatus(EnumOrderStatus.PENDING);
            orderInfo.setOrderdItemNo(item.getId());
            orderInfo.setBuyerId(buyerId);
            orderInfo.setOrderdItemNo(itemId);
            orderInfo.setTotalOrderPrice(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
            //주소입력
            orderInfo.setShippingAddress(new Address());
            orderInfo.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            orderInfo.setLastModified(new Timestamp(System.currentTimeMillis()));
            orderInfo.setLastModifiedBy(buyerId);
            orderInfo.setCreatedBy(buyerId);

            orderRepository.save(orderInfo);
            orderInfo.setOrderEventType(EnumOrderEventType.ORDER_SAVED);

            //스냅샷 생성
            snapShot.setOrderId(orderInfo.getId());
            snapShot.setItemNo(item.getId());
            snapShot.setItemName(item.getName());
            snapShot.setCurrency(item.getCurrency());
            snapShot.setAmount(item.getPrice());
            snapShot.setOrderQty(orderQty);

            snapShot.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            snapShot.setLastModified(new Timestamp(System.currentTimeMillis()));
            snapShot.setLastModifiedBy(buyerId);
            snapShot.setCreatedBy(buyerId);

            //스냅샷 저장
            orderSnapShotRepository.save(snapShot);
            orderInfo.setOrderEventType(EnumOrderEventType.SNAPSHOT_SAVED);

            //재고 차감
            itemService.decrementInventory(item.getId(), orderQty);
            orderInfo.setOrderEventType(EnumOrderEventType.STOCK_DEDUCTED);

            //리워드 조회는 생략
            //SmilePointBalance smilePointInfo = rewardClient.findSmilePointBalancesByMemberId(buyerId);
            SmilePointBalance smilePointInfo
              = new SmilePointBalance("test4plan@ebay.com", BigDecimal.valueOf(500000), new Date(),new Date());




            orderInfo.setOrderEventType(EnumOrderEventType.REWARD_SAVED);


            //문제없이 완료됬기에 상태값변경
            orderInfo.setLastModified(new Timestamp(System.currentTimeMillis()));
            orderInfo.setLastModifiedBy(buyerId);
            orderInfo.setOrderStatus(EnumOrderStatus.CONFIRMED);
            orderInfo.setOrderSnapShotId(snapShot.getId());
            orderRepository.save(orderInfo);

            orderInfo.setOrderEventType(EnumOrderEventType.CONFIRMED);
            ret.setIsSuccess(true);
            ret.setOrderId(orderInfo.getId());
        }
        catch(Exception e){
            //롤백할 필요가 없도록 주문에 대해 FAIL 처리

            ret.setResultMessage("서비스 제공에 불편을 제공해드려 죄송합니다.");

            orderInfo.setOrderStatus(EnumOrderStatus.FAILED);
            orderRepository.save(orderInfo);

            if(orderInfo.getOrderEventType() == EnumOrderEventType.STOCK_DEDUCTED ||
                    orderInfo.getOrderEventType() == EnumOrderEventType.REWARD_SAVED   ) {
                itemService.decrementInventory(item.getId(), orderQty);
            }


        }
        finally {

            if(orderInfo.getOrderStatus() != EnumOrderStatus.FAILED){

                return  ret;
            }
            else return ret;
        }
    }

    /**
     * 주문상태변경
     *
     * @param id
     * @param enumOrderStatus
     */
    @Override
    public void setOrderStatus(Long id, EnumOrderStatus enumOrderStatus) {
        OrderInfo order = orderRepository.findById(id).get();
        order.setOrderStatus(enumOrderStatus);
        orderRepository.save(order);
    }


    /**
     * 주문한 상품정보 조회
     * @param itemId
     * @return
     */
    public  Item getOrderdItem(Long itemId){
        return this.itemService.getItemById(itemId).orElse(new Item());
    }




    /**
     * 배송주소 조회
     * @param memberId
     * @return
     */
    public  Address getShippingAddress(String memberId){
        return new Address();
    }
}