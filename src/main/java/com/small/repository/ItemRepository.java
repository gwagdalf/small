package com.small.repository;

import com.small.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Modifying
    @Query("update Item Item set Item.isActive = :isActive where Item.id = :itemId")
    void setActiveStatus(@Param("itemId") Long itemId, @Param("isActive") Boolean isActive);
}

