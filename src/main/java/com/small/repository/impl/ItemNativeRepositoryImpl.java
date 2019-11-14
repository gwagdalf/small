package com.small.repository.impl;

import java.util.ArrayList;
import java.util.Optional;
import com.small.domain.Item;
import com.small.repository.ItemNativeReposiotry;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ItemNativeRepositoryImpl implements ItemNativeReposiotry {

  @SuppressWarnings("unchecked")
  @Override
  public Optional<Item> findById(long itemId) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb");
    EntityManager em = emf.createEntityManager();

    List<Item> itemlist = new ArrayList<>();
    Item item = new Item();
    String sql = String.format("SELECT i.Id as id, i.name as name, i.CATALOG_ID as catalogId, i.DESCRIPTION as description, i.PRICE as price, i.CURRENCY as currency, i.quantity as quantity, i.IS_ACTIVE as isActive, i.LAST_MODIFIED as lastModified, i.LAST_MODIFIED_BY as lastModifiedBy, i.CREATED_AT as createdAt, i.CREATED_BY as createdBy FROM ITEM I WHERE I.id= %s", itemId);
    try {

      JpaResultMapper jpaResultMapper = new JpaResultMapper();
      itemlist = jpaResultMapper.list(em.createNativeQuery(sql), Item.class);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return itemlist.stream().findFirst();
  }
}
