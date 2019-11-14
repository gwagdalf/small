package com.small.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table

public class Item {

    /**
     * 상품번호
     */
    @Id
    @GeneratedValue
//    @Column(nullable = false, name="id")
    private Long id;

    /**
     * 상품명
     */
//    @Column(length = 250, nullable = false, name="name")
    private String name;

    /**
     * 카탈로그ID
     */
//    @Column(nullable = true,name="CATALOG_ID")
    private Long catalogId;


    /**
     * 상세설명
     */
//    @Column(nullable = false, name="description")
    private String description;

    /**
     * 상품가격
     */
//    @Column(nullable = false, name="price")
    private BigDecimal price;

    /**
     * 통화
     */
//    @Column(nullable = false, name="currency")
    private String currency;

    /**
     * 수량
     */
//    @Column(nullable = false, name="quantity")
    private Integer quantity;

    /**
     * 상품상태
     */
//    @Column(nullable = false, name="isActive")
    private Boolean isActive;

    /**
     * 마지막 수정 날짜
     */
    @LastModifiedDate
//    @Column(nullable = false, name="lastModified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    /**
     * 수정자
     */
//    @Column(name = "lastModifiedBy")
    @LastModifiedBy
    private String lastModifiedBy;

    /**
     * 생성 일자(등록일)
     */
    @CreatedDate
//    @Column(nullable = false, name="createdAt", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /**
     * 생성자
     */
//    @Column(name = "createdBy", updatable = false)
    @CreatedBy
    private String createdBy;

    // JpaResultMapper 는 생성자의 Type과 순서를 맞춰줘야 한다
    public Item(BigInteger id, String name, BigInteger catalogId, String description, BigDecimal price,
        String currency, Integer quantity, Boolean isActive, Timestamp lastModified,
        String lastModifiedBy, Timestamp createdAt, String createdBy) {
        this.id = id == null ? null : id.longValue();
        this.name = name;
        this.catalogId = catalogId == null ? null : catalogId.longValue();
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.quantity = quantity;
        this.isActive = isActive;
        this.lastModified = id == null ? null : new Date(lastModified.getTime());
        this.lastModifiedBy = lastModifiedBy;
        this.createdAt = id == null ? null : new Date(createdAt.getTime());
        this.createdBy = createdBy;
    }
}