package com.small.domain;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SmilePointBalance {
    private String memberId;
    private BigDecimal smilePoint;
    private Date insertDate;
    private Date updateDate;

}