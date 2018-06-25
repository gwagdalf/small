package com.small.domain;

import com.small.domain.enums.EnumOrderEventType;
import com.small.domain.enums.EnumOrderStatus;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@Entity
@Table(
       // indexes = {@Index(name = "IDX_ITEM_OPTION__ITEM_ID",  columnList="buyerId", unique = false)}
)
public class OrderInfo {

    public OrderInfo() {
        this.orderEventType = EnumOrderEventType.PURCHASED;
        this.orderStatus = EnumOrderStatus.PURCHASED;

        //스냅샷 정보 ID정보/스냡샷 상품ID는 시작시 -1
        this.orderSnapShotId = new Long(-1);
        this.orderdItemNo = new Long(-1);
        this.shippingAddress = new Address();
        this.buyerId = "guest";
        this.totalOrderPrice = new BigDecimal(0);
    }


    /**
     * 주문번호
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    /**
     * 고객아이디
     */
    @Column(nullable = false)
    private String buyerId;

    /**
     * 주문상태
     */
    @Column(nullable = false)
    private EnumOrderStatus orderStatus;

    /**
     * 주문 이벤트
     */
    @Transient
    private EnumOrderEventType orderEventType;

    /**
     * 구매한 상품 번호(OrderSnapShot List)
     */
//    private List<SnapshotItem> orderdItems = new ArrayList<>();


    /**
     * 상품 ID정보
     */
    @Column(nullable = true)
    private Long orderdItemNo;


    /**
     *상품 스냅샷 ID정보
     */
    @Column(nullable = true)
    private Long orderSnapShotId;

    /**
     * 스냅샷 상품 정보
     * Non-DB Column
     */
    @Transient
    private OrderSnapShot orderdItemInfo;

    /**
     * 총 주문 금액
     */
    @Column(nullable = false)
    private BigDecimal totalOrderPrice;

    /**
     * 배송주소
     */
    @Transient
    private Address shippingAddress;

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

    @Column(nullable = true, name="UPD_DATE")
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