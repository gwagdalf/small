package com.small.repository;

import com.small.domain.Item;
import java.util.Optional;

public interface ItemNativeReposiotry {
  Optional<Item> findById(long itemId);

}
