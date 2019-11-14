package com.small.repository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.small.domain.Item;
import java.util.Optional;
import javax.swing.text.html.Option;
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

//    @HystrixCommand(
//        threadPoolKey = "ItemRepository",
//        groupKey = "ItemRepository",
//        commandKey = "ItemRepository_findById"
//    )
//    @Query("SELECT I.ITEM_ID, I.CATALOG_ID, I.INS_DATE, I.INS_OPRT, I.CURRENCY, I.DESCRIPTION, I.IS_ACTIVE, I.UPD_DATE, I.UPD_OPRT, I.ITEM_NAME, I.PRICE, I.QUANTITY FROM ITEM I WHERE I.ITEM_ID = :itemId")
    Optional<Item> findById(@Param("itemId")  long itemId);

}

