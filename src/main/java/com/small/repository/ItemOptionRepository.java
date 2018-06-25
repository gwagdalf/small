package com.small.repository;

import com.small.domain.ItemOption;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {
    Optional<ItemOption> findByItemId(Long itemId);
}

