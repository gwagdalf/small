package com.small.repository;

import com.small.domain.OrderInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<OrderInfo, Long> {
    Page<OrderInfo> findOrderInfoByBuyerId(Pageable pageable, String buyerId);
    Page<OrderInfo> findOrderInfoByIdAndBuyerId(Pageable pageable, Long id, String buyerId);

//    @Modifying
//    @Query("update order_info set order_info.order_status = :orderStatus where order_info.id = :id")
//    void setOrderStatus(@Param("id") Long id, @Param("orderStatus") EnumOrderStatus orderStatus);
}
