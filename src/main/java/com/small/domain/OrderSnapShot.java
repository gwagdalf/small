package com.small.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table
@EqualsAndHashCode(callSuper = false)
public class OrderSnapShot {


    /**
     * 스냅샷 아이디
     */
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long Id;

    @Column(nullable = false)
    private Long orderId;

    /**
     * 상품번호
     */
    @Column(nullable = false)
    private Long itemNo;

    /**
     * 상품명
     */
    @Column(nullable = false)
    private String itemName;

    /**
     * 통화
     */
    @Column(nullable = false)
    private String currency;

    /**
     * 가격
     */
    @Column(nullable = false)
    private BigDecimal amount;

    /**
     * 주문수량
     */
    @Column(nullable = false)
    private Integer orderQty;


//    /**
//     * 옵션 수량
//     */
//    @Column(nullable = false)
//    private String optionQuntity;
//
//    /**
//     * 옵션ID
//     */
//    @Column(nullable = true)
//    private String optionId;
//
//    /**
//     * 옵션명
//     */
//    @Column(nullable = true)
//    private String optionName;

    /**
     * JPA Auditor통해서 처리하기전 임시로
     */
    @PrePersist
    public void prePersist() {
        this.setCreatedAt(new Date());
    }

    @PreUpdate
    public void preUpdate() {
        this.setLastModified(new Date());
    }


    @Column(nullable = false, name="INS_DATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false, name="UPD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date lastModified;

    @Column(name = "INS_OPRT", updatable = false)
    @CreatedBy
    private String createdBy;

    @Nullable
    @Column(name = "UPD_OPRT")
    @LastModifiedBy
    private String lastModifiedBy;

}