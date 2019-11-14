package com.small.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.small.domain.Item;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ItemRepositoryTest {

  @Autowired
  ItemRepository itemRepository;

  @Test
  void findById() {

    Optional<Item> item = itemRepository.findById(1L);

    log.info("@@@@ item :" + item);
  }
}