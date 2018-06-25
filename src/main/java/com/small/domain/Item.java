package com.small.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table
public class Item {

    public Item() {

    }

    /**
     * 상품번호
     */
    @Id
    @GeneratedValue
    @Column(nullable = false, name="ITEM_ID")
    private Long id;

    /**
     * 상품명
     */
    @Column(length = 250, nullable = false, name="ITEM_NAME")
    private String name;

    /**
     * 카탈로그ID
     */
    @Column(nullable = true)
    private Long catalogId;


    /**
     * 상세설명
     */
    @Column(nullable = false)
    private String description;

    /**
     * 상품가격
     */
    @Column(nullable = false)
    private BigDecimal price;

    /**
     * 배송비
     */
    //@Column(nullable = false)
    //private BigDecimal shippingFee;

    /**
     * 통화
     */
    @Column(nullable = false)
    private String currency;

    /**
     * 수량
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * 상품상태
     */
    @Column(nullable = false)
    private Boolean isActive;

    /**
     * 마지막 수정 날짜
     */
    @LastModifiedDate
    @Column(nullable = false, name="UPD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    /**
     * 생성 일자(등록일)
     */
    @CreatedDate
    @Column(nullable = false, name="INS_DATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /**
     * 생성자
     */
    @Column(name = "INS_OPRT", updatable = false)
    @CreatedBy
    private String createdBy;

    /**
     * 수정자
     */
    @Column(name = "UPD_OPRT")
    @LastModifiedBy
    private String lastModifiedBy;
}