package com.small.repository;

import com.small.domain.OrderEvent;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEvent,Long> {
    Stream<OrderEvent> findOrderEventsByorderId(@Param("orderId") Long orderId);
}
