package com.small.domain;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SmilePointTrade {

    public SmilePointTrade() {
        this.approvalStatus = "Wait";
        this.tradeCode = "USE";
    }

    private String memberId;
    private BigDecimal smilePoint;  // SmilePoint
    private String tradeCode;    // 거래구분 Enum
    private String approvalStatus;  // 승인상태 Enum
    private long refPayNo;    // 참조 결제번호

}
