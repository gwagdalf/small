package com.small.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(
//        indexes = {@Index(name = "IDX_ITEM_OPTION__ITEM_NO",  columnList="itemNo", unique = false)}
)
public class ItemOption {
    @Id
    @GeneratedValue
    @Column(nullable = false, name="OPTION_ID")
    private Long id;

    private Long itemId;

    private String option;

    private Price optionPrice;

    private Integer quantity;
}