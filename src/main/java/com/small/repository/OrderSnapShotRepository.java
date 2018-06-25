package com.small.repository;

import com.small.domain.OrderSnapShot;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderSnapShotRepository extends JpaRepository<OrderSnapShot, Long> {
    Optional<OrderSnapShot> findOrderSnapShotByOrderId(Long orderId);
}
