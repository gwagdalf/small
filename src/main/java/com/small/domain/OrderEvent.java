package com.small.domain;


import com.small.domain.enums.EnumOrderEventType;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.lang.Nullable;


/**
 * 주문 발생시 발생되는 이벤트를 티켓으로 발행
 * 어떻게 구현할 지는 진지하게 고민해보지 못함
 */
@Data
@AllArgsConstructor
@Entity
@Table
@EqualsAndHashCode(callSuper = false)
public class OrderEvent {
    public OrderEvent() {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private EnumOrderEventType type;

    @Column(nullable = false)
    private Long orderId;

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
